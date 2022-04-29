package com.yahacode.iot.protocol.box.dina.parser;

import com.yahacode.hiddenblade.tool.utils.ByteUtil;
import com.yahacode.iot.protocol.TerminalDataParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;

public abstract class AbstractDinaParser<T> implements TerminalDataParser<T> {

    private static final Logger log = LoggerFactory.getLogger(AbstractDinaParser.class);

    private static final int TIME_ZONE = 8;

    protected int toInt(byte data) {
        return data & 0xff;
    }

    protected LocalDateTime getTimestamp(byte[] data) {
        return LocalDateTime.of(toInt(data[3]) + 2000, toInt(data[4]), toInt(data[5]), toInt(data[0]), toInt(data[1]), toInt(data[2])).plusHours(TIME_ZONE);
    }

    protected int toInt(byte[] data, int start, int length) {
        int result = 0;
        for (int index = start; index < start + length; index++) {
            result = (result << 8) | toInt(data[index]);
        }
        return result;
    }

    protected int toSignedInt(byte[] data, int start, int length) {
        int signedBit = bitFlag(data[start], 7) ? -1 : 1;
        int result = data[start] & 0x7f;
        for (int index = start + 1; index < start + length; index++) {
            result = (result << 8) | toInt(data[index]);
        }
        return signedBit * result;
    }

    protected long toLong(byte[] data, int start, int length) {
        long result = 0;
        for (int index = start; index < start + length; index++) {
            result = (result << 8) | ((long) toInt(data[index]));
        }
        return result;
    }

    protected String toString(byte[] data, int start, int length) {
        try {
            return new String(ByteUtil.subBytes(data, start, length), "US-ASCII").trim();
        } catch (UnsupportedEncodingException e) {
            log.warn("parse byte to String fail", e);
            return null;
        }
    }

    protected String toHexString(byte[] data, int start, int length) {
        return ByteUtil.bytesToHexString(ByteUtil.subBytes(data, start, length));
    }

    protected boolean bitFlag(byte data, int bit) {
        return (data & (1 << bit)) > 0;
    }
}
