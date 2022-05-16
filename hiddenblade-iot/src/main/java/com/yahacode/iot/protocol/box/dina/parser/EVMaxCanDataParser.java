package com.yahacode.iot.protocol.box.dina.parser;

import com.yahacode.hiddenblade.tool.utils.ByteUtil;
import com.yahacode.iot.protocol.box.dina.data.EVCanData;

import java.util.LinkedList;
import java.util.List;

public class EVMaxCanDataParser extends AbstractDinaParser<List<EVCanData>> {

    private EVCanDataParser parser = new EVCanDataParser();

    @Override
    public List<EVCanData> parse(byte[] data) {
        List<EVCanData> list = new LinkedList<>();
        int packetNumber = toInt(data[0]);
        int index = 1;
        for (int i = 0; i < packetNumber; i++) {
            int packetLength = toInt(data[index]);
            index++;
            list.addAll(parser.parse(ByteUtil.subBytes(data, index, packetLength)));
            index += packetLength;
        }
        return list;
    }
}
