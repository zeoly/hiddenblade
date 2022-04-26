package com.yahacode.iot.protocol.box.dina.data;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

public class DtcData {

    LocalDateTime timestamp;

    Integer dtcNumber;

    List<String> dtcList = new LinkedList<>();

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public Integer getDtcNumber() {
        return dtcNumber;
    }

    public void setDtcNumber(Integer dtcNumber) {
        this.dtcNumber = dtcNumber;
    }

    public List<String> getDtcList() {
        return dtcList;
    }

    public void setDtcList(List<String> dtcList) {
        this.dtcList = dtcList;
    }
}
