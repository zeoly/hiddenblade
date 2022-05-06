package com.yahacode.iot.protocol.box.dina.data;

public class HeartbeatParamData {

    Integer onInterval;

    Integer offInterval;

    Integer offIntervalMinute;

    public Integer getOnInterval() {
        return onInterval;
    }

    public void setOnInterval(Integer onInterval) {
        this.onInterval = onInterval;
    }

    public Integer getOffInterval() {
        return offInterval;
    }

    public void setOffInterval(Integer offInterval) {
        this.offInterval = offInterval;
    }

    public Integer getOffIntervalMinute() {
        return offIntervalMinute;
    }

    public void setOffIntervalMinute(Integer offIntervalMinute) {
        this.offIntervalMinute = offIntervalMinute;
    }
}
