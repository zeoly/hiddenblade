package com.yahacode.iot.protocol.box.dina.data.sub;

public class ExtendEvent {

    String type;

    Integer length;

    //    ExtendEventContent content;
    String content;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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
//    public ExtendEventContent getContent() {
//        return content;
//    }
//
//    public void setContent(ExtendEventContent content) {
//        this.content = content;
//    }
}
