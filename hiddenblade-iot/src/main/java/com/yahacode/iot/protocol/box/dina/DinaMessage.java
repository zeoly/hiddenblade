package com.yahacode.iot.protocol.box.dina;

import com.yahacode.hiddenblade.tool.utils.ByteUtil;

public class DinaMessage {

    private Integer header1;

    private Integer header2;

    private Integer serial;

    private Integer functionId;

    private String deviceId;

    private Integer dataLength;

    private byte[] data;

    private Integer parity;

    private Integer footer;

    public static DinaMessage of(byte[] data) {
        DinaMessage message = new DinaMessage();
        message.setHeader1(toInt(data[0]));
        message.setHeader2(toInt(data[1]));
        message.setSerial(toInt(data[2]));
        message.setFunctionId(toInt(data[3]));
        String deviceId = Long.toString((long) toInt(data[9]) | (long) toInt(data[8]) << 8 | (long) toInt(data[7]) << 16 | (long) toInt(data[6]) << 24 | (long) toInt(data[5]) << 32 | (long) toInt(data[4]) << 40);
        message.setDeviceId(deviceId);
        message.setDataLength(toInt(data[11]) | toInt(data[10]) << 8);
        message.setData(ByteUtil.subBytes(data, 12, message.getDataLength()));
        message.setParity(toInt(data[data.length - 2]));
        message.setFooter(toInt(data[data.length - 1]));
        return message;
    }

    private static Integer toInt(byte data) {
        return data & 0xff;
    }

    public Integer getHeader1() {
        return header1;
    }

    public void setHeader1(Integer header1) {
        this.header1 = header1;
    }

    public Integer getHeader2() {
        return header2;
    }

    public void setHeader2(Integer header2) {
        this.header2 = header2;
    }

    public Integer getSerial() {
        return serial;
    }

    public void setSerial(Integer serial) {
        this.serial = serial;
    }

    public Integer getFunctionId() {
        return functionId;
    }

    public void setFunctionId(Integer functionId) {
        this.functionId = functionId;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public Integer getDataLength() {
        return dataLength;
    }

    public void setDataLength(Integer dataLength) {
        this.dataLength = dataLength;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public Integer getParity() {
        return parity;
    }

    public void setParity(Integer parity) {
        this.parity = parity;
    }

    public Integer getFooter() {
        return footer;
    }

    public void setFooter(Integer footer) {
        this.footer = footer;
    }
}
