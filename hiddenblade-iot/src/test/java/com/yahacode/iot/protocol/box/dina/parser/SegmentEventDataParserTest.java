package com.yahacode.iot.protocol.box.dina.parser;

import com.yahacode.hiddenblade.tool.utils.ByteUtil;
import com.yahacode.iot.protocol.box.dina.DinaMessage;
import com.yahacode.iot.protocol.box.dina.data.SegmentEventData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

class SegmentEventDataParserTest {


    @InjectMocks
    SegmentEventDataParser parser;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void parse() {
        String a = "FA FA DA 97 00 12 2E 8B 74 FA 00 25 03 00 2D 16"
                + "04 1A 01 FE 9D 9A 06 B3 B0 E0 00 00 C9 00 00 03"
                + "3E 0C 00 01 00 8A 00 01 00 01 52 59 7F 00 15 47"
                + "2C 13 FB";
        String b0 = "FA FA 30 97 00 0B 6E 42 A8 53 00 25 02 2D 34 16"
                + "05 10 01 F2 4D 50 06 AE 86 30 00 00 73 00 00 00"
                + "11 04 00 01 00 87 00 01 00 00 00 00 07 00 00 00"
                + "00 50 FB";
        String b1 = "FA FA 36 97 00 0B 6E 42 A8 53 00 25 04 18 0D 16"
                + "05 10 01 F2 08 58 06 AE BD 80 00 00 6D 00 00 00"
                + "C3 17 00 00 00 86 00 02 00 00 00 00 00 00 00 00"
                + "00 41 FB";
        String b2 = "FA FA 38 97 00 0B 6E 42 A8 53 00 25 04 18 10 16"
                + "05 10 01 F2 08 74 06 AE BD 80 00 00 6D 00 00 00"
                + "C3 18 00 01 00 86 00 01 00 00 00 00 00 00 00 00"
                + "00 73 FB";
        DinaMessage message = DinaMessage.of(ByteUtil.hexStringToBytes(b2.replace(" ", "")));
        SegmentEventData startStopData = parser.parse(message.getData());
        Assertions.assertNotNull(startStopData);
    }
}