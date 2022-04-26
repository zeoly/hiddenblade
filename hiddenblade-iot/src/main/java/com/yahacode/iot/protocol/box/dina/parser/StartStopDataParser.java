package com.yahacode.iot.protocol.box.dina.parser;

import com.yahacode.iot.protocol.box.dina.data.GpsData;
import com.yahacode.iot.protocol.box.dina.data.StartStopData;
import com.yahacode.iot.protocol.box.dina.data.sub.StartStop;
import org.springframework.beans.BeanUtils;

public class StartStopDataParser extends AbstractDinaParser<StartStopData> {

    private GpsDataParser gpsDataParser = new GpsDataParser();

    @Override
    public StartStopData parse(byte[] data) {
        GpsData gpsData = gpsDataParser.parse(data);
        StartStopData startStopData = new StartStopData();
        BeanUtils.copyProperties(gpsData, startStopData);
        int index = 26 + 1;
        if (bitFlag(data[index], 0)) {
            startStopData.setStartStop(StartStop.START);
        }
        if (bitFlag(data[index], 1)) {
            startStopData.setStartStop(StartStop.STOP);
        }
        index += 2;
        startStopData.setTotalMileage(toInt(data, index, 4));
        index += 4;
        startStopData.setTotalConsumption(toInt(data, index, 4));
        return startStopData;
    }
}
