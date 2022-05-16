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

    public static final int PARAM_ITEM = 0x10;

    public static final int VEHICLE = 0x0A;

    public static final int QUERY_SETTING_PARAM = 0x93;

    public static final int TIME_SYNC = 0x94;

    public static final int SEGMENT_EVENT = 0x97;

    public static final int DEBUG_EVENT = 0x9D;

    public static final int EXTEND_EVENT = 0x9E;

    public static final int MAX_GPS = 0xA0;

    public static final int MAX_CAN = 0xA1;

    public static final int RAPID_DECELERATION = 0xA8;

    public static final int RAPID_ACCELERATION = 0xA9;

    public static final int SHARP_TURN = 0xAA;

    public static final int COLLISION = 0xAB;

    public static final int HARD_BRAKING = 0xAC;

    public static final int EV_CAN = 0xB6;

    public static final int EV_MAX_CAN = 0xBA;

    public static final int STATUS = 0xBD;

    public class Back {

        public static final int GPS = 0x20;

        public static final int GPS_PARAM = 0x11;

        public static final int CAN_PARAM = 0x12;

        public static final int GPRS_PARAM = 0x13;

        public static final int SMS_PARAM = 0x14;

        public static final int APN_PARAM = 0x15;

        public static final int HEARTBEAT_PARAM = 0x16;

        public static final int LOW_VOLT_ALERT_PARAM = 0x18;

        public static final int SETTING = 0x7F;

    }

    public class Query {

        public static final int GPS = 0x30;

        public static final int GPS_PARAM = 0x31;

        public static final int CAN_PARAM = 0x32;

        public static final int GPRS_PARAM = 0x33;

        public static final int SMS_PARAM = 0x34;

        public static final int APN_PARAM = 0x35;

        public static final int HEARTBEAT_PARAM = 0x36;

        public static final int LOW_VOLT_ALERT_PARAM = 0x38;

        public static final int IDLING_PARAM = 0x47;
    }

    public class Setting {

        public static final int INIT_VEHICLE_TYPE = 0x5B;

        public static final int INIT_MILES_CONS = 0x5C;
    }
}
