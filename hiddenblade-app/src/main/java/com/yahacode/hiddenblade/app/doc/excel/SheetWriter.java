package com.yahacode.hiddenblade.app.doc.excel;

import com.yahacode.hiddenblade.app.doc.excel.annotation.ExcelColumn;
import com.yahacode.hiddenblade.app.doc.excel.annotation.Style;
import com.yahacode.hiddenblade.tool.utils.DateUtil;
import com.yahacode.hiddenblade.tool.utils.JsonUtil;
import com.yahacode.hiddenblade.tool.utils.StringUtil;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.streaming.SXSSFCell;
import org.apache.poi.xssf.streaming.SXSSFRow;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URLEncoder;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Excel sheet writer/exporter
 *
 * @author zengyongli
 * @since 2022/05/20
 */
public class SheetWriter {

    private static final Logger log = LoggerFactory.getLogger(SheetWriter.class);

    private String fileName;

    private SXSSFWorkbook workbook;

    private SXSSFSheet sheet;

    private SheetContext sheetContext;

    private SheetWriter(String fileName, SXSSFWorkbook workbook, SXSSFSheet sheet, SheetContext sheetContext) {
        this.fileName = fileName;
        this.workbook = workbook;
        this.sheet = sheet;
        this.sheetContext = sheetContext;
    }

    public static SheetWriter open(String fileName, Class clazz) {
        return open(fileName, fileName, clazz);
    }

    public static SheetWriter open(String fileName, String sheetName, Class clazz) {
        SXSSFWorkbook wb = new SXSSFWorkbook();
        SXSSFSheet sheet = wb.createSheet(sheetName);
        SheetContext context = buildContext(wb, clazz);
        setColumnWidth(sheet, context);
        createHeader(sheet, context);
        return new SheetWriter(fileName, wb, sheet, context);
    }

    public void fill(List<? extends Object> list) throws Exception {
        int rowNum = sheet.getPhysicalNumberOfRows();
        for (int i = 0; i < list.size(); i++) {
            Row row = sheet.createRow(rowNum + i);
            createRowCells(row, list.get(i), sheetContext);
        }
    }

    public void export(HttpServletResponse response) throws IOException {
        response.reset();
        response.setContentType("application/vnd.ms-excel;charset=utf-8");
        response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8") + ".xlsx");
        ServletOutputStream out = response.getOutputStream();
        workbook.write(out);
        workbook.dispose();
        out.close();
    }

    public void write() throws IOException {
        try (OutputStream fileOut = new FileOutputStream(fileName + ".xlsx")) {
            workbook.write(fileOut);
            workbook.dispose();
        }
    }

    public static void export(HttpServletResponse response, String fileName, List<? extends Object> list, Class clazz) throws Exception {
        export(response, fileName, fileName, list, clazz);
    }

    public static void export(HttpServletResponse response, String fileName, String sheetName, List<? extends Object> list, Class clazz) throws Exception {
        SheetWriter writer = open(fileName, sheetName, clazz);
        writer.fill(list);
        writer.export(response);
    }

    public static void write(String fileName, List<? extends Object> list, Class clazz) throws Exception {
        write(fileName, fileName, list, clazz);
    }

    public static void write(String fileName, String sheetName, List<? extends Object> list, Class clazz) throws Exception {
        SheetWriter writer = open(fileName, sheetName, clazz);
        writer.fill(list);
        writer.write();
    }

    private static void createHeader(SXSSFSheet sheet, SheetContext context) {
        SXSSFRow row = sheet.createRow(0);
        List<AccessibleObject> members = context.getMembers();
        for (int i = 0; i < members.size(); i++) {
            SXSSFCell cell = row.createCell(i);
            ExcelColumn column = members.get(i).getAnnotation(ExcelColumn.class);
            cell.setCellValue(StringUtil.isEmpty(column.name()) ? getName(members.get(i)) : column.name());
            cell.setCellStyle(context.getHeaderStyles().get(i));
        }
    }

    private static void createRowCells(Row row, Object obj, SheetContext context) throws Exception {
        List<AccessibleObject> members = context.getMembers();
        for (int i = 0; i < members.size(); i++) {
            Cell cell = row.createCell(i);
            ExcelColumn column = members.get(i).getAnnotation(ExcelColumn.class);
            AccessibleObject accessibleObject = members.get(i);
            Object objValue = null;
            if (accessibleObject instanceof Field) {
                Field field = (Field) accessibleObject;
                field.setAccessible(true);
                objValue = field.get(obj);
            } else if (accessibleObject instanceof Method) {
                Method method = (Method) accessibleObject;
                method.setAccessible(true);
                objValue = method.invoke(obj);
            }

            if (objValue == null) {
                cell.setBlank();
            } else if (objValue instanceof String) {
                cell.setCellValue((String) objValue);
            } else if (objValue instanceof Integer) {
                cell.setCellValue((int) objValue);
            } else if (objValue instanceof Date) {
                Date value = (Date) objValue;
                String pattern = StringUtil.isEmpty(column.dateTimePattern()) ? DateUtil.PATTERN_FULL : column.dateTimePattern();
                cell.setCellValue(DateUtil.format(value, pattern));
            } else if (objValue instanceof LocalDateTime) {
                LocalDateTime value = (LocalDateTime) objValue;
                String pattern = StringUtil.isEmpty(column.dateTimePattern()) ? DateUtil.PATTERN_FULL : column.dateTimePattern();
                cell.setCellValue(value.format(DateTimeFormatter.ofPattern(pattern)));
            } else if (objValue instanceof LocalDate) {
                LocalDate value = (LocalDate) objValue;
                String pattern = StringUtil.isEmpty(column.dateTimePattern()) ? DateUtil.PATTERN_DATE : column.dateTimePattern();
                cell.setCellValue(value.format(DateTimeFormatter.ofPattern(pattern)));
            } else if (objValue instanceof LocalTime) {
                LocalTime value = (LocalTime) objValue;
                String pattern = StringUtil.isEmpty(column.dateTimePattern()) ? DateUtil.PATTERN_TIME : column.dateTimePattern();
                cell.setCellValue(value.format(DateTimeFormatter.ofPattern(pattern)));
            } else {
                cell.setCellValue(JsonUtil.toStr(objValue));
            }
            cell.setCellStyle(context.getCellStyles().get(i));
        }

    }

    private static void setColumnWidth(SXSSFSheet sheet, SheetContext context) {
        for (int i = 0; i < context.getMembers().size(); i++) {
            ExcelColumn column = context.getMembers().get(i).getAnnotation(ExcelColumn.class);
            sheet.setColumnWidth(i, column.width() * 256);
        }
    }

    private static SheetContext buildContext(SXSSFWorkbook wb, Class clazz) {
        Map<Integer, AccessibleObject> memberMap = new LinkedHashMap<>();
        Map<Integer, CellStyle> headStyleMap = new LinkedHashMap<>();
        Map<Integer, CellStyle> cellStyleMap = new LinkedHashMap<>();
        List<Integer> keyList = new ArrayList<>();
        SheetContext context = new SheetContext();
        while (clazz != null) {
            Field[] fields = clazz.getDeclaredFields();
            for (Field field : fields) {
                ExcelColumn column = field.getAnnotation(ExcelColumn.class);
                if (column != null) {
                    log.info("read column: {}, name:{}, order:{}", field.getName(), column.name(), column.order());
                    if (keyList.contains(column.order())) {
                        throw new RuntimeException("order collision: " + column.order());
                    }
                    memberMap.put(column.order(), field);
                    headStyleMap.put(column.order(), buildCellStyle(wb, column.headerStyle()));
                    cellStyleMap.put(column.order(), buildCellStyle(wb, column.cellStyle()));
                    keyList.add(column.order());
                }
            }
            Method[] methods = clazz.getDeclaredMethods();
            for (Method method : methods) {
                ExcelColumn column = method.getAnnotation(ExcelColumn.class);
                if (column != null) {
                    log.info("read column: {}, name:{}, order:{}", method.getName(), column.name(), column.order());
                    if (keyList.contains(column.order())) {
                        throw new RuntimeException("order collision: " + column.order());
                    }
                    memberMap.put(column.order(), method);
                    headStyleMap.put(column.order(), buildCellStyle(wb, column.headerStyle()));
                    cellStyleMap.put(column.order(), buildCellStyle(wb, column.cellStyle()));
                    keyList.add(column.order());
                }
            }
            clazz = clazz.getSuperclass();
        }
        Collections.sort(keyList);
        for (Integer key : keyList) {
            context.getMembers().add(memberMap.get(key));
            context.getHeaderStyles().add(headStyleMap.get(key));
            context.getCellStyles().add(cellStyleMap.get(key));
        }
        log.info("read column finish, found {} columns", context.getMembers().size());
        return context;
    }

    private static CellStyle buildCellStyle(SXSSFWorkbook wb, Style style) {
        CellStyle cellStyle = wb.createCellStyle();
        cellStyle.setAlignment(style.alignment());
        Font font = wb.createFont();
        font.setFontHeightInPoints(style.fontSize());
        font.setBold(style.bold());
        cellStyle.setFont(font);
        if (style.fillColor() != -1) {
            cellStyle.setFillForegroundColor(style.fillColor());
            cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        }
        cellStyle.setBorderBottom(style.border());
        cellStyle.setBorderTop(style.border());
        cellStyle.setBorderLeft(style.border());
        cellStyle.setBorderRight(style.border());
        return cellStyle;
    }

    private static String getName(AccessibleObject accessibleObject) {
        if (accessibleObject instanceof Field) {
            Field field = (Field) accessibleObject;
            return field.getName();
        } else if (accessibleObject instanceof Method) {
            Method method = (Method) accessibleObject;
            return method.getName();
        } else {
            return null;
        }
    }
}
