package com.yahacode.iot.protocol.box.dina.responder;

import com.yahacode.iot.protocol.box.dina.FunctionId;

public class ResponderFactory {

    public static MessageResponder getResponder(Integer functionId) {
        switch (functionId) {
            case FunctionId.ALERT:
            case FunctionId.SEGMENT_EVENT:
                return new AlertResponder();
            case FunctionId.QUERY_SETTING_PARAM:
                return new SettingParamResponder();
            case FunctionId.TIME_SYNC:
                return new TimeSyncResponder();
            default:
                return new CommonResponder();
        }
    }
}
