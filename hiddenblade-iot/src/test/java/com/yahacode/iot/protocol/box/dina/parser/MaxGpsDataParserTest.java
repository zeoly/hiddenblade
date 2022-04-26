package com.yahacode.iot.protocol.box.dina.parser;

import com.yahacode.hiddenblade.tool.utils.ByteUtil;
import com.yahacode.iot.protocol.box.dina.DinaMessage;
import com.yahacode.iot.protocol.box.dina.data.GpsData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MaxGpsDataParserTest {

    @InjectMocks
    MaxGpsDataParser parser;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void parse() {
        String a = "FA FA 07 A0 00 12 2E 8B 7D 38 00 B0 05 22 03 00"
                + "1C 16 04 1A 01 BD CE 4A 07 1E 9E 18 00 00 3D 00"
                + "69 01 86 0C 1B 01 00 84 00 00 00 00 02 9A 26 C0"
                + "22 03 00 1D 16 04 1A 01 BD CE 58 01 1E 9E 40 00"
                + "00 3D 00 69 02 D0 0C 1B 01 00 84 00 00 00 00 02"
                + "79 A6 C0 22 03 00 1E 16 04 1A 01 BD CE 58 07 1E"
                + "9E 60 00 00 3E 00 75 03 DE 0C 1B 01 00 84 00 00"
                + "00 00 02 DA 67 C0 22 03 00 1F 16 04 1A 01 BD CE"
                + "4A 07 1E 9E 70 00 00 3E 00 6F 05 A0 0C 1B 01 00"
                + "84 00 00 00 00 02 B9 E6 C0 22 03 00 21 16 04 1A"
                + "01 BD CE 30 07 1E 9E 80 00 00 3D 00 7C 06 FE 0C"
                + "1B 01 00 84 00 00 00 00 02 9A 27 C0 07 FB";
        DinaMessage message = DinaMessage.of(ByteUtil.hexStringToBytes(a.replace(" ", "")));
        List<GpsData> list = parser.parse(message.getData());
        Assertions.assertNotNull(list);
    }
}