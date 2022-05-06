package com.yahacode.iot.protocol.box.dina.commander;

import com.yahacode.iot.protocol.box.dina.FunctionId;

public class CommanderFactory {

    public static AbstractCommander getCommander(int functionId) {
        switch (functionId) {
            case FunctionId.Setting.INIT_VEHICLE_TYPE:
                return new InitVehicleTypeCommander();
            case FunctionId.Setting.INIT_MILES_CONS:
                return new InitMilesConsCommander();
            case FunctionId.Query.GPS:
            case FunctionId.Query.GPS_PARAM:
            case FunctionId.Query.CAN_PARAM:
            case FunctionId.Query.HEARTBEAT_PARAM:
            case FunctionId.Query.LOW_VOLT_ALERT_PARAM:
                return new QueryCommander(functionId);
            default:
                return null;
        }
    }
}
