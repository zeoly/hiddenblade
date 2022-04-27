package com.yahacode.iot.protocol.box.dina.parser;

import com.yahacode.hiddenblade.tool.utils.ByteUtil;
import com.yahacode.iot.protocol.box.dina.data.CanData;

import java.util.LinkedList;
import java.util.List;

public class MaxCanDataParser extends AbstractDinaParser<List<CanData>> {

    CanDataParser canDataParser = new CanDataParser();

    @Override
    public List<CanData> parse(byte[] data) {
        List<CanData> list = new LinkedList<>();
        int packetNumber = toInt(data[0]);
        int index = 1;
        for (int i = 0; i < packetNumber; i++) {
            int packetLength = toInt(data[index]);
            index++;
            list.addAll(canDataParser.parse(ByteUtil.subBytes(data, index, packetLength)));
            index += packetLength;
        }
        return list;
    }
}
