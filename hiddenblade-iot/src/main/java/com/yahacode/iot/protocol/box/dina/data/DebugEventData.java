package com.yahacode.iot.protocol.box.dina.data;

public class DebugEventData extends GpsData {

    Integer debugType;

    Integer length;

    String content;

    public Integer getDebugType() {
        return debugType;
    }

    public void setDebugType(Integer debugType) {
        this.debugType = debugType;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
