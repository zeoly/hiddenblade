package com.yahacode.iot.protocol.box.dina.parser;

import com.yahacode.hiddenblade.tool.utils.MathUtil;
import com.yahacode.iot.protocol.box.dina.data.GpsData;

public class GpsDataParser extends AbstractDinaParser<GpsData> {

    @Override
    public GpsData parse(byte[] data) {
        GpsData gpsData = new GpsData();
        gpsData.setTimestamp(getTimestamp(data));
        double[] coordinates = toCoordinates(data, 6);
        gpsData.setLatitude(coordinates[1]);
        gpsData.setLongitude(coordinates[0]);
        gpsData.setAltitude(toInt(data, 14, 3));
        gpsData.setSpeed(MathUtil.precision(0.1 * toInt(data, 17, 2), 1));
        gpsData.setDirection(toInt(data, 19, 2) / 10);
        gpsData.setSatellites(toInt(data[21]));
        gpsData.setGsmSignal(toInt(data[22]));
        gpsData.setAccStatus(data[23] & 0x01);
        gpsData.setErrorLog(getErrorLog(data[23]));
        gpsData.setVoltage(MathUtil.precision(0.1 * toInt(data, 24, 2), 1));
        return gpsData;
    }

    public Integer getErrorLog(byte data) {
        int log = 0;
        if (0x80 == (data & 0x80)) {
            log = log + 1;
        }
        if (0x40 == (data & 0x40)) {
            log = log + 2;
        }
        if (0x20 == (data & 0x20)) {
            log = log + 4;
        }
        if (0x10 == (data & 0x10)) {
            log = log + 8;
        }
        return log;
    }
}
