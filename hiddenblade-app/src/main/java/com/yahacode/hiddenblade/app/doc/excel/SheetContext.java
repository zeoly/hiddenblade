package com.yahacode.hiddenblade.app.doc.excel;

import org.apache.poi.ss.usermodel.CellStyle;

import java.lang.reflect.AccessibleObject;
import java.util.LinkedList;
import java.util.List;

/**
 * Sheet param context
 *
 * @author zengyongli
 * @since 2022/05/18
 */
public class SheetContext {

    List<AccessibleObject> members = new LinkedList<>();

    List<CellStyle> headerStyles = new LinkedList<>();

    List<CellStyle> cellStyles = new LinkedList<>();

    public List<AccessibleObject> getMembers() {
        return members;
    }

    public List<CellStyle> getHeaderStyles() {
        return headerStyles;
    }

    public List<CellStyle> getCellStyles() {
        return cellStyles;
    }
}
