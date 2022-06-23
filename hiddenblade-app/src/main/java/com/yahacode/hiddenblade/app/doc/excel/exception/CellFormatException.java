package com.yahacode.hiddenblade.app.doc.excel.exception;

/**
 * Excel cell format exception
 *
 * @author zengyongli
 * @since 2022/06/15
 */
public class CellFormatException extends Exception {

    int row;

    String columnName;

    String rawValue;

    public int getRow() {
        return row;
    }

    public String getColumnName() {
        return columnName;
    }

    public String getRawValue() {
        return rawValue;
    }

    public CellFormatException(Throwable cause, int row, String columnName, String rawValue) {
        super(cause);
        this.row = row;
        this.columnName = columnName;
        this.rawValue = rawValue;
    }
}
