package com.yahacode.iot.protocol.box.dina.parser;

import com.yahacode.hiddenblade.tool.utils.ByteUtil;
import com.yahacode.iot.protocol.box.dina.DinaMessage;
import com.yahacode.iot.protocol.box.dina.data.DebugEventData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

class DebugEventDataParserTest {

    @InjectMocks
    DebugEventDataParser parser;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void parse() {
        String a = "FA FA 22 9D 00 11 03 7F 07 C4 00 26 06 20 39 16"
                + "04 1D 01 58 2E 6E 06 CD 61 D8 00 00 47 00 00 0C"
                + "80 04 00 00 00 7E 09 00 09 20 22 04 29 14 32 57"
                + "FF 01 5D FB";
        DinaMessage message = DinaMessage.of(ByteUtil.hexStringToBytes(a.replace(" ", "")));
        DebugEventData debugEventData = parser.parse(message.getData());
        Assertions.assertNotNull(debugEventData);
    }
}