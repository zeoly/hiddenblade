package com.yahacode.iot.protocol.box.dina.parser;

import com.yahacode.hiddenblade.tool.utils.ByteUtil;
import com.yahacode.iot.protocol.box.dina.DinaMessage;
import com.yahacode.iot.protocol.box.dina.data.StatusData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

class StatusDataParserTest {

    @InjectMocks
    StatusDataParser parser;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void parse() {
        String a = "FA FA 83 BD 00 11 03 7F 05 93 00 E9 0C 24 02 16" +
                "05 08 21 01 01 01 02 01 00 03 01 00 11 04 00 33" +
                "F0 03 21 01 01 22 01 01 23 01 02 24 01 00 25 01" +
                "00 26 02 00 3C 27 02 00 1E 28 02 00 1E 29 02 00" +
                "87 2A 02 00 83 2B 02 00 74 2C 02 00 FA 2D 02 0B" +
                "B7 2E 02 0B B7 2F 01 01 31 0B 31 35 39 30 35 31" +
                "38 32 37 36 31 32 2B 54 43 50 3B 31 32 34 2E 31" +
                "39 36 2E 35 37 2E 39 32 3A 33 39 32 33 31 3B 31" +
                "32 34 2E 31 39 36 2E 35 37 2E 39 32 3A 33 39 32" +
                "33 31 33 07 63 6D 6E 65 74 2C 2C 34 18 75 70 67" +
                "72 61 64 65 2E 63 70 73 64 6E 61 2E 63 6F 6D 3A" +
                "33 31 35 32 38 35 0D 47 49 44 41 47 31 30 33 56" +
                "39 33 32 00 36 14 47 49 44 34 2E 30 20 20 46 37" +
                "31 33 33 63 63 20 20 20 20 20 37 01 00 41 01 00" +
                "51 02 00 23 52 02 00 BA 53 02 62 7A 54 01 00 61" +
                "01 00 62 01 00 14 FB";
        DinaMessage message = DinaMessage.of(ByteUtil.hexStringToBytes(a.replace(" ", "")));
        StatusData statusData = parser.parse(message.getData());
        Assertions.assertNotNull(statusData);
    }
}