package com.yahacode.iot.protocol.box.dina.parser;

import com.yahacode.iot.protocol.box.dina.data.SmsParamData;

import java.util.LinkedList;
import java.util.List;

public class SmsParamDataParser extends AbstractDinaParser<List<SmsParamData>> {

    @Override
    public List<SmsParamData> parse(byte[] data) {
        List<SmsParamData> list = new LinkedList<>();
        int number = toInt(data[0]);
        String all = toString(data, 1, data.length - 1);
        String[] array = all.split(",", -1);
        for (int i = 0; i < number; i += 2) {
            SmsParamData smsParamData = new SmsParamData();
            smsParamData.setIndex(array[i]);
            smsParamData.setSms(array[i + 1]);
            list.add(smsParamData);
        }
        return list;
    }
}
