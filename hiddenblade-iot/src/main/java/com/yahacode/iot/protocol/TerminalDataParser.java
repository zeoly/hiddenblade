package com.yahacode.iot.protocol;

/**
 * parse terminal raw data
 *
 * @param <T> target data
 * @author zengyongli
 * @since 2020/12/22
 */
public interface TerminalDataParser<T> {

    T parse(byte[] data);
}
