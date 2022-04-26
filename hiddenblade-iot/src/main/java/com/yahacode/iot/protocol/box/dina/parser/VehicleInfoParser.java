package com.yahacode.iot.protocol.box.dina.parser;

import com.yahacode.iot.protocol.box.dina.data.VehicleData;

public class VehicleInfoParser extends AbstractDinaParser<VehicleData> {

    @Override
    public VehicleData parse(byte[] data) {
        VehicleData vehicleData = new VehicleData();
        vehicleData.setBrandId(toInt(data[0]));
        vehicleData.setProductId(toInt(data[1]));
        vehicleData.setYearId(toInt(data[2]));
        vehicleData.setGearType(toInt(data[3]));
        vehicleData.setHandleType(toInt(data[4]));
        vehicleData.setObdProtocol(getObdProtocol(toInt(data[5])));
        int index = 6;
        vehicleData.setVin(toString(data, index, 17));
        index += 17;
        vehicleData.setDisplacement(toInt(data[index]));
        index++;
        vehicleData.setCidNum(toInt(data[index]));
        index++;
        if (vehicleData.getCidNum() > 0) {
            vehicleData.setCid1(toString(data, index, 16));
            index += 16;
        }
        if (vehicleData.getCidNum() > 1) {
            vehicleData.setCid2(toString(data, index, 16));
            index += 16;
        }
        if (vehicleData.getCidNum() > 2) {
            vehicleData.setCid3(toString(data, index, 16));
        }
        return vehicleData;
    }

    private String getObdProtocol(int code) {
        switch (code) {
            case 1:
                return "KWP quick init";
            case 2:
                return "KWP slow init";
            case 3:
                return "ISO";
            case 4:
                return "CAN STD 500K";
            case 5:
                return "CAN STD 250K";
            case 6:
                return "CAN EXT 500K";
            case 7:
                return "CAN EXT 250K";
            default:
                return null;
        }
    }
}
