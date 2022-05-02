package com.yahacode.iot.protocol.box.dina.data;

public class SharpTurnSceneData extends DrivingSceneData {

    Double directionRange;

    Double lowSpeed;

    public Double getDirectionRange() {
        return directionRange;
    }

    public void setDirectionRange(Double directionRange) {
        this.directionRange = directionRange;
    }

    public Double getLowSpeed() {
        return lowSpeed;
    }

    public void setLowSpeed(Double lowSpeed) {
        this.lowSpeed = lowSpeed;
    }
}
