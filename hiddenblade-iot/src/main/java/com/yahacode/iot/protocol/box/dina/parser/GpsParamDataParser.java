package com.yahacode.iot.protocol.box.dina.parser;

import com.yahacode.iot.protocol.box.dina.data.GpsParamData;

public class GpsParamDataParser extends AbstractDinaParser<GpsParamData> {

    @Override
    public GpsParamData parse(byte[] data) {
        GpsParamData gpsParamData = new GpsParamData();
        gpsParamData.setEnable(toInt(data[0]));
        gpsParamData.setType(toInt(data[1]));
        gpsParamData.setInterval(toInt(data, 2, 2));
        return gpsParamData;
    }
}
