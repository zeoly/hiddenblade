package com.yahacode.iot.protocol.box.dina.data;

public class AlertData extends GpsData {

    Boolean collision;

    Boolean towing;

    Boolean rollover;

    Boolean lowVoltage;

    Boolean collisionTest;

    Boolean collisionVideo;

    Boolean collisionFile;

    Boolean start;

    Boolean end;

    Boolean plugOut;

    Boolean plugIn;

    Boolean positionOvertime;

    Boolean idleOvertime;

    Boolean button;

    Boolean flashFault;

    Boolean canFault;

    Boolean sensorFault;

    Boolean gpsFault;

    Double accelerationX;

    Double accelerationY;

    Double accelerationZ;

    Integer idleDuration;

    public Boolean getCollision() {
        return collision;
    }

    public void setCollision(Boolean collision) {
        this.collision = collision;
    }

    public Boolean getTowing() {
        return towing;
    }

    public void setTowing(Boolean towing) {
        this.towing = towing;
    }

    public Boolean getRollover() {
        return rollover;
    }

    public void setRollover(Boolean rollover) {
        this.rollover = rollover;
    }

    public Boolean getLowVoltage() {
        return lowVoltage;
    }

    public void setLowVoltage(Boolean lowVoltage) {
        this.lowVoltage = lowVoltage;
    }

    public Boolean getCollisionTest() {
        return collisionTest;
    }

    public void setCollisionTest(Boolean collisionTest) {
        this.collisionTest = collisionTest;
    }

    public Boolean getCollisionVideo() {
        return collisionVideo;
    }

    public void setCollisionVideo(Boolean collisionVideo) {
        this.collisionVideo = collisionVideo;
    }

    public Boolean getCollisionFile() {
        return collisionFile;
    }

    public void setCollisionFile(Boolean collisionFile) {
        this.collisionFile = collisionFile;
    }

    public Boolean getStart() {
        return start;
    }

    public void setStart(Boolean start) {
        this.start = start;
    }

    public Boolean getEnd() {
        return end;
    }

    public void setEnd(Boolean end) {
        this.end = end;
    }

    public Boolean getPlugOut() {
        return plugOut;
    }

    public void setPlugOut(Boolean plugOut) {
        this.plugOut = plugOut;
    }

    public Boolean getPlugIn() {
        return plugIn;
    }

    public void setPlugIn(Boolean plugIn) {
        this.plugIn = plugIn;
    }

    public Boolean getPositionOvertime() {
        return positionOvertime;
    }

    public void setPositionOvertime(Boolean positionOvertime) {
        this.positionOvertime = positionOvertime;
    }

    public Boolean getIdleOvertime() {
        return idleOvertime;
    }

    public void setIdleOvertime(Boolean idleOvertime) {
        this.idleOvertime = idleOvertime;
    }

    public Boolean getButton() {
        return button;
    }

    public void setButton(Boolean button) {
        this.button = button;
    }

    public Boolean getFlashFault() {
        return flashFault;
    }

    public void setFlashFault(Boolean flashFault) {
        this.flashFault = flashFault;
    }

    public Boolean getCanFault() {
        return canFault;
    }

    public void setCanFault(Boolean canFault) {
        this.canFault = canFault;
    }

    public Boolean getSensorFault() {
        return sensorFault;
    }

    public void setSensorFault(Boolean sensorFault) {
        this.sensorFault = sensorFault;
    }

    public Boolean getGpsFault() {
        return gpsFault;
    }

    public void setGpsFault(Boolean gpsFault) {
        this.gpsFault = gpsFault;
    }

    public Double getAccelerationX() {
        return accelerationX;
    }

    public void setAccelerationX(Double accelerationX) {
        this.accelerationX = accelerationX;
    }

    public Double getAccelerationY() {
        return accelerationY;
    }

    public void setAccelerationY(Double accelerationY) {
        this.accelerationY = accelerationY;
    }

    public Double getAccelerationZ() {
        return accelerationZ;
    }

    public void setAccelerationZ(Double accelerationZ) {
        this.accelerationZ = accelerationZ;
    }

    public Integer getIdleDuration() {
        return idleDuration;
    }

    public void setIdleDuration(Integer idleDuration) {
        this.idleDuration = idleDuration;
    }
}
