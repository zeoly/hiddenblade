package com.yahacode.iot.protocol.box.dina.parser;

import com.yahacode.iot.protocol.box.dina.data.IdlingParamData;

public class IdlingParamDataParser extends AbstractDinaParser<IdlingParamData> {

    @Override
    public IdlingParamData parse(byte[] data) {
        IdlingParamData idlingParamData = new IdlingParamData();
        idlingParamData.setEnable(toInt(data[0]) == 1);
        idlingParamData.setAlertTime(toInt(data, 1, 2));
        return idlingParamData;
    }
}
