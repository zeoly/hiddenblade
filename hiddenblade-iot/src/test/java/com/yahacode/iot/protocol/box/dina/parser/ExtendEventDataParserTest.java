package com.yahacode.iot.protocol.box.dina.parser;

import com.yahacode.hiddenblade.tool.utils.ByteUtil;
import com.yahacode.iot.protocol.box.dina.DinaMessage;
import com.yahacode.iot.protocol.box.dina.data.ExtendEventData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

class ExtendEventDataParserTest {

    @InjectMocks
    ExtendEventDataParser parser;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void parse() {
        String a = "FA FA 11 9E 00 11 03 7F 07 C4 00 28 04 28 1A 16"
                + "04 1D 01 58 2E 5E 06 CD 62 08 00 00 4C 00 00 0C"
                + "80 08 00 00 00 7E 01 00 02 00 02 00 07 01 00 0C"
                + "00 04 00 09 08 FB";
        String b = "FA FA 05 9E 00 11 03 7F 07 C4 00 28 02 2F 08 16"
                + "04 1D 01 58 2C 6A 06 CD 65 C0 00 00 54 00 00 00"
                + "00 0A 00 00 00 7E 01 00 02 00 02 00 07 01 00 09"
                + "00 03 00 14 7F FB";
        DinaMessage message = DinaMessage.of(ByteUtil.hexStringToBytes(b.replace(" ", "")));
        ExtendEventData extendEventData = parser.parse(message.getData());
        Assertions.assertNotNull(extendEventData);
    }
}