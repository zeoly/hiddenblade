package com.yahacode.iot.protocol.box.dina.data;

public class CanParamData extends GpsParamData {

    Integer statusMask;

    Long dsMask;

    public Integer getStatusMask() {
        return statusMask;
    }

    public void setStatusMask(Integer statusMask) {
        this.statusMask = statusMask;
    }

    public Long getDsMask() {
        return dsMask;
    }

    public void setDsMask(Long dsMask) {
        this.dsMask = dsMask;
    }
}
