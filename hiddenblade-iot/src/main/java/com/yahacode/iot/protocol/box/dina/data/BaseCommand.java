package com.yahacode.iot.protocol.box.dina.data;

public class BaseCommand {

    String deviceId;

    public BaseCommand(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }
}
