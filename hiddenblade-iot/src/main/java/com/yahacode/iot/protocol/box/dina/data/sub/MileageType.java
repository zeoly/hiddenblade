package com.yahacode.iot.protocol.box.dina.data.sub;

public enum MileageType {

    CAR(1), OBD(2), GPS(3);

    int code;

    MileageType(int code) {
        this.code = code;
    }

    public static MileageType ofCode(int code) {
        for (MileageType type : MileageType.values()) {
            if (type.getCode() == code) {
                return type;
            }
        }
        return null;
    }

    public int getCode() {
        return code;
    }
}
