package com.yahacode.iot.protocol.box.dina.data;

public class BaseStationData extends GpsData {

    Integer mobileCountryCode = 0;

    Integer mobileNetworkCode = 0;

    /**
     * cdma sid
     */
    Integer systemIdentity = 0;

    /**
     * cdma nid
     */
    Integer networkIdentity = 0;

    /**
     * cdma bid
     */
    Integer billingIdentity = 0;

    /**
     * gsm lac
     */
    Integer locationAreaCode = 0;

    /**
     * gsm cid
     */
    Integer cellIdentity = 0;

    public Integer getMobileCountryCode() {
        return mobileCountryCode;
    }

    public void setMobileCountryCode(Integer mobileCountryCode) {
        this.mobileCountryCode = mobileCountryCode;
    }

    public Integer getMobileNetworkCode() {
        return mobileNetworkCode;
    }

    public void setMobileNetworkCode(Integer mobileNetworkCode) {
        this.mobileNetworkCode = mobileNetworkCode;
    }

    public Integer getSystemIdentity() {
        return systemIdentity;
    }

    public void setSystemIdentity(Integer systemIdentity) {
        this.systemIdentity = systemIdentity;
    }

    public Integer getNetworkIdentity() {
        return networkIdentity;
    }

    public void setNetworkIdentity(Integer networkIdentity) {
        this.networkIdentity = networkIdentity;
    }

    public Integer getBillingIdentity() {
        return billingIdentity;
    }

    public void setBillingIdentity(Integer billingIdentity) {
        this.billingIdentity = billingIdentity;
    }

    public Integer getLocationAreaCode() {
        return locationAreaCode;
    }

    public void setLocationAreaCode(Integer locationAreaCode) {
        this.locationAreaCode = locationAreaCode;
    }

    public Integer getCellIdentity() {
        return cellIdentity;
    }

    public void setCellIdentity(Integer cellIdentity) {
        this.cellIdentity = cellIdentity;
    }
}
