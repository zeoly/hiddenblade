package com.yahacode.iot.protocol.box.dina.data.sub;

public class DoorStatus {

    Boolean leftFront;

    Boolean rightFront;

    Boolean leftBack;

    Boolean rightBack;

    Boolean trunk;

    public Boolean getLeftFront() {
        return leftFront;
    }

    public void setLeftFront(Boolean leftFront) {
        this.leftFront = leftFront;
    }

    public Boolean getRightFront() {
        return rightFront;
    }

    public void setRightFront(Boolean rightFront) {
        this.rightFront = rightFront;
    }

    public Boolean getLeftBack() {
        return leftBack;
    }

    public void setLeftBack(Boolean leftBack) {
        this.leftBack = leftBack;
    }

    public Boolean getRightBack() {
        return rightBack;
    }

    public void setRightBack(Boolean rightBack) {
        this.rightBack = rightBack;
    }

    public Boolean getTrunk() {
        return trunk;
    }

    public void setTrunk(Boolean trunk) {
        this.trunk = trunk;
    }
}
