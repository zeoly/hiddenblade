package com.yahacode.iot.protocol.box.dina.parser;

import com.yahacode.iot.protocol.box.dina.data.GprsParamData;

import java.util.LinkedList;
import java.util.List;

public class GprsParamDataParser extends AbstractDinaParser<List<GprsParamData>> {

    @Override
    public List<GprsParamData> parse(byte[] data) {
        List<GprsParamData> list = new LinkedList<>();
        int number = toInt(data[0]);
        String all = toString(data, 1, data.length - 1);
        String[] array = all.split(",");
        for (int i = 0; i < number; i += 8) {
            GprsParamData gprsParamData = new GprsParamData();
            gprsParamData.setIndex(array[i]);
            gprsParamData.setType(array[i + 1]);
            gprsParamData.setAddress(array[i + 2]);
            gprsParamData.setPort(array[i + 3]);
            gprsParamData.setDns(array[i + 4]);
            gprsParamData.setApn(array[i + 5]);
            gprsParamData.setUsername(array[i + 6]);
            gprsParamData.setPassword(array[i + 7]);
            list.add(gprsParamData);
        }
        return list;
    }
}
