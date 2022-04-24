package com.yahacode.iot.protocol.box.dina.parser;

import com.yahacode.hiddenblade.tool.utils.ByteUtil;
import com.yahacode.iot.protocol.box.dina.data.GpsData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

class GpsDataParserTest {

    @InjectMocks
    GpsDataParser parser;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void parse() {
        String a = "08 15 2A 16 04 09 01 58 09 36 06 CC AF 00 00 00 74 00 00 06 AE 04 16 01 00 8A 00 00 00 00 00 00 00 00";
        GpsData gpsData = parser.parse(ByteUtil.hexStringToBytes(a.replace(" ", "")));
        Assertions.assertNotNull(gpsData);
    }
}