package com.yahacode.hiddenblade.app.doc.excel;

import com.yahacode.hiddenblade.app.doc.excel.model.TestModel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
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
        list.add(new TestModel("9273692837659378", 3423, new Date(), LocalDateTime.now(), 5.555, LocalDate.now(), LocalTime.now()));
        list.add(new TestModel("3875836538746858", 12312321, new Date(), LocalDateTime.now(), 4.3423432, LocalDate.now(), LocalTime.now()));
        SheetWriter.write("文件测试", list, TestModel.class);
    }

    @Test
    void read() throws Exception {
        File file = new File("文件测试.xlsx");
        System.out.println("row: " + SheetReader.countRow(file));
        List<TestModel> list = SheetReader.read(file, TestModel.class);
        Assertions.assertNotNull(list);
    }
}