package com.yahacode.iot.protocol.box.dina.parser;

import com.yahacode.iot.protocol.box.dina.data.SimData;

public class SimDataParser extends AbstractDinaParser<SimData> {

    @Override
    public SimData parse(byte[] data) {
        SimData simData = new SimData();
        int index = 0;
        simData.setImsi(toString(data, index, 15));
        index += 15;
        simData.setIccid(toString(data, index, 20));
        index += 20;
        simData.setSimNo(toString(data, index, 20));
        return simData;
    }
}
