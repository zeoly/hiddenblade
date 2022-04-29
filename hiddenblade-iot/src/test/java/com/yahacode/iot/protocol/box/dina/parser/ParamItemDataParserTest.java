package com.yahacode.iot.protocol.box.dina.parser;

import com.yahacode.hiddenblade.tool.utils.ByteUtil;
import com.yahacode.iot.protocol.box.dina.DinaMessage;
import com.yahacode.iot.protocol.box.dina.data.ParamItemData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import java.util.List;

class ParamItemDataParserTest {


    @InjectMocks
    ParamItemDataParser parser;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void parse() {
        String a = "FA FA 0A 10 00 11 03 7F 07 C4 00 14 02 00 00 00"
                + "03 01 02 00 05 00 0B 08 00 00 00 00 00 00 00 00"
                + "A4 FB";
        DinaMessage message = DinaMessage.of(ByteUtil.hexStringToBytes(a.replace(" ", "")));
        List<ParamItemData> list = parser.parse(message.getData());
        Assertions.assertNotNull(list);
    }
}