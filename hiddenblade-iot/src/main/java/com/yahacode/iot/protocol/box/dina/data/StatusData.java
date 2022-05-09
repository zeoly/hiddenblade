package com.yahacode.iot.protocol.box.dina.data;

import com.yahacode.iot.protocol.box.dina.data.sub.ModuleStatus;

import java.time.LocalDateTime;

public class StatusData {

    LocalDateTime timestamp;

    Integer gpsStatus;

    Integer obdStatus;

    Integer commStatus;

    ModuleStatus moduleStatus;

    Integer batterySet;

    Integer obdSet;

    Integer commSet;

    Integer gpsSet;

    Integer ubiSet;

    Integer sleepDuration;

    Integer gpsDuration;

    Integer obdDuration;

    Double accOnVoltThreshold;

    Double accOffVoltThreshold;

    Double lowPowerVoltThreshold;

    Integer wakeupAccThreshold;

    Integer sleepAccThreshold;

    Integer runningAccThreshold;

    Integer wifiSet;

    String smsCenterNum;

    String gwAddress;

    String apnInfo;

    String upgradeAddress;

    String softwareVersion;

    String hardwareVersion;

    Integer internationalVersionSet;

    Integer TrafficModule;

    Integer coldStartTime;

    Integer warmStartTime;

    Integer bufferNum;

    Integer submeterPositioning;

    Integer transEncryption;

    Integer protocolType;

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public Integer getGpsStatus() {
        return gpsStatus;
    }

    public void setGpsStatus(Integer gpsStatus) {
        this.gpsStatus = gpsStatus;
    }

    public Integer getObdStatus() {
        return obdStatus;
    }

    public void setObdStatus(Integer obdStatus) {
        this.obdStatus = obdStatus;
    }

    public Integer getCommStatus() {
        return commStatus;
    }

    public void setCommStatus(Integer commStatus) {
        this.commStatus = commStatus;
    }

    public ModuleStatus getModuleStatus() {
        return moduleStatus;
    }

    public void setModuleStatus(ModuleStatus moduleStatus) {
        this.moduleStatus = moduleStatus;
    }

    public Integer getBatterySet() {
        return batterySet;
    }

    public void setBatterySet(Integer batterySet) {
        this.batterySet = batterySet;
    }

    public Integer getObdSet() {
        return obdSet;
    }

    public void setObdSet(Integer obdSet) {
        this.obdSet = obdSet;
    }

    public Integer getCommSet() {
        return commSet;
    }

    public void setCommSet(Integer commSet) {
        this.commSet = commSet;
    }

    public Integer getGpsSet() {
        return gpsSet;
    }

    public void setGpsSet(Integer gpsSet) {
        this.gpsSet = gpsSet;
    }

    public Integer getUbiSet() {
        return ubiSet;
    }

    public void setUbiSet(Integer ubiSet) {
        this.ubiSet = ubiSet;
    }

    public Integer getSleepDuration() {
        return sleepDuration;
    }

    public void setSleepDuration(Integer sleepDuration) {
        this.sleepDuration = sleepDuration;
    }

    public Integer getGpsDuration() {
        return gpsDuration;
    }

    public void setGpsDuration(Integer gpsDuration) {
        this.gpsDuration = gpsDuration;
    }

    public Integer getObdDuration() {
        return obdDuration;
    }

    public void setObdDuration(Integer obdDuration) {
        this.obdDuration = obdDuration;
    }

    public Double getAccOnVoltThreshold() {
        return accOnVoltThreshold;
    }

    public void setAccOnVoltThreshold(Double accOnVoltThreshold) {
        this.accOnVoltThreshold = accOnVoltThreshold;
    }

    public Double getAccOffVoltThreshold() {
        return accOffVoltThreshold;
    }

    public void setAccOffVoltThreshold(Double accOffVoltThreshold) {
        this.accOffVoltThreshold = accOffVoltThreshold;
    }

    public Double getLowPowerVoltThreshold() {
        return lowPowerVoltThreshold;
    }

    public void setLowPowerVoltThreshold(Double lowPowerVoltThreshold) {
        this.lowPowerVoltThreshold = lowPowerVoltThreshold;
    }

    public Integer getWakeupAccThreshold() {
        return wakeupAccThreshold;
    }

    public void setWakeupAccThreshold(Integer wakeupAccThreshold) {
        this.wakeupAccThreshold = wakeupAccThreshold;
    }

    public Integer getSleepAccThreshold() {
        return sleepAccThreshold;
    }

    public void setSleepAccThreshold(Integer sleepAccThreshold) {
        this.sleepAccThreshold = sleepAccThreshold;
    }

    public Integer getRunningAccThreshold() {
        return runningAccThreshold;
    }

    public void setRunningAccThreshold(Integer runningAccThreshold) {
        this.runningAccThreshold = runningAccThreshold;
    }

    public Integer getWifiSet() {
        return wifiSet;
    }

    public void setWifiSet(Integer wifiSet) {
        this.wifiSet = wifiSet;
    }

    public String getSmsCenterNum() {
        return smsCenterNum;
    }

    public void setSmsCenterNum(String smsCenterNum) {
        this.smsCenterNum = smsCenterNum;
    }

    public String getGwAddress() {
        return gwAddress;
    }

    public void setGwAddress(String gwAddress) {
        this.gwAddress = gwAddress;
    }

    public String getApnInfo() {
        return apnInfo;
    }

    public void setApnInfo(String apnInfo) {
        this.apnInfo = apnInfo;
    }

    public String getUpgradeAddress() {
        return upgradeAddress;
    }

    public void setUpgradeAddress(String upgradeAddress) {
        this.upgradeAddress = upgradeAddress;
    }

    public String getSoftwareVersion() {
        return softwareVersion;
    }

    public void setSoftwareVersion(String softwareVersion) {
        this.softwareVersion = softwareVersion;
    }

    public String getHardwareVersion() {
        return hardwareVersion;
    }

    public void setHardwareVersion(String hardwareVersion) {
        this.hardwareVersion = hardwareVersion;
    }

    public Integer getInternationalVersionSet() {
        return internationalVersionSet;
    }

    public void setInternationalVersionSet(Integer internationalVersionSet) {
        this.internationalVersionSet = internationalVersionSet;
    }

    public Integer getTrafficModule() {
        return TrafficModule;
    }

    public void setTrafficModule(Integer trafficModule) {
        TrafficModule = trafficModule;
    }

    public Integer getColdStartTime() {
        return coldStartTime;
    }

    public void setColdStartTime(Integer coldStartTime) {
        this.coldStartTime = coldStartTime;
    }

    public Integer getWarmStartTime() {
        return warmStartTime;
    }

    public void setWarmStartTime(Integer warmStartTime) {
        this.warmStartTime = warmStartTime;
    }

    public Integer getBufferNum() {
        return bufferNum;
    }

    public void setBufferNum(Integer bufferNum) {
        this.bufferNum = bufferNum;
    }

    public Integer getSubmeterPositioning() {
        return submeterPositioning;
    }

    public void setSubmeterPositioning(Integer submeterPositioning) {
        this.submeterPositioning = submeterPositioning;
    }

    public Integer getTransEncryption() {
        return transEncryption;
    }

    public void setTransEncryption(Integer transEncryption) {
        this.transEncryption = transEncryption;
    }

    public Integer getProtocolType() {
        return protocolType;
    }

    public void setProtocolType(Integer protocolType) {
        this.protocolType = protocolType;
    }
}
