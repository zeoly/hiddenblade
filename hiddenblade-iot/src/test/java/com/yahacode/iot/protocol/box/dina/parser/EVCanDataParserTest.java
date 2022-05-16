package com.yahacode.iot.protocol.box.dina.parser;

import com.yahacode.hiddenblade.tool.utils.ByteUtil;
import com.yahacode.iot.protocol.box.dina.DinaMessage;
import com.yahacode.iot.protocol.box.dina.data.EVCanData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import java.util.List;

class EVCanDataParserTest {

    @InjectMocks
    EVCanDataParser parser;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void parse() {
        String a = "FA FA 6E B6 00 0B 6E 3B 08 45 00 21 02 38 2F 16"
                + "05 10 00 00 20 00 30 00 00 00 00 00 C0 01 37 00"
                + "00 03 D1 76 78 03 D1 76 78 00 01 57 C0 8C FB";
        String b = "FA FA DD B6 00 0B 6E 41 1B 31 00 19 03 02 3B 16"
                + "05 10 00 00 20 00 20 00 00 00 00 00 40 01 91 00"
                + "00 00 03 8A 40 5C FB";
        String c = "FA FA 15 B6 00 0B 6E 3B 03 08 00 1D 03 02 1E 16"
                + "05 10 00 00 20 00 30 00 00 00 00 00 40 01 7A 00"
                + "00 00 2E ED 26 00 02 FD A0 66 FB";
        String d = "FA FA 73 B6 00 0B 6E 3B 03 08 00 1D 03 13 25 16"
                + "05 10 00 00 20 00 30 00 00 00 00 00 40 01 78 02"
                + "D0 00 2E FE 13 00 02 EE 00 6F FB";
        String e = "FA FA DE B6 00 0B 6E 42 A8 53 00 19 03 2F 38 16"
                + "05 10 00 00 20 00 20 00 00 00 00 00 40 01 52 00"
                + "00 00 02 03 A0 08 FB";
        DinaMessage message = DinaMessage.of(ByteUtil.hexStringToBytes(e.replace(" ", "")));
        List<EVCanData> data = parser.parse(message.getData());
        Assertions.assertNotNull(data);
    }
}