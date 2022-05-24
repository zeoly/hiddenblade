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
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

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

    private SheetContext context;

    private List<CellStyle> headerStyles = new LinkedList<>();

    private List<CellStyle> cellStyles = new LinkedList<>();

    private SheetWriter(String fileName, SXSSFWorkbook workbook, SXSSFSheet sheet, SheetContext sheetContext) {
        this.fileName = fileName;
        this.workbook = workbook;
        this.sheet = sheet;
        this.context = sheetContext;
        createStyle();
        createHeader();
    }

    public static SheetWriter open(String fileName, Class clazz) {
        return open(fileName, fileName, clazz);
    }

    public static SheetWriter open(String fileName, String sheetName, Class clazz) {
        SXSSFWorkbook wb = new SXSSFWorkbook();
        SXSSFSheet sheet = wb.createSheet(sheetName);
        SheetContext context = SheetContext.forExport(clazz);
        return new SheetWriter(fileName, wb, sheet, context);
    }

    public void fill(List<? extends Object> list) throws Exception {
        int rowNum = sheet.getPhysicalNumberOfRows();
        for (int i = 0; i < list.size(); i++) {
            Row row = sheet.createRow(rowNum + i);
            createRowCells(row, list.get(i));
        }
    }

    public void export(HttpServletResponse response) throws IOException {
        response.reset();
        response.setContentType("application/vnd.ms-excel;charset=utf-8");
        response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8") + ".xlsx");
        ServletOutputStream out = response.getOutputStream();
        setColumnWidth();
        workbook.write(out);
        workbook.dispose();
        out.close();
    }

    public void write() throws IOException {
        try (OutputStream fileOut = new FileOutputStream(fileName + ".xlsx")) {
            setColumnWidth();
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

    private void createStyle() {
        for (int i = 0; i < context.getMembers().size(); i++) {
            AccessibleObject accessibleObject = context.getMembers().get(i);
            ExcelColumn annotation = accessibleObject.getAnnotation(ExcelColumn.class);
            headerStyles.add(buildCellStyle(annotation.headerStyle()));
            cellStyles.add(buildCellStyle(annotation.cellStyle()));
        }
    }

    private void createHeader() {
        SXSSFRow row = sheet.createRow(0);
        List<AccessibleObject> members = context.getMembers();
        for (int i = 0; i < members.size(); i++) {
            SXSSFCell cell = row.createCell(i);
            ExcelColumn column = members.get(i).getAnnotation(ExcelColumn.class);
            cell.setCellValue(StringUtil.isEmpty(column.name()) ? getName(members.get(i)) : column.name());
            cell.setCellStyle(headerStyles.get(i));
        }
    }

    private void createRowCells(Row row, Object obj) throws Exception {
        List<AccessibleObject> members = context.getMembers();
        for (int i = 0; i < members.size(); i++) {
            Cell cell = row.createCell(i);
            ExcelColumn column = members.get(i).getAnnotation(ExcelColumn.class);
            AccessibleObject accessibleObject = members.get(i);
            Object objValue = null;
            if (accessibleObject instanceof Field) {
                Field field = (Field) accessibleObject;
                objValue = field.get(obj);
            } else if (accessibleObject instanceof Method) {
                Method method = (Method) accessibleObject;
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
            cell.setCellStyle(cellStyles.get(i));
        }
    }

    private void setColumnWidth() {
        sheet.trackAllColumnsForAutoSizing();
        for (int i = 0; i < context.getMembers().size(); i++) {
            ExcelColumn column = context.getMembers().get(i).getAnnotation(ExcelColumn.class);
            if (column.width() == -1) {
                sheet.autoSizeColumn(i, true);
            } else {
                sheet.setColumnWidth(i, column.width() * 256);
            }
        }
    }

    private CellStyle buildCellStyle(Style style) {
        CellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setAlignment(style.alignment());
        Font font = workbook.createFont();
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

    private String getName(AccessibleObject accessibleObject) {
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
