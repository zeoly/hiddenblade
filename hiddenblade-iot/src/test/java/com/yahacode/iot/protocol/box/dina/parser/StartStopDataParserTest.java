package com.yahacode.iot.protocol.box.dina.parser;

import com.yahacode.hiddenblade.tool.utils.ByteUtil;
import com.yahacode.iot.protocol.box.dina.DinaMessage;
import com.yahacode.iot.protocol.box.dina.data.StartStopData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

class StartStopDataParserTest {


    @InjectMocks
    StartStopDataParser parser;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void parse() {
        String a = "FA FA DA 97 00 12 2E 8B 74 FA 00 25 03 00 2D 16"
                + "04 1A 01 FE 9D 9A 06 B3 B0 E0 00 00 C9 00 00 03"
                + "3E 0C 00 01 00 8A 00 01 00 01 52 59 7F 00 15 47"
                + "2C 13 FB";
        DinaMessage message = DinaMessage.of(ByteUtil.hexStringToBytes(a.replace(" ", "")));
        StartStopData startStopData = parser.parse(message.getData());
        Assertions.assertNotNull(startStopData);
    }
}