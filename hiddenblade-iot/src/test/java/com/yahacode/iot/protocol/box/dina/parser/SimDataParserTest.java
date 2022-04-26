package com.yahacode.iot.protocol.box.dina.parser;

import com.yahacode.hiddenblade.tool.utils.ByteUtil;
import com.yahacode.iot.protocol.box.dina.DinaMessage;
import com.yahacode.iot.protocol.box.dina.data.SimData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

class SimDataParserTest {

    @InjectMocks
    SimDataParser parser;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void parse() {
        String a = "FA FA 1B 07 00 11 03 7F 07 C4 00 37 34 36 30 30"
                + "34 32 36 33 31 34 32 30 38 37 31 38 39 38 36 30"
                + "34 30 36 31 39 32 30 37 30 30 30 30 38 37 31 00"
                + "00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00"
                + "00 00 00 B3 FE";
        DinaMessage message = DinaMessage.of(ByteUtil.hexStringToBytes(a.replace(" ", "")));
        SimData simData = parser.parse(message.getData());
        Assertions.assertNotNull(simData);
    }
}