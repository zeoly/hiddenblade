package com.yahacode.iot.protocol.box.dina;

import com.yahacode.hiddenblade.tool.utils.ByteUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DinaMessage {

    private static final Logger log = LoggerFactory.getLogger(DinaMessage.class);

    public static final int MIN_LENGTH = 14;

    public static final int HEADER = 0xFA;

    public static final int FOOTER = 0xFB;

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
        if (data.length < MIN_LENGTH) {
            log.warn("message length is too short, length: {}\n{}", data.length, ByteUtil.formatBytes(data));
            return null;
        }
        DinaMessage message = new DinaMessage();
        message.setHeader1(toInt(data[0]));
        message.setHeader2(toInt(data[1]));
        if (message.getHeader1() != HEADER || message.getHeader2() != HEADER) {
            log.warn("message header is invalid, header: {} {}\n{}", message.getHeader1(), message.getHeader2(), ByteUtil.formatBytes(data));
            return null;
        }
        message.setSerial(toInt(data[2]));
        message.setFunctionId(toInt(data[3]));
        String deviceId = Long.toString((long) toInt(data[9]) | (long) toInt(data[8]) << 8 | (long) toInt(data[7]) << 16 | (long) toInt(data[6]) << 24 | (long) toInt(data[5]) << 32 | (long) toInt(data[4]) << 40);
        message.setDeviceId(deviceId);
        message.setDataLength(toInt(data[11]) | toInt(data[10]) << 8);
        if (MIN_LENGTH + message.getDataLength() > data.length) {
            log.warn("message length is not long enough, length: {}, require:{}\n{}", data.length, MIN_LENGTH + message.getDataLength(), ByteUtil.formatBytes(data));
            return null;
        }
        message.setData(ByteUtil.subBytes(data, 12, message.getDataLength()));
        message.setParity(toInt(data[data.length - 2]));
        message.setFooter(toInt(data[data.length - 1]));
        if (message.getFooter() != FOOTER) {
            log.warn("message footer is invalid, footer: {}\n{}", message.getFooter(), ByteUtil.formatBytes(data));
            return null;
        }
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
