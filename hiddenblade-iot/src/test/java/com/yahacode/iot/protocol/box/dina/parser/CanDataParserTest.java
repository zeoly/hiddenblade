package com.yahacode.iot.protocol.box.dina.parser;

import com.yahacode.hiddenblade.tool.utils.ByteUtil;
import com.yahacode.iot.protocol.box.dina.DinaMessage;
import com.yahacode.iot.protocol.box.dina.data.CanData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import java.util.List;

class CanDataParserTest {

    @InjectMocks
    CanDataParser parser;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void parse() {
        String a = "FA FA 87 01 00 11 03 7F 07 C4 00 36 16 38 24 16"
                + "04 18 00 7F D7 FC 03 83 01 00 29 00 85 03 4B 07"
                + "50 92 43 23 64 00 79 00 DF 04 45 00 00 00 00 02"
                + "1D 11 03 EF 03 3E 00 8E 0F 30 00 07 B1 E9 00 00"
                + "95 E7 4A FB";
        DinaMessage message = DinaMessage.of(ByteUtil.hexStringToBytes(a.replace(" ", "")));
        List<CanData> list = parser.parse(message.getData());
        Assertions.assertNotNull(list);
    }
}