package com.yahacode.hiddenblade.app.doc.excel.exception;

/**
 * Excel cell value missing exception
 *
 * @author zengyongli
 * @since 2022/06/15
 */
public class CellValueMissingException extends Exception {

    int row;

    String column;

    public CellValueMissingException(int row, String column) {
        this.row = row;
        this.column = column;
    }

    protected CellValueMissingException(Throwable cause, int row, String column) {
        super(cause);
        this.row = row;
        this.column = column;
    }
}
