package com.yahacode.iot.protocol.box.dina.parser;

import com.yahacode.hiddenblade.tool.utils.ByteUtil;
import com.yahacode.iot.protocol.box.dina.DinaMessage;
import com.yahacode.iot.protocol.box.dina.data.DeviceVerData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

class DeviceVerDataParserTest {

    @InjectMocks
    DeviceVerDataParser parser;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void parse() {
        String a = "FA FA E7 03 00 11 03 7F 07 C4 00 2E 54 49 44 41"
                + "47 31 30 33 56 39 35 35 32 30 32 30 2D 30 39 2D"
                + "30 39 47 49 44 34 2E 30 20 20 46 37 31 33 33 63"
                + "63 20 42 54 4C 44 56 34 30 30 1A FB";
        DinaMessage message = DinaMessage.of(ByteUtil.hexStringToBytes(a.replace(" ", "")));
        DeviceVerData deviceVerData = parser.parse(message.getData());
        Assertions.assertNotNull(deviceVerData);
    }
}