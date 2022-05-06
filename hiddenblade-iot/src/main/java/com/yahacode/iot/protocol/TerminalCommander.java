package com.yahacode.iot.protocol;

/**
 * encode command data
 *
 * @param <T> raw data
 * @author zengyongli
 * @since 2021/01/08
 */
public interface TerminalCommander<T> {

    byte[] encode(T t);
}
