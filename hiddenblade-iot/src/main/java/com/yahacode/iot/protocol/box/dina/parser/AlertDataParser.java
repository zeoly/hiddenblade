package com.yahacode.iot.protocol.box.dina.parser;

import com.yahacode.hiddenblade.tool.utils.MathUtil;
import com.yahacode.iot.protocol.box.dina.data.AlertData;
import com.yahacode.iot.protocol.box.dina.data.GpsData;
import org.springframework.beans.BeanUtils;

public class AlertDataParser extends AbstractDinaParser<AlertData> {

    private GpsDataParser gpsDataParser = new GpsDataParser();

    @Override
    public AlertData parse(byte[] data) {
        GpsData gpsData = gpsDataParser.parse(data);
        AlertData alertData = new AlertData();
        BeanUtils.copyProperties(gpsData, alertData);
        int index = 26;
        alertData.setCollision(bitFlag(data[index], 0));
        alertData.setTowing(bitFlag(data[index], 1));
        alertData.setRollover(bitFlag(data[index], 2));
        alertData.setLowVoltage(bitFlag(data[index], 3));
        alertData.setCollisionTest(bitFlag(data[index], 4));
        alertData.setCollisionVideo(bitFlag(data[index], 5));
        alertData.setCollisionFile(bitFlag(data[index], 6));
        index++;
        alertData.setStart(bitFlag(data[index], 0));
        alertData.setEnd(bitFlag(data[index], 1));
        alertData.setPlugOut(bitFlag(data[index], 2));
        alertData.setPlugIn(bitFlag(data[index], 3));
        alertData.setPositionOvertime(bitFlag(data[index], 4));
        alertData.setIdleOvertime(bitFlag(data[index], 5));
        alertData.setButton(bitFlag(data[index], 6));
        index++;
        alertData.setFlashFault(bitFlag(data[index], 0));
        alertData.setCanFault(bitFlag(data[index], 1));
        alertData.setSensorFault(bitFlag(data[index], 2));
        alertData.setGpsFault(bitFlag(data[index], 3));
        index++;
        if (data.length > index) {
            alertData.setAccelerationX(MathUtil.precision(0.1 * toSignedInt(data, index, 2), 1));
            index += 2;
            alertData.setAccelerationY(MathUtil.precision(0.1 * toSignedInt(data, index, 2), 1));
            index += 2;
            alertData.setAccelerationZ(MathUtil.precision(0.1 * toSignedInt(data, index, 2), 1));
            index += 2;
        }
        if (data.length > index) {
            alertData.setIdleDuration(toInt(data, index, 2));
        }
        return alertData;
    }
}
