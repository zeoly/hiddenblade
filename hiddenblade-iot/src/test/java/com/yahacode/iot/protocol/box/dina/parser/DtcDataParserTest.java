package com.yahacode.iot.protocol.box.dina.parser;

import com.yahacode.hiddenblade.tool.utils.ByteUtil;
import com.yahacode.iot.protocol.box.dina.DinaMessage;
import com.yahacode.iot.protocol.box.dina.data.DtcData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

class DtcDataParserTest {

    @InjectMocks
    DtcDataParser parser;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void parse() {
        String a = "FA FA 4C 02 00 0B 6E 3B 02 65 00 0C 02 36 2E 16"
                + "04 1A 01 7F 00 7F 21 10 59 FB";
        DinaMessage message = DinaMessage.of(ByteUtil.hexStringToBytes(a.replace(" ", "")));
        DtcData dtcData = parser.parse(message.getData());
        Assertions.assertNotNull(dtcData);
    }
}