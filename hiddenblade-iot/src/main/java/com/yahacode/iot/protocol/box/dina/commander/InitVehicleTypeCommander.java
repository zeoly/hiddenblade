package com.yahacode.iot.protocol.box.dina.commander;

import com.yahacode.hiddenblade.tool.utils.JsonUtil;
import com.yahacode.iot.protocol.box.dina.FunctionId;
import com.yahacode.iot.protocol.box.dina.data.VehicleTypeCommand;

public class InitVehicleTypeCommander extends AbstractCommander<VehicleTypeCommand> {

    @Override
    public VehicleTypeCommand getData(String str) {
        return JsonUtil.toObj(str, VehicleTypeCommand.class);
    }

    @Override
    public byte[] getCommandBody(VehicleTypeCommand command) {
        byte[] body = new byte[5];
        body[0] = (byte) (command.getBrandId() & 0xff);
        body[1] = (byte) (command.getProductId() & 0xff);
        body[2] = (byte) (command.getYearId() & 0xff);
        body[3] = (byte) (command.getGearType() & 0xff);
        body[4] = (byte) ((int) (command.getCapacity() * 10) & 0xff);
        return body;
    }

    @Override
    public int getCommandId() {
        return FunctionId.Setting.INIT_VEHICLE_TYPE;
    }
}
