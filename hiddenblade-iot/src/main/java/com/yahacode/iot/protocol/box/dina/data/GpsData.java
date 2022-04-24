package com.yahacode.iot.protocol.box.dina.data;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public class GpsData {

    @JsonFormat
    private LocalDateTime timestamp;

    private Double longitude;

    private Double latitude;

    private Integer altitude;

    private Double speed;

    private Integer direction;

    private Integer satellites;

    private Integer gsmSignal;

    private Integer accStatus;

    private Integer errorLog;

    private Double voltage;

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Integer getAltitude() {
        return altitude;
    }

    public void setAltitude(Integer altitude) {
        this.altitude = altitude;
    }

    public Double getSpeed() {
        return speed;
    }

    public void setSpeed(Double speed) {
        this.speed = speed;
    }

    public Integer getDirection() {
        return direction;
    }

    public void setDirection(Integer direction) {
        this.direction = direction;
    }

    public Integer getSatellites() {
        return satellites;
    }

    public void setSatellites(Integer satellites) {
        this.satellites = satellites;
    }

    public Integer getGsmSignal() {
        return gsmSignal;
    }

    public void setGsmSignal(Integer gsmSignal) {
        this.gsmSignal = gsmSignal;
    }

    public Integer getAccStatus() {
        return accStatus;
    }

    public void setAccStatus(Integer accStatus) {
        this.accStatus = accStatus;
    }

    public Integer getErrorLog() {
        return errorLog;
    }

    public void setErrorLog(Integer errorLog) {
        this.errorLog = errorLog;
    }

    public Double getVoltage() {
        return voltage;
    }

    public void setVoltage(Double voltage) {
        this.voltage = voltage;
    }
}
