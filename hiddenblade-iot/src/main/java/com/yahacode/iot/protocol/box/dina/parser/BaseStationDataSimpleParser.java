package com.yahacode.iot.protocol.box.dina.parser;

import com.yahacode.hiddenblade.tool.utils.MathUtil;
import com.yahacode.iot.protocol.box.dina.data.BaseStationData;

public class BaseStationDataSimpleParser extends AbstractDinaParser<BaseStationData> {

    private GpsDataParser gpsDataParser = new GpsDataParser();

    @Override
    public BaseStationData parse(byte[] data) {
        BaseStationData baseStationData = new BaseStationData();
        baseStationData.setTimestamp(getTimestamp(data));
        baseStationData.setAccStatus(data[6] & 0x01);
        baseStationData.setErrorLog(gpsDataParser.getErrorLog(data[6]));
        baseStationData.setSpeed((double) toInt(data[7]));
        baseStationData.setVoltage(MathUtil.precision(0.1 * toInt(data, 8, 2), 1));
        baseStationData.setMobileCountryCode(toInt(data, 10, 2));
        baseStationData.setMobileNetworkCode(toInt(data[12]));
        if (data.length == 19) {
            baseStationData.setSystemIdentity(toInt(data, 13, 2));
            baseStationData.setNetworkIdentity(toInt(data, 15, 2));
            baseStationData.setBillingIdentity(toInt(data, 17, 2));
        } else {
            baseStationData.setLocationAreaCode(toInt(data, 13, 2));
            baseStationData.setCellIdentity(toInt(data, 15, 3));
        }
        return baseStationData;
    }
}
