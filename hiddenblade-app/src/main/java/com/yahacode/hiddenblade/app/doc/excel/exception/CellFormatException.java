package com.yahacode.hiddenblade.app.doc.excel.exception;

/**
 * Excel cell format exception
 *
 * @author zengyongli
 * @since 2022/06/15
 */
public class CellFormatException extends CellValueMissingException {

    String rawValue;

    public String getRawValue() {
        return rawValue;
    }

    public CellFormatException(Throwable cause, int row, String column, String rawValue) {
        super(cause, row, column);
        this.rawValue = rawValue;
    }
}
