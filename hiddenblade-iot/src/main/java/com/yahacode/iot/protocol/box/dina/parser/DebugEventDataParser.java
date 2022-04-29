package com.yahacode.iot.protocol.box.dina.parser;

import com.yahacode.hiddenblade.tool.utils.ByteUtil;
import com.yahacode.iot.protocol.box.dina.data.DebugEventData;
import com.yahacode.iot.protocol.box.dina.data.GpsData;
import org.springframework.beans.BeanUtils;

public class DebugEventDataParser extends AbstractDinaParser<DebugEventData> {

    GpsDataParser gpsDataParser = new GpsDataParser();

    @Override
    public DebugEventData parse(byte[] data) {
        DebugEventData debugEventData = new DebugEventData();
        GpsData gpsData = gpsDataParser.parse(data);
        BeanUtils.copyProperties(gpsData, debugEventData);
        int index = 26;
        debugEventData.setDebugType(toInt(data[index]));
        index++;
        debugEventData.setLength(toInt(data, index, 2));
        index += 2;
        debugEventData.setContent(ByteUtil.bytesToHexString(ByteUtil.subBytes(data, index, debugEventData.getLength())));
        return debugEventData;
    }
}
