package com.yahacode.iot.protocol.box.dina;

public class FunctionId {

    public static final int GPS = 0x00;

    public static final int CAN = 0x01;

    public static final int DTC = 0x02;

    public static final int DEVICE_VERSION = 0x03;

    public static final int ALERT = 0x04;

    public static final int HEARTBEAT = 0x05;

    public static final int DRIVING_SCENE = 0x06;

    public static final int SIM = 0x07;

    public static final int BASE_STATION = 0x08;

    public static final int BASE_STATION_SIMPLE = 0x09;

    public static final int VEHICLE = 0x10;

    public static final int QUERY_SETTING_PARAM = 0x93;

    public static final int TIME_SYNC = 0x94;

    public static final int SEGMENT_EVENT = 0x97;

    public class Query {

        public static final int GPS = 0x30;

        public static final int GPS_PARAM = 0x31;

        public static final int CAN_PARAM = 0x32;

        public static final int HEARTBEAT_PARAM = 0x36;

        public static final int LOW_VOLT_ALERT_PARAM = 0x38;

    }

    public class Setting {

        public static final int INIT_VEHICLE_TYPE = 0x5B;

        public static final int INIT_MILES_CONS = 0x5C;
    }
}
