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
        DinaMessage message = DinaMessage.of(ByteUtil.hexStringToBytes(a.replace(" ", "")));
        AlertData alertData = parser.parse(message.getData());
        Assertions.assertNotNull(alertData);
    }
}