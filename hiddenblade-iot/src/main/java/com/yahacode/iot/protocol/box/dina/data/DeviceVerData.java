package com.yahacode.iot.protocol.box.dina.data;

public class DeviceVerData {

    private String softwareVer;

    private String softwareDate;

    private String hardwareVer;

    private String gsmType;

    private String bootloaderVer;

    private Integer rebootTimes;

    private String imei;

    public String getSoftwareVer() {
        return softwareVer;
    }

    public void setSoftwareVer(String softwareVer) {
        this.softwareVer = softwareVer;
    }

    public String getSoftwareDate() {
        return softwareDate;
    }

    public void setSoftwareDate(String softwareDate) {
        this.softwareDate = softwareDate;
    }

    public String getHardwareVer() {
        return hardwareVer;
    }

    public void setHardwareVer(String hardwareVer) {
        this.hardwareVer = hardwareVer;
    }

    public String getGsmType() {
        return gsmType;
    }

    public void setGsmType(String gsmType) {
        this.gsmType = gsmType;
    }

    public String getBootloaderVer() {
        return bootloaderVer;
    }

    public void setBootloaderVer(String bootloaderVer) {
        this.bootloaderVer = bootloaderVer;
    }

    public Integer getRebootTimes() {
        return rebootTimes;
    }

    public void setRebootTimes(Integer rebootTimes) {
        this.rebootTimes = rebootTimes;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }
}
