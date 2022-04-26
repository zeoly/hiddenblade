package com.yahacode.iot.protocol.box.dina.data;

import com.yahacode.iot.protocol.box.dina.data.sub.DoorStatus;
import com.yahacode.iot.protocol.box.dina.data.sub.Gear;
import com.yahacode.iot.protocol.box.dina.data.sub.LightStatus;
import com.yahacode.iot.protocol.box.dina.data.sub.LockStatus;
import com.yahacode.iot.protocol.box.dina.data.sub.MileageType;
import com.yahacode.iot.protocol.box.dina.data.sub.SecurityStatus;
import com.yahacode.iot.protocol.box.dina.data.sub.WindowStatus;

import java.time.LocalDateTime;

public class CanData {

    private LocalDateTime timestamp;

    private SecurityStatus securityStatus;

    private DoorStatus doorStatus;

    private WindowStatus windowStatus;

    private LockStatus lockStatus;

    private LightStatus lightStatus;

    private Boolean malfunctionIndicatorStatus;

    private Integer DtcNum;

    private String obdType;

    private Double voltage;

    private Integer engineRpm;

    private Integer speed;

    private Integer intakeAirTemp;

    private Integer coolantTemp;

    private Integer environmentTemp;

    private Integer intakePressure;

    private Integer fuelPressure;

    private Integer barometricPressure;

    private Double airflow;

    private Double throttlePosition;

    private Double accPedalPosition;

    private Integer engineRunTime;

    private Long faultMileage;

    private String innage;

    private Integer engineLoad;

    private Double longTermFuelTrimBank;

    private Double sparkAngleBeforeTdc;

    private Long panelMileage;

    private Gear gear;

    private Integer tirePressureLF;

    private Integer tirePressureRF;

    private Integer tirePressureLB;

    private Integer tirePressureRB;

    private MileageType mileageType;

    private Long totalMileage;

    private Long totalFuelConsumption;

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public SecurityStatus getSecurityStatus() {
        return securityStatus;
    }

    public void setSecurityStatus(SecurityStatus securityStatus) {
        this.securityStatus = securityStatus;
    }

    public DoorStatus getDoorStatus() {
        return doorStatus;
    }

    public void setDoorStatus(DoorStatus doorStatus) {
        this.doorStatus = doorStatus;
    }

    public WindowStatus getWindowStatus() {
        return windowStatus;
    }

    public void setWindowStatus(WindowStatus windowStatus) {
        this.windowStatus = windowStatus;
    }

    public LockStatus getLockStatus() {
        return lockStatus;
    }

    public void setLockStatus(LockStatus lockStatus) {
        this.lockStatus = lockStatus;
    }

    public LightStatus getLightStatus() {
        return lightStatus;
    }

    public void setLightStatus(LightStatus lightStatus) {
        this.lightStatus = lightStatus;
    }

    public Boolean getMalfunctionIndicatorStatus() {
        return malfunctionIndicatorStatus;
    }

    public void setMalfunctionIndicatorStatus(Boolean malfunctionIndicatorStatus) {
        this.malfunctionIndicatorStatus = malfunctionIndicatorStatus;
    }

    public Integer getDtcNum() {
        return DtcNum;
    }

    public void setDtcNum(Integer dtcNum) {
        DtcNum = dtcNum;
    }

    public String getObdType() {
        return obdType;
    }

    public void setObdType(String obdType) {
        this.obdType = obdType;
    }

    public Double getVoltage() {
        return voltage;
    }

    public void setVoltage(Double voltage) {
        this.voltage = voltage;
    }

    public Integer getEngineRpm() {
        return engineRpm;
    }

    public void setEngineRpm(Integer engineRpm) {
        this.engineRpm = engineRpm;
    }

    public Integer getSpeed() {
        return speed;
    }

    public void setSpeed(Integer speed) {
        this.speed = speed;
    }

    public Integer getIntakeAirTemp() {
        return intakeAirTemp;
    }

    public void setIntakeAirTemp(Integer intakeAirTemp) {
        this.intakeAirTemp = intakeAirTemp;
    }

    public Integer getCoolantTemp() {
        return coolantTemp;
    }

    public void setCoolantTemp(Integer coolantTemp) {
        this.coolantTemp = coolantTemp;
    }

    public Integer getEnvironmentTemp() {
        return environmentTemp;
    }

    public void setEnvironmentTemp(Integer environmentTemp) {
        this.environmentTemp = environmentTemp;
    }

    public Integer getIntakePressure() {
        return intakePressure;
    }

    public void setIntakePressure(Integer intakePressure) {
        this.intakePressure = intakePressure;
    }

    public Integer getFuelPressure() {
        return fuelPressure;
    }

    public void setFuelPressure(Integer fuelPressure) {
        this.fuelPressure = fuelPressure;
    }

    public Integer getBarometricPressure() {
        return barometricPressure;
    }

    public void setBarometricPressure(Integer barometricPressure) {
        this.barometricPressure = barometricPressure;
    }

    public Double getAirflow() {
        return airflow;
    }

    public void setAirflow(Double airflow) {
        this.airflow = airflow;
    }

    public Double getThrottlePosition() {
        return throttlePosition;
    }

    public void setThrottlePosition(Double throttlePosition) {
        this.throttlePosition = throttlePosition;
    }

    public Double getAccPedalPosition() {
        return accPedalPosition;
    }

    public void setAccPedalPosition(Double accPedalPosition) {
        this.accPedalPosition = accPedalPosition;
    }

    public Integer getEngineRunTime() {
        return engineRunTime;
    }

    public void setEngineRunTime(Integer engineRunTime) {
        this.engineRunTime = engineRunTime;
    }

    public Long getFaultMileage() {
        return faultMileage;
    }

    public void setFaultMileage(Long faultMileage) {
        this.faultMileage = faultMileage;
    }

    public String getInnage() {
        return innage;
    }

    public void setInnage(String innage) {
        this.innage = innage;
    }

    public Integer getEngineLoad() {
        return engineLoad;
    }

    public void setEngineLoad(Integer engineLoad) {
        this.engineLoad = engineLoad;
    }

    public Double getLongTermFuelTrimBank() {
        return longTermFuelTrimBank;
    }

    public void setLongTermFuelTrimBank(Double longTermFuelTrimBank) {
        this.longTermFuelTrimBank = longTermFuelTrimBank;
    }

    public Double getSparkAngleBeforeTdc() {
        return sparkAngleBeforeTdc;
    }

    public void setSparkAngleBeforeTdc(Double sparkAngleBeforeTdc) {
        this.sparkAngleBeforeTdc = sparkAngleBeforeTdc;
    }

    public Long getPanelMileage() {
        return panelMileage;
    }

    public void setPanelMileage(Long panelMileage) {
        this.panelMileage = panelMileage;
    }

    public Gear getGear() {
        return gear;
    }

    public void setGear(Gear gear) {
        this.gear = gear;
    }

    public Integer getTirePressureLF() {
        return tirePressureLF;
    }

    public void setTirePressureLF(Integer tirePressureLF) {
        this.tirePressureLF = tirePressureLF;
    }

    public Integer getTirePressureRF() {
        return tirePressureRF;
    }

    public void setTirePressureRF(Integer tirePressureRF) {
        this.tirePressureRF = tirePressureRF;
    }

    public Integer getTirePressureLB() {
        return tirePressureLB;
    }

    public void setTirePressureLB(Integer tirePressureLB) {
        this.tirePressureLB = tirePressureLB;
    }

    public Integer getTirePressureRB() {
        return tirePressureRB;
    }

    public void setTirePressureRB(Integer tirePressureRB) {
        this.tirePressureRB = tirePressureRB;
    }

    public MileageType getMileageType() {
        return mileageType;
    }

    public void setMileageType(MileageType mileageType) {
        this.mileageType = mileageType;
    }

    public Long getTotalMileage() {
        return totalMileage;
    }

    public void setTotalMileage(Long totalMileage) {
        this.totalMileage = totalMileage;
    }

    public Long getTotalFuelConsumption() {
        return totalFuelConsumption;
    }

    public void setTotalFuelConsumption(Long totalFuelConsumption) {
        this.totalFuelConsumption = totalFuelConsumption;
    }
}
