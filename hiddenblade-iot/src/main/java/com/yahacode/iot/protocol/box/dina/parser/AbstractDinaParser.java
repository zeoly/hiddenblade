package com.yahacode.iot.protocol.box.dina.parser;

import com.yahacode.iot.protocol.TerminalDataParser;

import java.time.LocalDateTime;

public abstract class AbstractDinaParser<T> implements TerminalDataParser<T> {

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

    protected long toLong(byte[] data, int start, int length) {
        long result = 0;
        for (int index = start; index < start + length; index++) {
            result = (result << 8) | ((long) toInt(data[index]));
        }
        return result;
    }
}
