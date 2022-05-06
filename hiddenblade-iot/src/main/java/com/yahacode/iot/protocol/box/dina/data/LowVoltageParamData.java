package com.yahacode.iot.protocol.box.dina.data;

public class LowVoltageParamData {

    Boolean enable;

    Double alertVoltage;

    public Boolean getEnable() {
        return enable;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }

    public Double getAlertVoltage() {
        return alertVoltage;
    }

    public void setAlertVoltage(Double alertVoltage) {
        this.alertVoltage = alertVoltage;
    }
}
