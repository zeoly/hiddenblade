package com.yahacode.iot.protocol.box.dina.data.sub;

import java.util.LinkedList;
import java.util.List;

public class ExtendEventContent {

    Integer obdNumber;

    List<ExtendEventContentObd> obdList = new LinkedList<>();

    public Integer getObdNumber() {
        return obdNumber;
    }

    public void setObdNumber(Integer obdNumber) {
        this.obdNumber = obdNumber;
    }

    public List<ExtendEventContentObd> getObdList() {
        return obdList;
    }

    public void setObdList(List<ExtendEventContentObd> obdList) {
        this.obdList = obdList;
    }
}
