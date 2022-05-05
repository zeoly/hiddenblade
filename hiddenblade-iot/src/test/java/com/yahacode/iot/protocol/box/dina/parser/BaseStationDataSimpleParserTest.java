package com.yahacode.iot.protocol.box.dina.parser;

import com.yahacode.hiddenblade.tool.utils.ByteUtil;
import com.yahacode.iot.protocol.box.dina.DinaMessage;
import com.yahacode.iot.protocol.box.dina.data.BaseStationData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;

class BaseStationDataSimpleParserTest {

    @InjectMocks
    BaseStationDataSimpleParser parser;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void parse() {
        String a = "FA FA 19 08 00 11 03 7F 07 C4 00 22 09 38 36 16"
                + "05 04 01 59 05 22 06 CD D9 F0 00 00 3A 00 00 00"
                + "00 07 00 01 00 90 01 CC 04 24 8B 00 09 84 57 FB";
        DinaMessage message = DinaMessage.of(ByteUtil.hexStringToBytes(a.replace(" ", "")));
        BaseStationData baseStationData = parser.parse(message.getData());
        Assertions.assertNotNull(baseStationData);
    }
}