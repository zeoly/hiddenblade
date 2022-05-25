package com.yahacode.hiddenblade.app.doc.excel;

import com.yahacode.hiddenblade.app.doc.excel.annotation.ExcelColumn;
import com.yahacode.hiddenblade.tool.utils.DateUtil;
import com.yahacode.hiddenblade.tool.utils.StringUtil;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class SheetReader {

    public static int countRow(File file) throws IOException {
        FileInputStream inputStream = new FileInputStream(file);
        return countRow(inputStream);
    }

    public static int countRow(MultipartFile file) throws IOException {
        InputStream inputStream = file.getInputStream();
        return countRow(inputStream);
    }

    private static int countRow(InputStream inputStream) throws IOException {
        XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
        XSSFSheet sheet = workbook.getSheetAt(0);
        return sheet.getPhysicalNumberOfRows();
    }

    public static <T> List<T> read(MultipartFile file, Class<T> clazz) throws Exception {
        return read(file.getInputStream(), clazz);
    }

    public static <T> List<T> read(File file, Class<T> clazz) throws Exception {
        FileInputStream fileInputStream = new FileInputStream(file);
        return read(fileInputStream, clazz);
    }

    private static <T> List<T> read(InputStream inputStream, Class<T> clazz) throws Exception {
        XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
        XSSFSheet sheet = workbook.getSheetAt(0);
        SheetContext context = SheetContext.forImport(clazz);
        List<T> result = new LinkedList<>();
        for (int i = 1; i < sheet.getPhysicalNumberOfRows(); i++) {
            XSSFRow row = sheet.getRow(i);
            Constructor<T> declaredConstructor = clazz.getDeclaredConstructor();
            T instance = declaredConstructor.newInstance();
            for (int j = 0; j <= row.getLastCellNum(); j++) {
                XSSFCell cell = row.getCell(j);
                AccessibleObject accessibleObject = context.getMembers().get(j);
                if (accessibleObject == null) {
                    continue;
                } else if (accessibleObject instanceof Field) {
                    Field field = (Field) accessibleObject;
                    ExcelColumn column = field.getAnnotation(ExcelColumn.class);
                    if (field.getType() == String.class) {
                        field.set(instance, cell.getStringCellValue());
                    } else if (field.getType() == Integer.class) {
                        Double value = Double.parseDouble(getCellString(cell));
                        field.set(instance, value.intValue());
                    } else if (field.getType() == Double.class) {
                        field.set(instance, Double.parseDouble(getCellString(cell)));
                    } else if (field.getType() == Long.class) {
                        field.setLong(instance, Long.parseLong(getCellString(cell)));
                    } else if (field.getType() == Boolean.class) {
                        field.setBoolean(instance, cell.getBooleanCellValue());
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
                } else if (accessibleObject instanceof Method) {
                    Method method = (Method) accessibleObject;
                    method.invoke(instance, getCellString(cell));
                }
            }
            result.add(instance);
        }
        return result;
    }

    private static String getCellString(XSSFCell cell) {
        CellType cellType = cell.getCellType();
        switch (cellType) {
            case STRING:
                return cell.getStringCellValue();
            case NUMERIC:
                if (org.apache.poi.ss.usermodel.DateUtil.isCellDateFormatted(cell)) {
                    return DateUtil.formatFull(cell.getDateCellValue());
                } else {
                    return String.valueOf(cell.getNumericCellValue());
                }
            default:
                return cell.getRawValue();
        }
    }

    private static String getDateString(XSSFCell cell, String pattern) {
        CellType cellType = cell.getCellType();
        switch (cellType) {
            case STRING:
                return cell.getStringCellValue();
            case NUMERIC:
                if (org.apache.poi.ss.usermodel.DateUtil.isCellDateFormatted(cell)) {
                    return DateUtil.format(cell.getDateCellValue(), pattern);
                } else {
                    return String.valueOf(cell.getNumericCellValue());
                }
            default:
                return cell.getRawValue();
        }
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
