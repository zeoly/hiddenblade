package com.yahacode.hiddenblade.app.doc.excel;

import com.yahacode.hiddenblade.app.doc.excel.annotation.ExcelColumn;
import com.yahacode.hiddenblade.app.doc.excel.exception.CellFormatException;
import com.yahacode.hiddenblade.app.doc.excel.exception.CellValueMissingException;
import com.yahacode.hiddenblade.app.doc.excel.exception.ExceptionRow;
import com.yahacode.hiddenblade.app.doc.excel.exception.ExceptionType;
import com.yahacode.hiddenblade.tool.utils.DateUtil;
import com.yahacode.hiddenblade.tool.utils.StringUtil;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * Excel sheet reader/importer
 *
 * @author zengyongli
 * @since 2022/05/20
 */
public class SheetReader {

    private static Logger log = LoggerFactory.getLogger(SheetReader.class);

    /**
     * count the row of sheet
     *
     * @param file excel file
     * @return row count
     * @throws IOException count exception
     */
    public static int countRow(File file) throws IOException {
        FileInputStream inputStream = new FileInputStream(file);
        return countRow(inputStream);
    }


    /**
     * count the row of sheet
     *
     * @param file upload file
     * @return row count
     * @throws IOException count exception
     */
    public static int countRow(MultipartFile file) throws IOException {
        InputStream inputStream = file.getInputStream();
        return countRow(inputStream);
    }

    private static int countRow(InputStream inputStream) throws IOException {
        XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
        XSSFSheet sheet = workbook.getSheetAt(0);
        return sheet.getPhysicalNumberOfRows();
    }

    /**
     * read upload file to list
     *
     * @param file  upload file
     * @param clazz data class
     * @param <T>   data type
     * @return data list
     * @throws Exception read exception
     */
    public static <T> List<T> read(MultipartFile file, Class<T> clazz) throws Exception {
        return read(file.getInputStream(), clazz, false, null);
    }

    public static <T> ReservedResult<T> readReserved(MultipartFile file, Class<T> clazz) throws Exception {
        List<ExceptionRow> exceptionRows = new LinkedList<>();
        List<T> list = read(file.getInputStream(), clazz, true, exceptionRows);
        return new ReservedResult<T>(list, exceptionRows);
    }

    /**
     * read file to list
     *
     * @param file  excel file
     * @param clazz data class
     * @param <T>   data type
     * @return data list
     * @throws Exception read exception
     */
    public static <T> List<T> read(File file, Class<T> clazz) throws Exception {
        FileInputStream fileInputStream = new FileInputStream(file);
        return read(fileInputStream, clazz, false, null);
    }

    public static <T> ReservedResult<T> readReserved(File file, Class<T> clazz) throws Exception {
        List<ExceptionRow> exceptionRows = new LinkedList<>();
        FileInputStream fileInputStream = new FileInputStream(file);
        List<T> list = read(fileInputStream, clazz, true, exceptionRows);
        return new ReservedResult<T>(list, exceptionRows);
    }

    private static <T> List<T> read(InputStream inputStream, Class<T> clazz, boolean reserved, List<ExceptionRow> exceptionRows) throws Exception {
        XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
        XSSFSheet sheet = workbook.getSheetAt(0);
        SheetContext context = SheetContext.forImport(clazz);
        List<T> result = new LinkedList<>();
        for (int i = 1; i < sheet.getPhysicalNumberOfRows(); i++) {
            XSSFRow row = sheet.getRow(i);
            if (isEmptyRow(row)) {
                if (reserved) {
                    result.add(null);
                } else {
                    continue;
                }
            }
            T instance = convertRowToInstance(row, context, clazz, reserved, exceptionRows);
            result.add(instance);
        }
        return result;
    }

    private static String getName(AccessibleObject accessibleObject) {
        ExcelColumn annotation = accessibleObject.getAnnotation(ExcelColumn.class);
        if (annotation != null && StringUtil.isNotBlank(annotation.name())) {
            return annotation.name();
        } else if (accessibleObject instanceof Field) {
            Field field = (Field) accessibleObject;
            return field.getName();
        } else if (accessibleObject instanceof Method) {
            Method method = (Method) accessibleObject;
            return method.getName();
        } else {
            return null;
        }
    }

    private static String getCellString(XSSFCell cell) {
        CellType cellType = cell.getCellType();
        switch (cellType) {
            case STRING:
                return cell.getStringCellValue().trim();
            case NUMERIC:
                if (org.apache.poi.ss.usermodel.DateUtil.isCellDateFormatted(cell)) {
                    return DateUtil.formatFull(cell.getDateCellValue());
                }
            default:
                cell.setCellType(CellType.STRING);
                return cell.toString();
        }
    }

    private static String getDateString(XSSFCell cell, String pattern) {
        CellType cellType = cell.getCellType();
        switch (cellType) {
            case STRING:
                return cell.getStringCellValue().trim();
            case NUMERIC:
                if (org.apache.poi.ss.usermodel.DateUtil.isCellDateFormatted(cell)) {
                    return DateUtil.format(cell.getDateCellValue(), pattern);
                }
            default:
                cell.setCellType(CellType.STRING);
                return cell.toString();
        }
    }

    private static boolean isEmptyRow(XSSFRow row) {
        for (int i = 0; i <= row.getLastCellNum(); i++) {
            XSSFCell cell = row.getCell(i);
            if (cell != null && cell.getCellType() != CellType.BLANK) {
                return false;
            }
        }
        return true;
    }

    private static <T> T convertRowToInstance(XSSFRow row, SheetContext context, Class<T> clazz, boolean reserved, List<ExceptionRow> exceptionRows) throws Exception {
        Constructor<T> declaredConstructor = clazz.getDeclaredConstructor();
        T instance = declaredConstructor.newInstance();
        ExceptionRow exceptionRow = new ExceptionRow(row.getRowNum());
        for (int j = 0; j <= row.getLastCellNum(); j++) {
            XSSFCell cell = row.getCell(j);
            AccessibleObject accessibleObject = context.getMembers().get(j);
            if (accessibleObject == null) {
                continue;
            } else if (cell == null || cell.getCellType() == CellType.BLANK) {
                ExcelColumn column = accessibleObject.getAnnotation(ExcelColumn.class);
                if (!column.optional()) {
                    if (reserved) {
                        exceptionRow.addColumn(getName(accessibleObject), ExceptionType.MISSING);
                    } else {
                        throw new CellValueMissingException(row.getRowNum(), getName(accessibleObject));
                    }
                }
            } else if (accessibleObject instanceof Field) {
                Field field = (Field) accessibleObject;
                ExcelColumn column = field.getAnnotation(ExcelColumn.class);
                try {
                    if (field.getType() == String.class) {
                        field.set(instance, getCellString(cell));
                    } else if (field.getType() == Integer.class) {
                        Double value = Double.parseDouble(getCellString(cell));
                        field.set(instance, value.intValue());
                    } else if (field.getType() == Double.class) {
                        field.set(instance, Double.parseDouble(getCellString(cell)));
                    } else if (field.getType() == BigDecimal.class) {
                        field.set(instance, new BigDecimal(getCellString(cell)));
                    } else if (field.getType() == Long.class) {
                        field.set(instance, Long.parseLong(getCellString(cell)));
                    } else if (field.getType() == Boolean.class) {
                        field.set(instance, cell.getBooleanCellValue());
                    } else if (field.getType() == LocalDateTime.class) {
                        String pattern = StringUtil.isEmpty(column.dateTimePattern()) ? DateUtil.PATTERN_FULL : column.dateTimePattern();
                        field.set(instance, LocalDateTime.parse(getDateString(cell, pattern), DateTimeFormatter.ofPattern(pattern)));
                    } else if (field.getType() == LocalDate.class) {
                        String pattern = StringUtil.isEmpty(column.dateTimePattern()) ? DateUtil.PATTERN_DATE : column.dateTimePattern();
                        field.set(instance, LocalDate.parse(getDateString(cell, pattern), DateTimeFormatter.ofPattern(pattern)));
                    } else if (field.getType() == LocalTime.class) {
                        String pattern = StringUtil.isEmpty(column.dateTimePattern()) ? DateUtil.PATTERN_TIME : column.dateTimePattern();
                        field.set(instance, LocalTime.parse(getDateString(cell, pattern), DateTimeFormatter.ofPattern(pattern)));
                    } else if (field.getType() == Date.class) {
                        String pattern = StringUtil.isEmpty(column.dateTimePattern()) ? DateUtil.PATTERN_FULL : column.dateTimePattern();
                        field.set(instance, DateUtil.parse(getDateString(cell, pattern), pattern));
                    }
                } catch (Exception e) {
                    log.error("reading cell value [{}] to field [{}] error", cell.getStringCellValue(), field.getName(), e);
                    if (reserved) {
                        exceptionRow.addColumn(getName(accessibleObject), ExceptionType.FORMAT);
                    } else {
                        throw new CellFormatException(e, row.getRowNum(), getName(field), cell.getStringCellValue());
                    }
                }
            } else if (accessibleObject instanceof Method) {
                Method method = (Method) accessibleObject;
                try {
                    method.invoke(instance, getCellString(cell));
                } catch (Exception e) {
                    log.error("invoke cell value [{}] of field [{}] error", cell.getStringCellValue(), method.getName(), e);
                    if (reserved) {
                        exceptionRow.addColumn(getName(accessibleObject), ExceptionType.FORMAT);
                    } else {
                        throw new CellFormatException(e, row.getRowNum(), getName(method), cell.getStringCellValue());
                    }
                }
            }
        }
        if (exceptionRow.hasException()) {
            exceptionRows.add(exceptionRow);
            instance = null;
        }
        return instance;
    }

//    List list = new ArrayList<>();
//
//    Class clazz;
//
//    public static <T> List<T> read(InputStream inputStream, Class<T> clazz) throws Exception {
//        OPCPackage opcPackage = OPCPackage.open(inputStream);
//        ReadOnlySharedStringsTable table = new ReadOnlySharedStringsTable(opcPackage);
//        XSSFReader reader = new XSSFReader(opcPackage);
//        StylesTable stylesTable = reader.getStylesTable();
//        XSSFReader.SheetIterator iterator = (XSSFReader.SheetIterator) reader.getSheetsData();
//        while (iterator.hasNext()) {
//            try (InputStream stream = iterator.next()) {
//
//            }
//        }
//    }
//
//    private class SheetToList implements XSSFSheetXMLHandler.SheetContentsHandler {
//
//        @Override
//        public void startRow(int rowNum) {
//
//        }
//
//        @Override
//        public void endRow(int rowNum) {
//
//        }
//
//        @Override
//        public void cell(String cellReference, String formattedValue, XSSFComment comment) {
//
//        }
//    }
}
