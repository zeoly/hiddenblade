package com.yahacode.hiddenblade.tool.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StringUtilTest {

    @Test
    void isYear() {
        assertTrue(StringUtil.isYear("2021"));
    }

    @Test
    void isYearMonth() {
        assertTrue(StringUtil.isYearMonth("2021-02"));
    }


    @Test
    void isHourMinute() {
        assertTrue(StringUtil.isHourMinute("12:12"));
        assertFalse(StringUtil.isHourMinute("12:12:12"));
        assertFalse(StringUtil.isHourMinute("24:12"));
    }

    @Test
    void isTime() {
        assertTrue(StringUtil.isTime("00:01:00"));
        assertFalse(StringUtil.isTime("24:01:00"));
        assertFalse(StringUtil.isTime("13:60:00"));
    }

    @Test
    void isDateHourMinute() {
        assertTrue(StringUtil.isDateHourMinute("2021-01-01 23:12"));
        assertFalse(StringUtil.isDateHourMinute("2021-01-01 23:12:12"));
        assertFalse(StringUtil.isDateHourMinute("2021-01-01"));
    }

    @Test
    void isDateTime() {
        assertTrue(StringUtil.isDateTime("2021-01-01 23:12:12"));
        assertFalse(StringUtil.isDateTime("2021/00/01 23:12:34"));
        assertFalse(StringUtil.isDateTime("2021-01-01 23:12"));
        assertFalse(StringUtil.isDateTime("2021-01-32 23:12:12"));
        assertFalse(StringUtil.isDateTime("2021-00-01 23:12:34"));
        assertFalse(StringUtil.isDateTime("2021-13-01 23:12:34"));
    }

    @Test
    void isDateTimeShort() {
        assertTrue(StringUtil.isDateTimeShort("20220101121212"));
        assertFalse(StringUtil.isDateTimeShort("20220101 121212"));
        assertFalse(StringUtil.isDateTimeShort("20220101241212"));
        assertFalse(StringUtil.isDateTimeShort("20220132121212"));
    }

    @Test
    void isNumericFormat() {
        assertTrue(StringUtil.isNumericFormat("12.3"));
    }

    @Test
    void isVIN() {
        assertTrue(StringUtil.isVIN("LFV3A28W4L3718113"));
    }

    @Test
    void isPlateNo() {
        assertTrue(StringUtil.isPlateNo("粤B12345"));
        assertTrue(StringUtil.isPlateNo("粤BD12345"));
        assertTrue(StringUtil.isPlateNo("粤GGSU9港"));
        assertTrue(StringUtil.isPlateNo("粤X58KC学"));
        assertTrue(StringUtil.isPlateNo("使LXFKQZ"));
        assertTrue(StringUtil.isPlateNo("晋DDRE5领"));
        assertTrue(StringUtil.isPlateNo("WJ晋12345"));
    }
}