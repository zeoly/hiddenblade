package com.yahacode.iot.protocol.box.dina.parser;

import com.yahacode.hiddenblade.tool.utils.ByteUtil;
import com.yahacode.iot.protocol.box.dina.DinaMessage;
import com.yahacode.iot.protocol.box.dina.data.VehicleData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

class VehicleInfoParserTest {

    @InjectMocks
    VehicleInfoParser parser;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void parse() {
        String a = "FA FA FC 0A 00 11 03 7F 07 C4 00 29 01 02 03 04"
                + "00 04 4C 46 56 33 41 32 38 57 34 4C 33 37 31 38"
                + "31 31 33 10 01 38 57 30 32 35 39 41 45 30 30 30"
                + "33 42 48 41 48 33 FB";
        DinaMessage message = DinaMessage.of(ByteUtil.hexStringToBytes(a.replace(" ", "")));
        VehicleData vehicleData = parser.parse(message.getData());
        Assertions.assertNotNull(vehicleData);
    }
}