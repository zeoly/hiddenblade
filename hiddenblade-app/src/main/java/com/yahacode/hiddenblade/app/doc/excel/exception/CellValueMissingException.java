package com.yahacode.hiddenblade.app.doc.excel.exception;

/**
 * Excel cell value missing exception
 *
 * @author zengyongli
 * @since 2022/06/15
 */
public class CellValueMissingException extends Exception {

    int row;

    String columnName;

    public CellValueMissingException(int row, String columnName) {
        this.row = row;
        this.columnName = columnName;
    }

    public int getRow() {
        return row;
    }

    public String getColumnName() {
        return columnName;
    }
}
