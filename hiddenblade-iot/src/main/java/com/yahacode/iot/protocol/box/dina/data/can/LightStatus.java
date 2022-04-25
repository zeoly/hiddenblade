package com.yahacode.iot.protocol.box.dina.data.can;

public class LightStatus {

    Boolean highBeam;

    Boolean lowBeam;

    Boolean leftSignal;

    Boolean rightSignal;

    Boolean reversingLight;

    Boolean brakeLight;

    Boolean fogLight;

    Boolean clearanceLight;

    public Boolean getHighBeam() {
        return highBeam;
    }

    public void setHighBeam(Boolean highBeam) {
        this.highBeam = highBeam;
    }

    public Boolean getLowBeam() {
        return lowBeam;
    }

    public void setLowBeam(Boolean lowBeam) {
        this.lowBeam = lowBeam;
    }

    public Boolean getLeftSignal() {
        return leftSignal;
    }

    public void setLeftSignal(Boolean leftSignal) {
        this.leftSignal = leftSignal;
    }

    public Boolean getRightSignal() {
        return rightSignal;
    }

    public void setRightSignal(Boolean rightSignal) {
        this.rightSignal = rightSignal;
    }

    public Boolean getReversingLight() {
        return reversingLight;
    }

    public void setReversingLight(Boolean reversingLight) {
        this.reversingLight = reversingLight;
    }

    public Boolean getBrakeLight() {
        return brakeLight;
    }

    public void setBrakeLight(Boolean brakeLight) {
        this.brakeLight = brakeLight;
    }

    public Boolean getFogLight() {
        return fogLight;
    }

    public void setFogLight(Boolean fogLight) {
        this.fogLight = fogLight;
    }

    public Boolean getClearanceLight() {
        return clearanceLight;
    }

    public void setClearanceLight(Boolean clearanceLight) {
        this.clearanceLight = clearanceLight;
    }
}
