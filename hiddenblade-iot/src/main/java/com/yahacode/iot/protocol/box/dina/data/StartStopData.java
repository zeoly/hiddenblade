package com.yahacode.iot.protocol.box.dina.data;

import com.yahacode.iot.protocol.box.dina.data.sub.StartStop;

public class StartStopData extends GpsData {

    private StartStop startStop;

    Integer totalMileage;

    Integer totalConsumption;

    public StartStop getStartStop() {
        return startStop;
    }

    public void setStartStop(StartStop startStop) {
        this.startStop = startStop;
    }

    public Integer getTotalMileage() {
        return totalMileage;
    }

    public void setTotalMileage(Integer totalMileage) {
        this.totalMileage = totalMileage;
    }

    public Integer getTotalConsumption() {
        return totalConsumption;
    }

    public void setTotalConsumption(Integer totalConsumption) {
        this.totalConsumption = totalConsumption;
    }
}
