package com.yahacode.iot.protocol.box.dina.commander;

import com.yahacode.iot.protocol.box.dina.data.BaseCommand;

public class QueryCommander extends AbstractCommander<BaseCommand> {

    Integer commandId;

    public QueryCommander(Integer commandId) {
        this.commandId = commandId;
    }

    @Override
    public BaseCommand getData(String deviceId, String str) {
        return new BaseCommand(deviceId);
    }

    @Override
    public byte[] getCommandBody(BaseCommand command) {
        return new byte[0];
    }

    @Override
    public int getCommandId() {
        return this.commandId;
    }
}
