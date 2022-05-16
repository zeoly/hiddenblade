package com.yahacode.iot.protocol.box.dina.data.sub;

public class EVSecurityStatus {

    Boolean keyAcc;

    Boolean vehicleSecurity;

    public Boolean getKeyAcc() {
        return keyAcc;
    }

    public void setKeyAcc(Boolean keyAcc) {
        this.keyAcc = keyAcc;
    }

    public Boolean getVehicleSecurity() {
        return vehicleSecurity;
    }

    public void setVehicleSecurity(Boolean vehicleSecurity) {
        this.vehicleSecurity = vehicleSecurity;
    }
}
