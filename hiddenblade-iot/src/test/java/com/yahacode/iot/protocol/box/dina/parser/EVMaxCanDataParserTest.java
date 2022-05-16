package com.yahacode.iot.protocol.box.dina.parser;

import com.yahacode.hiddenblade.tool.utils.ByteUtil;
import com.yahacode.iot.protocol.box.dina.DinaMessage;
import com.yahacode.iot.protocol.box.dina.data.EVCanData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class EVMaxCanDataParserTest {

    @InjectMocks
    EVMaxCanDataParser parser;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void parse() {
        String a = "FA FA 39 BA 00 0B 6E 3B 07 99 00 1F 01 1D 02 1B"
                + "2D 16 05 10 00 00 20 00 30 00 00 00 00 00 40 01"
                + "98 00 00 00 27 82 1E 00 03 B9 20 9F FB";
        DinaMessage message = DinaMessage.of(ByteUtil.hexStringToBytes(a.replace(" ", "")));
        List<EVCanData> data = parser.parse(message.getData());
        Assertions.assertNotNull(data);
    }
}