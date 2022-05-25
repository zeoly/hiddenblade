package com.yahacode.hiddenblade.app.doc.excel;

import com.yahacode.hiddenblade.app.doc.excel.annotation.ColumnPurpose;
import com.yahacode.hiddenblade.app.doc.excel.annotation.ExcelColumn;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * Sheet param context
 *
 * @author zengyongli
 * @since 2022/05/18
 */
public class SheetContext {

    private static final Logger log = LoggerFactory.getLogger(SheetContext.class);

    private Map<Integer, AccessibleObject> members = new HashMap<>();

    public static SheetContext forImport(Class clazz) {
        SheetContext context = new SheetContext();
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            ExcelColumn column = field.getAnnotation(ExcelColumn.class);
            if (column != null && column.purpose() != ColumnPurpose.EXPORT) {
                log.info("read field for import: {}, name:{}, index:{}", field.getName(), column.name(), column.index());
                if (context.getMembers().containsKey(column.index())) {
                    throw new RuntimeException("index collision: " + column.index());
                }
                field.setAccessible(true);
                context.getMembers().put(column.index(), field);
            }
        }
        Method[] methods = clazz.getDeclaredMethods();
        for (Method method : methods) {
            ExcelColumn column = method.getAnnotation(ExcelColumn.class);
            if (column != null && column.purpose() != ColumnPurpose.EXPORT) {
                log.info("read method for import: {}, name:{}, index:{}", method.getName(), column.name(), column.index());
                if (context.getMembers().containsKey(column.index())) {
                    throw new RuntimeException("index collision: " + column.index());
                }
                method.setAccessible(true);
                context.getMembers().put(column.index(), method);
            }
        }
        log.info("read column for import finish, found {} column", context.getMembers().size());
        return context;
    }

    public static SheetContext forExport(Class clazz) {
        SheetContext context = new SheetContext();
        while (clazz != null) {
            Field[] fields = clazz.getDeclaredFields();
            for (Field field : fields) {
                ExcelColumn column = field.getAnnotation(ExcelColumn.class);
                if (column != null && column.purpose() != ColumnPurpose.IMPORT) {
                    log.info("read field for export: {}, name:{}, index:{}", field.getName(), column.name(), column.index());
                    if (context.getMembers().containsKey(column.index())) {
                        throw new RuntimeException("index collision: " + column.index());
                    }
                    field.setAccessible(true);
                    context.getMembers().put(column.index(), field);
                }
            }
            Method[] methods = clazz.getDeclaredMethods();
            for (Method method : methods) {
                ExcelColumn column = method.getAnnotation(ExcelColumn.class);
                if (column != null && column.purpose() != ColumnPurpose.IMPORT) {
                    log.info("read method for export: {}, name:{}, order:{}", method.getName(), column.name(), column.index());
                    if (context.getMembers().containsKey(column.index())) {
                        throw new RuntimeException("order collision: " + column.index());
                    }
                    method.setAccessible(true);
                    context.getMembers().put(column.index(), method);
                }
            }
            clazz = clazz.getSuperclass();
        }
        log.info("read column for export finish, found {} column", context.getMembers().size());
        return context;
    }

    public Map<Integer, AccessibleObject> getMembers() {
        return members;
    }
}
