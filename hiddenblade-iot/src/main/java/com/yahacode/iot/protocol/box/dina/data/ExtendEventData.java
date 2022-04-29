package com.yahacode.iot.protocol.box.dina.data;

import com.yahacode.iot.protocol.box.dina.data.sub.ExtendEvent;

import java.util.LinkedList;
import java.util.List;

public class ExtendEventData extends GpsData {

    Integer eventNumber;

    List<ExtendEvent> eventList = new LinkedList<>();

    public Integer getEventNumber() {
        return eventNumber;
    }

    public void setEventNumber(Integer eventNumber) {
        this.eventNumber = eventNumber;
    }

    public List<ExtendEvent> getEventList() {
        return eventList;
    }

    public void setEventList(List<ExtendEvent> eventList) {
        this.eventList = eventList;
    }
}
