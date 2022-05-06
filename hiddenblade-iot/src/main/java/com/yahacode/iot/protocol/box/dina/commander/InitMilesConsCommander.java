package com.yahacode.iot.protocol.box.dina.commander;

import com.yahacode.iot.protocol.box.dina.FunctionId;
import com.yahacode.iot.protocol.box.dina.data.MilesConsCommand;

public class InitMilesConsCommander extends AbstractCommander<MilesConsCommand> {

    @Override
    public byte[] getCommandBody(MilesConsCommand baseCommand) {
        byte[] body = new byte[8];
        body[0] = (byte) ((baseCommand.getInitMileage()) >> 24 & 0xff);
        body[1] = (byte) ((baseCommand.getInitMileage()) >> 16 & 0xff);
        body[2] = (byte) ((baseCommand.getInitMileage()) >> 8 & 0xff);
        body[3] = (byte) (baseCommand.getInitMileage() & 0xff);
        body[4] = (byte) ((baseCommand.getInitFuelConsumption()) >> 24 & 0xff);
        body[5] = (byte) ((baseCommand.getInitFuelConsumption()) >> 16 & 0xff);
        body[6] = (byte) ((baseCommand.getInitFuelConsumption()) >> 8 & 0xff);
        body[7] = (byte) (baseCommand.getInitFuelConsumption() & 0xff);
        return body;
    }

    @Override
    public int getCommandId() {
        return FunctionId.Setting.INIT_MILES_CONS;
    }
}
