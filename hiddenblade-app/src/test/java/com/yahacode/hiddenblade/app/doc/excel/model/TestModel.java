package com.yahacode.hiddenblade.app.doc.excel.model;

import com.yahacode.hiddenblade.app.doc.excel.annotation.ColumnPurpose;
import com.yahacode.hiddenblade.app.doc.excel.annotation.ExcelColumn;

import java.time.LocalDate;
import java.time.LocalTime;

public class TestModel {

    @ExcelColumn(name = "第二列", index = 1)
    String a;

    @ExcelColumn(index = 0)
    Integer b;

//    @ExcelColumn(order = 3, dateTimePattern = "yyyyMMddHHmmss")
//    Date date;
//
//    @ExcelColumn(order = 2, dateTimePattern = "yyyyMMddHHmmss")
//    LocalDateTime localDateTime;
//
//    @ExcelColumn(order = 5)
//    Double dd;

    @ExcelColumn(index = 6, dateTimePattern = "yyyyMMdd")
    LocalDate localDate;

    @ExcelColumn(index = 7, dateTimePattern = "HHmmss")
    LocalTime localTime;

    String c;

    String d;

    public TestModel(String a, Integer b, LocalDate localDate, LocalTime localTime) {
        this.a = a;
        this.b = b;
        this.localDate = localDate;
        this.localTime = localTime;
    }

    //    public TestModel(String a, Integer b, Date date, LocalDateTime localDateTime, Double dd, LocalDate localDate, LocalTime localTime) {
//        this.a = a;
//        this.b = b;
//        this.date = date;
//        this.localDateTime = localDateTime;
//        this.dd = dd;
//        this.localDate = localDate;
//        this.localTime = localTime;
//    }
//
//    public TestModel(String a, Integer b, Date date, LocalDateTime localDateTime, Double dd) {
//        this.a = a;
//        this.b = b;
//        this.date = date;
//        this.localDateTime = localDateTime;
//        this.dd = dd;
//    }
//
//    public TestModel(String a, Integer b, Date date, LocalDateTime localDateTime) {
//        this.a = a;
//        this.b = b;
//        this.date = date;
//        this.localDateTime = localDateTime;
//    }
//
//    public TestModel(String a, Integer b) {
//        this.a = a;
//        this.b = b;
//    }

    public TestModel() {
    }

    public String getA() {
        return a;
    }

    public void setA(String a) {
        this.a = a;
    }

    public Integer getB() {
        return b;
    }

    public void setB(Integer b) {
        this.b = b;
    }

//    public Date getDate() {
//        return date;
//    }
//
//    public void setDate(Date date) {
//        this.date = date;
//    }
//
//    public LocalDateTime getLocalDateTime() {
//        return localDateTime;
//    }
//
//    public void setLocalDateTime(LocalDateTime localDateTime) {
//        this.localDateTime = localDateTime;
//    }
//
//    public Double getDd() {
//        return dd;
//    }
//
//    public void setDd(Double dd) {
//        this.dd = dd;
//    }

    public LocalDate getLocalDate() {
        return localDate;
    }

    public void setLocalDate(LocalDate localDate) {
        this.localDate = localDate;
    }

    public LocalTime getLocalTime() {
        return localTime;
    }

    public void setLocalTime(LocalTime localTime) {
        this.localTime = localTime;
    }

    @ExcelColumn(name = "合并测试", index = 5, purpose = ColumnPurpose.EXPORT)
    public String getName() {
        return a + "+" + b;
    }

    @ExcelColumn(index = 5, purpose = ColumnPurpose.IMPORT)
    public void parse(String input) {
        String[] a = input.split("\\+");
        c = a[0];
        d = a[1];
    }
}
