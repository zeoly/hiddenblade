package com.yahacode.iot.protocol.box.dina.parser;

import com.yahacode.hiddenblade.tool.utils.ByteUtil;
import com.yahacode.iot.protocol.box.dina.data.GpsData;

import java.util.LinkedList;
import java.util.List;

public class MaxGpsDataParser extends AbstractDinaParser<List<GpsData>> {

    GpsDataParser gpsDataParser = new GpsDataParser();

    @Override
    public List<GpsData> parse(byte[] data) {
        List<GpsData> list = new LinkedList<>();
        int packetNumber = toInt(data[0]);
        int index = 1;
        for (int i = 0; i < packetNumber; i++) {
            int packetLength = toInt(data[index]);
            index++;
            list.add(gpsDataParser.parse(ByteUtil.subBytes(data, index, packetLength)));
            index += packetLength;
        }
        return list;
    }
}
