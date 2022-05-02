package com.yahacode.iot.protocol.box.dina.parser;

import com.yahacode.hiddenblade.tool.utils.ByteUtil;
import com.yahacode.iot.protocol.box.dina.DinaMessage;
import com.yahacode.iot.protocol.box.dina.data.DrivingSceneData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

class DrivingSceneDataParserTest {

    @InjectMocks
    DrivingSceneDataParser parser;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void parse() {
        String a = "FA FA 1E A8 00 11 03 7F 07 C4 01 49 02 03 0A 16"
                + "05 02 11 A2 01 58 FF 86 06 CD B6 78 02 08 0A 6E"
                + "05 AA 01 58 FF 7E 06 CD B5 E8 02 1C 0A 64 05 BC"
                + "01 58 FF 72 06 CD B5 58 02 1C 0A 64 05 DA 01 58"
                + "FF 5E 06 CD B4 C0 02 1C 0A 50 05 45 01 58 FF 4E"
                + "06 CD B4 30 02 12 0A 46 04 06 01 58 FF 3C 06 CD"
                + "B3 A0 02 08 0A 3C 05 5D 01 58 FF 2C 06 CD B3 10"
                + "01 F4 0A 32 05 3F 01 58 FF 1C 06 CD B2 78 01 E0"
                + "0A 32 04 FB 01 58 FF 10 06 CD B1 F0 01 A4 0A 32"
                + "04 7E 01 58 FF 00 06 CD B1 70 01 68 0A 32 03 C6"
                + "01 58 FF 00 06 CD B1 70 01 68 0A 32 03 C6 01 58"
                + "FE F0 06 CD B0 F0 00 F0 0A 32 04 5E 01 58 FE DE"
                + "06 CD B0 90 00 8C 0A 28 03 6F 01 58 FE D4 06 CD"
                + "B0 30 00 5A 0A 28 03 13 01 58 FE BE 06 CD AF E8"
                + "00 32 0A 28 03 09 01 58 FE AC 06 CD AF A0 00 1E"
                + "0A 28 04 70 01 58 FE B6 06 CD AF A0 00 28 0A 28"
                + "04 93 01 58 FE CC 06 CD AF 88 00 1E 0A 28 04 DB"
                + "01 58 FE D4 06 CD AF 88 00 1E 0A 3C 05 29 01 58"
                + "FE D4 06 CD AF 88 00 46 0A 46 05 98 01 58 FE D6"
                + "06 CD AF 78 00 8C 0A 64 05 DA 01 58 FE DA 06 CD"
                + "AF 58 00 BE 0A 78 06 18 00 04 00 01 00 16 00 64"
                + "00 01 00 01 01 24 FB";
        String b = "FA FA E9 A9 00 11 03 7F 05 93 01 49 03 39 18 16"
                + "05 02 11 A2 01 58 3B AC 06 CA F6 18 00 00 03 E8"
                + "02 D1 01 58 3B AC 06 CA F6 18 00 00 03 E8 02 D1"
                + "01 58 3B AC 06 CA F6 18 00 00 03 E8 62 CC 01 58"
                + "3B AC 06 CA F6 18 00 00 03 E8 02 CA 01 58 3B AC"
                + "06 CA F6 18 00 90 03 E8 02 D2 01 58 3B AC 06 CA"
                + "F6 18 00 00 03 E8 02 CF 01 58 3B AC 06 CA F6 18"
                + "00 00 03 E8 02 D1 01 58 3B AC 06 CA F6 18 00 00"
                + "03 E8 02 CF 01 58 3B AC 06 CA F6 18 00 0A 03 E8"
                + "05 0B 01 58 3B AC 06 CA F6 08 00 32 03 E8 06 ED"
                + "01 58 3B AC 06 CA F6 08 00 32 03 E8 06 ED 01 58"
                + "3B AC 06 CA F6 08 00 A0 03 E8 07 73 01 58 3B AC"
                + "06 CA F6 08 00 DC 03 E8 06 7D 01 58 3B AC 06 CA"
                + "F6 18 01 04 03 E8 06 13 01 58 3B A4 06 CA F6 38"
                + "01 36 03 CA 06 5C 01 58 3B 94 06 CA F6 80 01 5E"
                + "03 C0 05 F9 01 58 3B 84 06 CA F6 B8 01 7C 03 D4"
                + "05 D9 01 58 3B 6E 06 CA F7 20 01 90 03 DE 05 5C"
                + "01 58 3B 5E 06 CA F7 80 01 A4 03 DE 05 11 01 58"
                + "3B 4E 06 CA F7 D8 81 9A 03 CA 95 01 01 58 3B 42"
                + "06 CA F8 50 01 86 03 C0 04 CA 01 58 3B 32 06 CA"
                + "F8 C0 01 54 03 CB 04 AC 00 01 00 01 00 03 00 0A"
                + "00 03 00 04 01 64 FB";
        DinaMessage message = DinaMessage.of(ByteUtil.hexStringToBytes(b.replace(" ", "")));
        DrivingSceneData drivingSceneData = parser.parse(message.getData());
        Assertions.assertNotNull(drivingSceneData);
    }
}