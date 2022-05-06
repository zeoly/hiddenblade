package com.yahacode.iot.protocol.box.dina.parser;

import com.yahacode.iot.protocol.box.dina.data.CanParamData;
import com.yahacode.iot.protocol.box.dina.data.GpsParamData;
import org.springframework.beans.BeanUtils;

public class CanParamDataParser extends AbstractDinaParser<CanParamData> {

    private GpsParamDataParser gpsParamDataParser = new GpsParamDataParser();

    @Override
    public CanParamData parse(byte[] data) {
        GpsParamData gpsParamData = gpsParamDataParser.parse(data);
        CanParamData canParamData = new CanParamData();
        BeanUtils.copyProperties(gpsParamData, canParamData);
        canParamData.setStatusMask(toInt(data[4]));
        canParamData.setDsMask(toLong(data, 5, 4));
        return canParamData;
    }
}
