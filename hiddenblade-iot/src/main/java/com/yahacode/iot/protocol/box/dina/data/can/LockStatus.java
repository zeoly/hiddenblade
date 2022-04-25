package com.yahacode.iot.protocol.box.dina.data.can;

public class LockStatus {

    Boolean leftFront;

    Boolean rightFront;

    Boolean leftBack;

    Boolean rightBack;

    Boolean truck;

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

    public Boolean getTruck() {
        return truck;
    }

    public void setTruck(Boolean truck) {
        this.truck = truck;
    }
}
