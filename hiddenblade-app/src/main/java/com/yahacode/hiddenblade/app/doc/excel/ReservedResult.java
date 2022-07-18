package com.yahacode.hiddenblade.app.doc.excel;

import com.yahacode.hiddenblade.app.doc.excel.exception.ExceptionRow;

import java.util.List;

/**
 * Result for reserved reading
 *
 * @param <T> data type
 * @author zengyongli
 * @since 0.3.0
 */
public class ReservedResult<T> {

    /**
     * result list, contain NULL
     */
    private List<T> list;

    /**
     * row exceptions
     */
    private List<ExceptionRow> exceptionList;

    public List<T> getList() {
        return list;
    }

    public List<ExceptionRow> getExceptionList() {
        return exceptionList;
    }

    public ReservedResult(List<T> list, List<ExceptionRow> exceptionList) {
        this.list = list;
        this.exceptionList = exceptionList;
    }
}
