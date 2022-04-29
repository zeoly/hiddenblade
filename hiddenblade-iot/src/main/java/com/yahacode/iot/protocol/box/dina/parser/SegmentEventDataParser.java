package com.yahacode.iot.protocol.box.dina.parser;

import com.yahacode.iot.protocol.box.dina.data.GpsData;
import com.yahacode.iot.protocol.box.dina.data.SegmentEventData;
import com.yahacode.iot.protocol.box.dina.data.sub.SegmentType;
import org.springframework.beans.BeanUtils;

public class SegmentEventDataParser extends AbstractDinaParser<SegmentEventData> {

    private GpsDataParser gpsDataParser = new GpsDataParser();

    @Override
    public SegmentEventData parse(byte[] data) {
        GpsData gpsData = gpsDataParser.parse(data);
        SegmentEventData startStopData = new SegmentEventData();
        BeanUtils.copyProperties(gpsData, startStopData);
        int index = 26 + 1;
        if (bitFlag(data[index], 0)) {
            startStopData.setType(SegmentType.START);
        }
        if (bitFlag(data[index], 1)) {
            startStopData.setType(SegmentType.STOP);
        }
        index += 2;
        startStopData.setTotalMileage(toLong(data, index, 4));
        index += 4;
        startStopData.setTotalConsumption(toLong(data, index, 4));
        return startStopData;
    }
}
