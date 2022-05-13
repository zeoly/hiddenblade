package com.yahacode.iot.protocol.box.dina.parser;

import com.yahacode.hiddenblade.tool.utils.ByteUtil;
import com.yahacode.iot.protocol.box.dina.DinaMessage;
import com.yahacode.iot.protocol.box.dina.data.AlertData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

class AlertDataParserTest {

    @InjectMocks
    AlertDataParser parser;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void parse() {
        String a = "FA FA BC 04 00 11 03 7F 07 C4 00 23 03 00 15 16"
                + "04 1A 02 53 BE 9C 06 FC 2B 78 00 00 05 00 00 0C"
                + "E4 04 00 00 00 7C 10 00 00 00 03 00 02 00 01 9F"
                + "FB";
        String b = "FA FA B2 04 00 11 03 7F 05 93 00 1F 0F 18 0A 16"
                + "05 0C 01 59 62 F2 06 CC 8E 60 00 00 81 00 00 06"
                + "9A 0C 00 01 00 8E 00 20 00 00 18 1A FB";
        DinaMessage message = DinaMessage.of(ByteUtil.hexStringToBytes(b.replace(" ", "")));
        AlertData alertData = parser.parse(message.getData());
        Assertions.assertNotNull(alertData);
    }
}