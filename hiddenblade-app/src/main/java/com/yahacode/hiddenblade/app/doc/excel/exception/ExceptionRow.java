package com.yahacode.hiddenblade.app.doc.excel.exception;

import java.util.LinkedList;
import java.util.List;

/**
 * Row with exception
 *
 * @author zengyongli
 * @since 0.3.0
 */
public class ExceptionRow {

    /**
     * row number
     */
    private Integer row;

    /**
     * column exceptions in this row
     */
    private List<ExceptionColumn> columns;

    public Integer getRow() {
        return row;
    }

    public List<ExceptionColumn> getColumns() {
        return columns;
    }

    public ExceptionRow(Integer row) {
        this.row = row;
        this.columns = new LinkedList<>();
    }

    /**
     * Whether this row has column exception
     *
     * @return has column exception
     */
    public boolean hasException() {
        return columns.size() > 0;
    }

    public void addColumn(String columnName, ExceptionType type) {
        this.columns.add(new ExceptionColumn(columnName, type));
    }
}
