package com.yahacode.iot.protocol.box.dina.data.can;

public enum Gear {

    P(1), N(2), R(3), D(4), D_PLUS(5), ONE(6), TWO(7), THREE(8), FOUR(9), FIVE(10), SIX(11);

    int code;

    Gear(int code) {
        this.code = code;
    }

    public static Gear ofCode(int code) {
        for (Gear gear : Gear.values()) {
            if (gear.getCode() == code) {
                return gear;
            }
        }
        return null;
    }

    public int getCode() {
        return code;
    }
}
