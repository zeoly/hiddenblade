package com.yahacode.iot.protocol.box.dina.parser;

import com.yahacode.iot.protocol.box.dina.data.HeartbeatParamData;

public class HeartbeatParamDataParser extends AbstractDinaParser<HeartbeatParamData> {

    @Override
    public HeartbeatParamData parse(byte[] data) {
        HeartbeatParamData heartbeatParamData = new HeartbeatParamData();
        heartbeatParamData.setOnInterval(toInt(data, 0, 2));
        heartbeatParamData.setOffInterval(toInt(data, 2, 2));
        if (data.length > 4) {
            heartbeatParamData.setOffIntervalMinute(toInt(data, 4, 2));
        }
        return heartbeatParamData;
    }
}
