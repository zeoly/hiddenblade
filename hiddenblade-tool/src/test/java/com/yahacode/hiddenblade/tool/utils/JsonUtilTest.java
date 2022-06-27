package com.yahacode.hiddenblade.tool.utils;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


class JsonUtilTest {

    @Test
    void toList() {
        List<TestEntity> list = new ArrayList<>();
        list.add(new TestEntity("foo", 1));
        list.add(new TestEntity("bar", 2));
        String jsonString = JsonUtil.toStr(list);
        List<TestEntity> toList = JsonUtil.toList(jsonString, TestEntity.class);
        assertEquals("foo", toList.get(0).getA());
    }
}