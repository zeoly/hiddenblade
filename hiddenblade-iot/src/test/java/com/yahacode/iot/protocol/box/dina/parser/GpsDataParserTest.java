package com.yahacode.iot.protocol.box.dina.parser;

import com.yahacode.hiddenblade.tool.utils.ByteUtil;
import com.yahacode.hiddenblade.tool.utils.StringUtil;
import com.yahacode.iot.protocol.box.dina.DinaMessage;
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
        String a = "FA FA 67 00 00 11 03 7F 07 C4 00 22 05 0F 2E 16"
                + "04 15 01 58 2B 00 06 CD 61 48 00 00 8E 00 00 04"
                + "9C 04 16 00 00 7E 00 00 00 00 00 00 00 00 22 FB";
        DinaMessage message = DinaMessage.of(ByteUtil.hexStringToBytes(a.replace(" ", "")));
        GpsData gpsData = parser.parse(message.getData());
        Assertions.assertNotNull(gpsData);

        String b = "FA FA 37 20 00 11 03 7F 07 C4 00 2C 7E 00 03 16"
                + "0F 16 05 1A 01 58 FC 8A 06 CD D7 18 00 00 00 00"
                + "00 00 00 04 13 00 00 00 00 00 00 00 00 00 00 00"
                + "00 00 00 00 00 00 00 00 C4 FB";
        byte[] bytes = ByteUtil.hexStringToBytes(b.replace(" ", ""));
        byte b1 = ByteUtil.parityCheck(bytes, bytes.length - 3);
        System.out.println(StringUtil.toHex2(b1));
    }
}