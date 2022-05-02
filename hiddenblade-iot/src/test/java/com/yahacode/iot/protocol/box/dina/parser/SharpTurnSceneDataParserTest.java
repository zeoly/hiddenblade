package com.yahacode.iot.protocol.box.dina.parser;

import com.yahacode.hiddenblade.tool.utils.ByteUtil;
import com.yahacode.iot.protocol.box.dina.DinaMessage;
import com.yahacode.iot.protocol.box.dina.data.SharpTurnSceneData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

class SharpTurnSceneDataParserTest {

    @InjectMocks
    SharpTurnSceneDataParser parser;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void parse() {
        String a = "FA FA 09 AA 00 12 2E 8B 7B D5 01 89 02 01 03 16" +
                "04 1B 11 A5 01 E6 B4 16 07 28 DA C0 03 02 0D FC" +
                "06 78 01 E6 B4 D2 07 28 DA B0 02 F8 0D FC 06 67" +
                "01 E6 B5 90 07 28 DA B0 02 EE 0E 06 06 45 01 E6" +
                "B6 4C 07 28 DA B0 02 DA 0E 06 06 36 01 E6 B7 02" +
                "07 28 DA B0 02 B2 0E 06 05 D9 01 E6 B7 BA 07 28" +
                "DA B0 02 62 0D F2 05 4D 01 E6 B8 6A 07 28 DA A0" +
                "02 26 0D FC 04 CD 01 E6 B9 08 07 28 DA A0 01 D6" +
                "00 00 04 3F 01 E6 B9 96 07 28 DA B0 01 C2 00 28" +
                "04 C6 01 E6 BA 10 07 28 DA C0 01 AE 00 64 06 22" +
                "01 E6 BA 10 07 28 DA C0 01 AE 00 64 06 22 01 E6" +
                "BA 7A 07 28 DA E8 01 B8 00 F0 06 22 01 E6 BA D6" +
                "07 28 DB 20 01 CC 01 FE 06 58 01 E6 BB 26 07 28" +
                "DB 88 01 E0 01 FE 06 BA 01 E6 BB 5E 07 28 DB F8" +
                "01 FE 02 DA 07 18 01 E6 BB A0 07 28 DC F8 02 26" +
                "03 0C 07 92 01 E6 BB AE 07 28 DD 88 02 44 03 66" +
                "07 FD 01 E6 BB B4 07 28 DE 28 02 62 03 8E 08 5C" +
                "01 E6 BB AE 07 28 DE D0 02 6C 03 7A 98 B7 01 E6" +
                "BB B4 07 28 DF 80 02 80 03 70 06 D8 01 E6 BB B8" +
                "07 28 E0 38 02 6C 03 70 06 42 01 E6 BB BC 07 28" +
                "E0 E8 02 4E 03 70 05 0B 01 E6 BB C0 07 28 E1 B0" +
                "02 3A 03 70 04 CF 01 E6 BB C0 07 28 E2 50 02 1C" +
                "03 7A 04 EF 01 E6 BB C4 07 28 E3 08 01 E0 03 70" +
                "05 47 00 03 00 09 00 00 00 07 00 09 00 0E 00 03" +
                "00 13 00 09 00 06 00 0A 00 00 00 07 00 0E 00 09" +
                "04 02 76 01 AE CE FB";
        DinaMessage message = DinaMessage.of(ByteUtil.hexStringToBytes(a.replace(" ", "")));
        SharpTurnSceneData sharpTurnSceneData = parser.parse(message.getData());
        Assertions.assertNotNull(sharpTurnSceneData);
    }
}