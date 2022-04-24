package com.yahacode.iot.protocol.box.dina.parser;

import com.yahacode.hiddenblade.tool.utils.MathUtil;
import com.yahacode.iot.protocol.TerminalDataParser;
import com.yahacode.iot.protocol.box.dina.data.GpsData;

import java.time.LocalDateTime;

public class GpsDataParser implements TerminalDataParser<GpsData> {

    @Override
    public GpsData parse(byte[] data) {
        GpsData gpsData = new GpsData();
        gpsData.setTimestamp(getTimestamp(data));
        gpsData.setLatitude(getLatitude(data));
        gpsData.setLongitude(getLongitude(data));
        gpsData.setAltitude(toInt(data[16]) | toInt(data[15]) << 8 | toInt(data[14]) << 16);
        gpsData.setSpeed(getSpeed(data));
        gpsData.setDirection(getDirection(data));
        gpsData.setSatellites(toInt(data[21]));
        gpsData.setGsmSignal(toInt(data[22]));
        gpsData.setAccStatus(data[23] & 0x01);
        gpsData.setErrorLog(getErrorLog(data));
        gpsData.setVoltage(getVoltage(data));
        return gpsData;
    }

    private int toInt(byte data) {
        return data & 0xff;
    }

    private LocalDateTime getTimestamp(byte[] data) {
        return LocalDateTime.of(toInt(data[3]) + 2000, toInt(data[4]) - 1, toInt(data[5]), toInt(data[0]), toInt(data[1]), toInt(data[2])).plusHours(8);
    }

    private Double getLatitude(byte[] data) {
        double latitude = 0.000001 * (toInt(data[9]) | toInt(data[8]) << 8 | toInt(data[7]) << 16 | (data[6] & 0x7F) << 24);
        return MathUtil.precision(latitude, 6);
    }

    private Double getLongitude(byte[] data) {
        double longitude = 0.000001 * (toInt(data[13]) | toInt(data[12]) << 8 | toInt(data[11]) << 16 | (data[10] & 0x7F) << 24);
        return MathUtil.precision(longitude, 6);
    }

    private Double getSpeed(byte[] data) {
        double speed = 0.1 * (toInt(data[18]) | toInt(data[17]) << 8);
        return MathUtil.precision(speed, 1);
    }

    private Integer getDirection(byte[] data) {
        int value = (data[19] & 0x0f) << 8 | toInt(data[20]);
        return new Integer(String.valueOf(value / 10));
    }

    private Integer getErrorLog(byte[] data) {
        int log = 0;
        if (0x80 == (data[23] & 0x80)) {
            log = log + 1;
        }
        if (0x40 == (data[23] & 0x40)) {
            log = log + 2;
        }
        if (0x20 == (data[23] & 0x20)) {
            log = log + 4;
        }
        if (0x10 == (data[23] & 0x10)) {
            log = log + 8;
        }
        return log;
    }

    private Double getVoltage(byte[] data) {
        double voltage = (toInt(data[24]) << 8 | toInt(data[25])) * 0.1;
        return MathUtil.precision(voltage, 1);
    }
}
