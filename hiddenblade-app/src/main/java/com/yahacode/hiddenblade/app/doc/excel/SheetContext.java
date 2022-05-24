package com.yahacode.hiddenblade.app.doc.excel;

import com.yahacode.hiddenblade.app.doc.excel.annotation.ColumnPurpose;
import com.yahacode.hiddenblade.app.doc.excel.annotation.ExcelColumn;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Sheet param context
 *
 * @author zengyongli
 * @since 2022/05/18
 */
public class SheetContext {

    private static final Logger log = LoggerFactory.getLogger(SheetContext.class);

    List<AccessibleObject> members = new LinkedList<>();

    public static SheetContext forImport(Class clazz) {
        SheetContext context = new SheetContext();
        Map<Integer, AccessibleObject> importMemberMap = new LinkedHashMap<>();
        List<Integer> importKeyList = new ArrayList<>();
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            ExcelColumn column = field.getAnnotation(ExcelColumn.class);
            if (column != null && column.purpose() != ColumnPurpose.EXPORT) {
                log.info("read field for import: {}, name:{}, order:{}", field.getName(), column.name(), column.order());
                if (importKeyList.contains(column.order())) {
                    throw new RuntimeException("order collision: " + column.order());
                }
                field.setAccessible(true);
                importMemberMap.put(column.order(), field);
                importKeyList.add(column.order());
            }
        }
        Method[] methods = clazz.getDeclaredMethods();
        for (Method method : methods) {
            ExcelColumn column = method.getAnnotation(ExcelColumn.class);
            if (column != null && column.purpose() != ColumnPurpose.EXPORT) {
                log.info("read method for import: {}, name:{}, order:{}", method.getName(), column.name(), column.order());
                if (importKeyList.contains(column.order())) {
                    throw new RuntimeException("order collision: " + column.order());
                }
                method.setAccessible(true);
                importMemberMap.put(column.order(), method);
                importKeyList.add(column.order());
            }
        }
        Collections.sort(importKeyList);
        for (Integer key : importKeyList) {
            context.getMembers().add(importMemberMap.get(key));
        }
        log.info("read column for import finish, found {} column", context.getMembers().size());
        return context;
    }

    public static SheetContext forExport(Class clazz) {
        Map<Integer, AccessibleObject> exportMemberMap = new LinkedHashMap<>();
        List<Integer> exportKeyList = new ArrayList<>();
        SheetContext context = new SheetContext();
        while (clazz != null) {
            Field[] fields = clazz.getDeclaredFields();
            for (Field field : fields) {
                ExcelColumn column = field.getAnnotation(ExcelColumn.class);
                if (column != null && column.purpose() != ColumnPurpose.IMPORT) {
                    log.info("read field for export: {}, name:{}, order:{}", field.getName(), column.name(), column.order());
                    if (exportKeyList.contains(column.order())) {
                        throw new RuntimeException("order collision: " + column.order());
                    }
                    field.setAccessible(true);
                    exportMemberMap.put(column.order(), field);
                    exportKeyList.add(column.order());
                }
            }
            Method[] methods = clazz.getDeclaredMethods();
            for (Method method : methods) {
                ExcelColumn column = method.getAnnotation(ExcelColumn.class);
                if (column != null && column.purpose() != ColumnPurpose.IMPORT) {
                    log.info("read method for export: {}, name:{}, order:{}", method.getName(), column.name(), column.order());
                    if (exportKeyList.contains(column.order())) {
                        throw new RuntimeException("order collision: " + column.order());
                    }
                    method.setAccessible(true);
                    exportMemberMap.put(column.order(), method);
                    exportKeyList.add(column.order());
                }
            }
            clazz = clazz.getSuperclass();
        }
        Collections.sort(exportKeyList);
        for (Integer key : exportKeyList) {
            context.getMembers().add(exportMemberMap.get(key));
        }
        log.info("read column for export finish, found {} column", context.getMembers().size());
        return context;
    }

    public List<AccessibleObject> getMembers() {
        return members;
    }
}
