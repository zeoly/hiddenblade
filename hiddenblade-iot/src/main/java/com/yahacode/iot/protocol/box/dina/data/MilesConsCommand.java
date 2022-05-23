package com.yahacode.iot.protocol.box.dina.data;

public class MilesConsCommand extends BaseCommand {

    Long InitMileage = 0L;

    Long InitFuelConsumption = 0L;

    public MilesConsCommand() {
    }

    public MilesConsCommand(String deviceId) {
        super(deviceId);
    }

    public Long getInitMileage() {
        return InitMileage;
    }

    public void setInitMileage(Long initMileage) {
        InitMileage = initMileage;
    }

    public Long getInitFuelConsumption() {
        return InitFuelConsumption;
    }

    public void setInitFuelConsumption(Long initFuelConsumption) {
        InitFuelConsumption = initFuelConsumption;
    }
}
