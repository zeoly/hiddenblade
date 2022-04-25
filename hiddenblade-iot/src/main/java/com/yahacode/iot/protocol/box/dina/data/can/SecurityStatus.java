package com.yahacode.iot.protocol.box.dina.data.can;

public class SecurityStatus {

    Boolean acc;

    Boolean lock;

    Boolean arming;

    Boolean parkingBrake;

    public Boolean isAcc() {
        return acc;
    }

    public void setAcc(Boolean acc) {
        this.acc = acc;
    }

    public Boolean isLock() {
        return lock;
    }

    public void setLock(Boolean lock) {
        this.lock = lock;
    }

    public Boolean isArming() {
        return arming;
    }

    public void setArming(Boolean arming) {
        this.arming = arming;
    }

    public Boolean isParkingBrake() {
        return parkingBrake;
    }

    public void setParkingBrake(Boolean parkingBrake) {
        this.parkingBrake = parkingBrake;
    }
}
