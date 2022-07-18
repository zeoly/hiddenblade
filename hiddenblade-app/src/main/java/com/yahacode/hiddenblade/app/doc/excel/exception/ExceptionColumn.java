package com.yahacode.hiddenblade.app.doc.excel.exception;

/**
 * Column with exception
 *
 * @author zengyongli
 * @since 0.3.0
 */
public class ExceptionColumn {

    private String columnName;

    private ExceptionType type;

    public String getColumnName() {
        return columnName;
    }

    public ExceptionType getType() {
        return type;
    }

    public ExceptionColumn(String columnName, ExceptionType type) {
        this.columnName = columnName;
        this.type = type;
    }
}
