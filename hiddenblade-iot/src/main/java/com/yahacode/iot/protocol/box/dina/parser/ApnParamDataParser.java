package com.yahacode.iot.protocol.box.dina.parser;

import com.yahacode.iot.protocol.box.dina.data.ApnParamData;

public class ApnParamDataParser extends AbstractDinaParser<ApnParamData> {

    @Override
    public ApnParamData parse(byte[] data) {
        ApnParamData apnParamData = new ApnParamData();
        String all = toString(data, 0, data.length);
        String[] array = all.split(",");
        apnParamData.setApn(array[0]);
        apnParamData.setUsername(array[1]);
        apnParamData.setPassword(array[2]);
        return apnParamData;
    }
}
