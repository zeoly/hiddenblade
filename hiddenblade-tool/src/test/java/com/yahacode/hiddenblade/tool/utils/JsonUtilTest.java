package com.yahacode.hiddenblade.tool.utils;

import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


class JsonUtilTest {

    @Test
    void toList() {
        List<TestEntity> list = Lists.newArrayList(new TestEntity("foo", 1), new TestEntity("bar", 2));
        String jsonString = JsonUtil.toStr(list);
        List<TestEntity> toList = JsonUtil.toList(jsonString, TestEntity.class);
        assertEquals("foo", toList.get(0).getA());
    }
}