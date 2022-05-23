package com.yahacode.hiddenblade.app.doc.excel;

import com.yahacode.hiddenblade.app.doc.excel.annotation.ExcelColumn;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

class SheetWriterTest {

    @Test
    void write() throws Exception {
        List<TestModel> list = new ArrayList<>();
        list.add(new TestModel(null,"aasdf", 3423, new Date(), LocalDateTime.now(), 5.555, LocalDate.now(), LocalTime.now()));
        list.add(new TestModel("parent",null, 12312321, new Date(), LocalDateTime.now(), 4.3423432, LocalDate.now(), LocalTime.now()));
        SheetWriter.write("文件测试", list, TestModel.class);
    }

    class BaseModel {

        @ExcelColumn(order = 9, name = "父类")
        private String parent;

        public String getParent() {
            return parent;
        }

        public void setParent(String parent) {
            this.parent = parent;
        }
    }

    class TestModel extends BaseModel {

        @ExcelColumn(name = "第二列", order = 2)
        String a;

        @ExcelColumn(order = 1)
        Integer b;

        @ExcelColumn(order = 4)
        Date date;

        @ExcelColumn(order = 3, dateTimePattern = "yyyy/MM/dd")
        LocalDateTime localDateTime;

        @ExcelColumn(order = 6)
        Double dd;

        @ExcelColumn(order = 7)
        LocalDate localDate;

        @ExcelColumn(order = 8)
        LocalTime localTime;



        public TestModel(String parent, String a, Integer b, Date date, LocalDateTime localDateTime, Double dd, LocalDate localDate, LocalTime localTime) {
            super.parent = parent;
            this.a = a;
            this.b = b;
            this.date = date;
            this.localDateTime = localDateTime;
            this.dd = dd;
            this.localDate = localDate;
            this.localTime = localTime;
        }

        public TestModel(String a, Integer b, Date date, LocalDateTime localDateTime, Double dd) {
            this.a = a;
            this.b = b;
            this.date = date;
            this.localDateTime = localDateTime;
            this.dd = dd;
        }

        public TestModel(String a, Integer b, Date date, LocalDateTime localDateTime) {
            this.a = a;
            this.b = b;
            this.date = date;
            this.localDateTime = localDateTime;
        }

        public TestModel(String a, Integer b) {
            this.a = a;
            this.b = b;
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

        public Date getDate() {
            return date;
        }

        public void setDate(Date date) {
            this.date = date;
        }

        public LocalDateTime getLocalDateTime() {
            return localDateTime;
        }

        public void setLocalDateTime(LocalDateTime localDateTime) {
            this.localDateTime = localDateTime;
        }

        public Double getDd() {
            return dd;
        }

        public void setDd(Double dd) {
            this.dd = dd;
        }

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

        @ExcelColumn(name = "合并测试", order = 5)
        public String getName() {
            return a + "+" + b;
        }
    }
}