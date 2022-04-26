package com.yahacode.iot.protocol.box.dina.parser;

import com.yahacode.iot.protocol.box.dina.data.DeviceVerData;

public class DeviceVerDataParser extends AbstractDinaParser<DeviceVerData> {

    @Override
    public DeviceVerData parse(byte[] data) {
        DeviceVerData deviceVerData = new DeviceVerData();
        int index = 0;
        deviceVerData.setSoftwareVer(toString(data, index, 12));
        index += 12;
        deviceVerData.setSoftwareDate(toString(data, index, 10));
        index += 10;
        deviceVerData.setHardwareVer(toString(data, index, 8));
        index += 8;
        deviceVerData.setGsmType(toString(data, index, 8));
        index += 8;
        deviceVerData.setBootloaderVer(toString(data, index, 8));
        if (data.length > 46) {
            index += 8;
            deviceVerData.setRebootTimes(toInt(data, index, 2));
            index += 2;
            deviceVerData.setImei(toString(data, index, 15));
        }
        return deviceVerData;
    }
}
