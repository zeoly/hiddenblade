package com.yahacode.iot.protocol.box.dina.parser;

import com.yahacode.iot.protocol.box.dina.data.ParamItemData;

import java.util.LinkedList;
import java.util.List;

public class ParamItemDataParser extends AbstractDinaParser<List<ParamItemData>> {

    @Override
    public List<ParamItemData> parse(byte[] data) {
        List<ParamItemData> list = new LinkedList<>();
        int length = toInt(data[0]);
        int index = 1;
        for (int i = 0; i < length; i++) {
            ParamItemData paramItemData = new ParamItemData();
            paramItemData.setParamId(toHexString(data, index, 4));
            index += 4;
            paramItemData.setLength(toInt(data[index]));
            index++;
            paramItemData.setContent(toHexString(data, index, paramItemData.getLength()));
            index += paramItemData.getLength();
            list.add(paramItemData);
        }
        return list;
    }
}
