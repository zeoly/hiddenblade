package com.yahacode.iot.protocol.box.dina.data;

import com.yahacode.iot.protocol.box.dina.data.sub.Status;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

public class StatusData {

    LocalDateTime timestamp;

    Integer statusNumber;

    List<Status> statusList = new LinkedList<>();

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public Integer getStatusNumber() {
        return statusNumber;
    }

    public void setStatusNumber(Integer statusNumber) {
        this.statusNumber = statusNumber;
    }

    public List<Status> getStatusList() {
        return statusList;
    }

    public void setStatusList(List<Status> statusList) {
        this.statusList = statusList;
    }
}
