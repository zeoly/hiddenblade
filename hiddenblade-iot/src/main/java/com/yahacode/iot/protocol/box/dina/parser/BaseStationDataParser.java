package com.yahacode.iot.protocol.box.dina.parser;

import com.yahacode.iot.protocol.box.dina.data.BaseStationData;
import com.yahacode.iot.protocol.box.dina.data.GpsData;
import org.springframework.beans.BeanUtils;

public class BaseStationDataParser extends AbstractDinaParser<BaseStationData> {

    private GpsDataParser gpsDataParser = new GpsDataParser();

    @Override
    public BaseStationData parse(byte[] data) {
        GpsData gpsData = gpsDataParser.parse(data);
        BaseStationData baseStationData = new BaseStationData();
        BeanUtils.copyProperties(gpsData, baseStationData);
        int index = 26;
        baseStationData.setMobileCountryCode(toInt(data, index, 2));
        index += 2;
        baseStationData.setMobileNetworkCode(toInt(data[index]));
        index++;
        if (data.length == 35) {
            baseStationData.setSystemIdentity(toInt(data, index, 2));
            index += 2;
            baseStationData.setNetworkIdentity(toInt(data, index, 2));
            index += 2;
            baseStationData.setBillingIdentity(toInt(data, index, 2));
        } else {
            baseStationData.setLocationAreaCode(toInt(data, index, 2));
            index += 2;
            baseStationData.setCellIdentity(toInt(data, index, 3));
        }
        return baseStationData;
    }
}
