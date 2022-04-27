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

class MaxCanDataParserTest {

    @InjectMocks
    MaxCanDataParser parser;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void parse() {
        String a = "FA FA 0E A1 00 12 2E 8E 7B 1E 01 0F 05 35 00 38"
                + "2F 16 04 1B 00 FF D7 BC 03 83 01 00 00 06 00 84"
                + "03 5E 17 4F 7D 45 24 64 00 98 00 8D 02 27 00 00"
                + "00 00 1C 03 8A 02 8F 09 D3 80 C0 00 28 9B D6 00"
                + "02 B1 06 35 00 39 13 16 04 1B 00 FF D7 BC 03 83"
                + "01 00 00 06 00 84 02 83 09 50 7E 45 43 64 00 81"
                + "00 8D 02 47 00 00 00 00 26 03 92 03 3E 09 D3 80"
                + "C0 00 28 9C 2D 00 02 B1 10 35 00 39 34 16 04 1B"
                + "00 FF D7 BC 03 83 01 00 00 06 00 83 02 B4 08 52"
                + "7F 45 56 64 00 95 00 91 02 67 00 00 00 00 37 03"
                + "B1 01 EF 09 D3 80 C0 00 28 9C 47 00 02 B1 1B 35"
                + "00 3A 17 16 04 1B 00 FF D7 BC 03 83 01 00 00 96"
                + "00 83 03 2E 02 53 7F 45 2F 64 00 9C 00 8D 02 87"
                + "00 00 00 00 2C 03 A9 02 E4 09 D3 84 A8 00 28 9C"
                + "83 00 02 B1 26 35 00 3A 37 16 04 1B 00 FF D7 BC"
                + "03 83 01 00 00 06 00 82 03 46 03 54 7F 46 2A 64"
                + "00 A0 00 91 02 A7 00 00 00 00 23 03 99 03 16 09"
                + "D3 84 A8 00 28 9C C3 00 02 B1 34 AF FB";
        DinaMessage message = DinaMessage.of(ByteUtil.hexStringToBytes(a.replace(" ", "")));
        List<CanData> list = parser.parse(message.getData());
        Assertions.assertNotNull(list);
    }
}