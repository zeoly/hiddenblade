package com.yahacode.iot.protocol.box.dina.parser;

import com.yahacode.hiddenblade.tool.utils.ByteUtil;
import com.yahacode.hiddenblade.tool.utils.MathUtil;
import com.yahacode.hiddenblade.tool.utils.StringUtil;
import com.yahacode.iot.protocol.box.dina.data.EVCanData;
import com.yahacode.iot.protocol.box.dina.data.sub.EVDoorStatus;
import com.yahacode.iot.protocol.box.dina.data.sub.EVSecurityStatus;
import com.yahacode.iot.protocol.box.dina.data.sub.EVWindowStatus;
import com.yahacode.iot.protocol.box.dina.data.sub.LockStatus;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class EVCanDataParser extends AbstractDinaParser<List<EVCanData>> {

    @Override
    public List<EVCanData> parse(byte[] data) {
        List<EVCanData> list = new LinkedList<>();
        LocalDateTime timestamp = getTimestamp(data);
        List<byte[]> packets = splitDataPacket(data);
        int timeInterval = 6 / packets.size();
        for (int i = 0; i < packets.size(); i++) {
            byte[] packet = packets.get(i);
            EVCanData canData = getCanData(packet, data);
            canData.setTimestamp(timestamp.plus(1000 * timeInterval * i, ChronoUnit.MILLIS));
            list.add(canData);
        }
        return list;
    }

    private EVCanData getCanData(byte[] packet, byte[] data) {
        EVCanData canData = new EVCanData();
        int index = 0;
        if (bitFlag(data[6], 7)) {
            canData.setSecurityStatus(buildSecurityStatus(packet[index]));
            index++;
        }
        if (bitFlag(data[6], 6)) {
            canData.setDoorStatus(buildDoorStatus(packet[index]));
            index++;
        }
        if (bitFlag(data[6], 5)) {
            canData.setWindowStatus(buildWindowStatus(packet[index]));
            index++;
        }
        if (bitFlag(data[6], 4)) {
            canData.setLockStatus(buildLockStatus(packet[index]));
        }
        if (bitFlag(data[7], 7)) {
            canData.setMotorRpm(toInt(packet, index, 2) - 15000);
            index += 2;
        }
        if (bitFlag(data[7], 6)) {
            canData.setMotorTorque(toInt(packet, index, 2) - 32000);
            index += 2;
        }
        if (bitFlag(data[7], 5)) {
            canData.setMotorTemp(toInt(packet, index, 2) - 200);
            index += 2;
        }
        if (bitFlag(data[7], 4)) {
            canData.setMotorCtrlOutputCurr(MathUtil.precision(0.1 * toInt(packet, index, 2) - 1000, 1));
            index += 2;
        }
        if (bitFlag(data[7], 3)) {
            canData.setMotorCtrlDcVolt(MathUtil.precision(0.1 * toInt(packet, index, 2), 1));
            index += 2;
        }
        if (bitFlag(data[7], 2)) {
            canData.setMotorCtrlTemp(toInt(packet, index, 2) - 200);
            index += 2;
        }
        if (bitFlag(data[7], 1)) {
            canData.setMotorCtrlStatus(ByteUtil.getEighthBit(toInt(packet[index])));
            index++;
        }
        if (bitFlag(data[7], 0)) {
            canData.setMotorCtrlLife(toInt(packet[index]));
            index++;
        }
        if (bitFlag(data[8], 7)) {
            canData.setTotalVolt(MathUtil.precision(0.1 * toInt(packet, index, 2), 1));
            index += 2;
        }
        if (bitFlag(data[8], 6)) {
            canData.setTotalCurr(MathUtil.precision(0.1 * toInt(packet, index, 2) - 300, 1));
            index += 2;
        }
        if (bitFlag(data[8], 5)) {
            canData.setSoc(MathUtil.precision(0.4 * toInt(packet[index]), 1));
            index++;
        }
        if (bitFlag(data[8], 4)) {
            canData.setMaxTempBatSeqId(toInt(packet[index]));
            index++;
        }
        if (bitFlag(data[8], 3)) {
            canData.setMaxTempValue(toInt(packet[index]) - 40);
            index++;
        }
        if (bitFlag(data[8], 2)) {
            canData.setMinTempBatSeqId(toInt(packet[index]));
            index++;
        }
        if (bitFlag(data[8], 1)) {
            canData.setMinTempValue(toInt(packet[index]) - 40);
            index++;
        }
        if (bitFlag(data[8], 0)) {
            canData.setBatMaxTempDiff(toInt(packet[index]) - 40);
            index++;
        }
        if (bitFlag(data[9], 7)) {
            canData.setMaxVoltBatSeqId(toInt(packet[index]));
            index++;
        }
        if (bitFlag(data[9], 6)) {
            canData.setMaxVoltValue(MathUtil.precision(0.01 * toInt(packet, index, 2), 2));
            index += 2;
        }
        if (bitFlag(data[9], 5)) {
            canData.setMinVoltBatSeqId(toInt(packet[index]));
            index++;
        }
        if (bitFlag(data[9], 4)) {
            canData.setMinVoltValue(MathUtil.precision(0.01 * toInt(packet, index, 2), 2));
            index += 2;
        }
        if (bitFlag(data[9], 3)) {
            canData.setBatMaxVoltDiff(MathUtil.precision(0.01 * toInt(packet, index, 2), 2));
            index += 2;
        }
        if (bitFlag(data[9], 2)) {
            canData.setLimitDcVolt(MathUtil.precision(0.1 * toInt(packet, index, 2) - 1000, 1));
            index += 2;
        }
        if (bitFlag(data[9], 1)) {
            canData.setLimitDcCurr(MathUtil.precision(0.1 * toInt(packet, index, 2) - 1000, 1));
            index += 2;
        }
        if (bitFlag(data[9], 0)) {
            canData.setMotorWorkModeOrder(ByteUtil.getEighthBit(toInt(packet[index])));
            index++;
        }
        if (bitFlag(data[10], 7)) {
            canData.setBatWorkModeOrder(ByteUtil.getEighthBit(toInt(packet[index])));
            index++;
        }
        if (bitFlag(data[10], 6)) {
            canData.setVcuCtrlLife(toInt(packet[index]));
            index++;
        }
        if (bitFlag(data[10], 5)) {
            canData.setVehicleSpeed(MathUtil.precision(0.0625 * toInt(packet, index, 2), 2));
            index += 2;
        }
        if (bitFlag(data[10], 4)) {
            canData.setDistanceTotal(100 * toLong(packet, index, 4));
            index += 4;
        }
        if (bitFlag(data[10], 3)) {
            canData.setAccPedalPosition(MathUtil.precision(0.4 * toInt(packet[index]), 1));
            index++;
        }
        if (bitFlag(data[10], 2)) {
            canData.setBrakePedalPosition(MathUtil.precision(0.4 * toInt(packet[index]), 1));
            index++;
        }
        if (bitFlag(data[10], 1)) {
            canData.setOperationStatus(ByteUtil.getEighthBit(toInt(packet[index])));
            index++;
        }
        if (bitFlag(data[10], 0)) {
            canData.setGearStatus(ByteUtil.getEighthBit(toInt(packet[index])));
            index++;
        }
        if (bitFlag(data[11], 7)) {
            canData.setMotorTargetOutputTorque(toInt(packet, index, 2) - 32000);
            index += 2;
        }
        if (bitFlag(data[11], 6)) {
            canData.setMotorTargetOutputRpm(toInt(packet, index, 2) - 32000);
            index += 2;
        }
        if (bitFlag(data[11], 5)) {
            canData.setMotorCtrlReqType(StringUtil.toHex2(toInt(packet[index])));
            index++;
        }
        if (bitFlag(data[11], 4)) {
            canData.setAmtReqResp(StringUtil.toHex2(toInt(packet[index])));
            index++;
        }
        if (bitFlag(data[11], 3)) {
            canData.setTireSize(MathUtil.precision(0.1 * toInt(packet, index, 2), 1));
            index += 2;
        }
        if (bitFlag(data[11], 2)) {
            canData.setDriveTrainAxleRatio(MathUtil.precision(0.001 * toInt(packet, index, 2), 3));
            index += 2;
        }
        if (bitFlag(data[11], 1)) {
            canData.setAmtStatus(ByteUtil.getEighthBit(toInt(packet[index])));
            index++;
        }
        if (bitFlag(data[11], 0)) {
            canData.setAmtChangeGear(ByteUtil.getEighthBit(toInt(packet[index])));
            index++;
        }
        if (bitFlag(data[12], 7)) {
            canData.setAmtOutputRpm(toInt(packet, index, 2) - 32000);
            index += 2;
        }
        if (bitFlag(data[12], 6)) {
            canData.setAmtCtrlLife(toInt(packet[index]));
            index++;
        }
        if (bitFlag(data[12], 5)) {
            canData.setAmtVehicleSpeed(MathUtil.precision(0.0625 * toInt(packet, index, 2), 2));
            index += 2;
        }
        if (bitFlag(data[12], 4)) {
            canData.setAmtDistance(100 * toLong(packet, index, 4));
            index += 4;
        }
        if (bitFlag(data[12], 3)) {
            canData.setMotorTargetOutputTorqueRpm(toInt(packet, index, 2) - 32000);
            index += 2;
        }
        if (bitFlag(data[12], 2)) {
            canData.setVcuCtrlLife2(toInt(packet[index]));
            index++;
        }
        if (bitFlag(data[12], 1)) {
            // TODO
            index++;
        }
        if (bitFlag(data[12], 0)) {
            // TODO
            index++;
        }
        if (bitFlag(data[13], 7)) {
            // TODO
            index++;
        }
        if (bitFlag(data[13], 6)) {
            canData.setMaxTempProbeSeqId(toInt(packet[index]));
            index++;
        }
        if (bitFlag(data[13], 5)) {
            // TODO
            index++;
        }
        if (bitFlag(data[13], 4)) {
            canData.setMinTempProbeSeqId(toInt(packet[index]));
            index++;
        }
        if (bitFlag(data[13], 3)) {
            canData.setInsulationResistance(toInt(packet, index, 2));
            index += 2;
        }
        if (bitFlag(data[13], 2)) {
            canData.setRecableEnergyCode(toString(packet, index, 15));
            index += 15;
        }
        if (bitFlag(data[13], 1)) {
            canData.setRecableEnergyNum(toInt(packet[index]));
            index++;
        }
        if (bitFlag(data[13], 0)) {
            canData.setSingleBatteryTotal(toInt(packet, index, 2));
            index += 2;
        }
        if (bitFlag(data[14], 7)) {
            canData.setRecableTempProbeNum(toInt(packet, index, 2));
            index += 2;
        }
        if (bitFlag(data[14], 6)) {
            canData.setRecableSubFaultTotal(toInt(packet[index]));
            index++;
        }
        if (bitFlag(data[14], 5)) {
            canData.setRecableModuleTotal(toInt(packet[index]));
            index++;
        }
        if (bitFlag(data[14], 4)) {
            // TODO
            index += 400;
        }
        if (bitFlag(data[14], 3)) {
            canData.setDcWorkOrder(ByteUtil.getEighthBit(toInt(packet[index])));
            index++;
        }
        if (bitFlag(data[14], 2)) {
            canData.setCarVcuStatus(ByteUtil.getEighthBit(toInt(packet[index])));
            index++;
        }
        if (bitFlag(data[14], 1)) {
            canData.setRunMode(StringUtil.toHex2(toInt(packet[index])));
            index++;
        }
        if (bitFlag(data[14], 0)) {
            canData.setDcOutputVolt(MathUtil.precision(0.1 * toInt(packet, index, 2), 1));
            index += 2;
        }
        if (bitFlag(data[15], 7)) {
            canData.setDcOutputCurr(MathUtil.precision(0.1 * toInt(packet, index, 2), 1));
            index += 2;
        }
        if (bitFlag(data[15], 6)) {
            canData.setDcInputVolt(MathUtil.precision(0.1 * toInt(packet, index, 2), 1));
            index += 2;
        }
        if (bitFlag(data[15], 5)) {
            canData.setDcTemp(toInt(packet, index, 2));
            index += 2;
        }
        if (bitFlag(data[15], 4)) {
            canData.setDcLife(toInt(packet[index]));
            index++;
        }
        if (bitFlag(data[15], 3)) {
            canData.setDcStatus(ByteUtil.getEighthBit(toInt(packet[index])));
            index++;
        }
        if (bitFlag(data[15], 2)) {
            // todo
            index += 80;
        }
        if (bitFlag(data[15], 1)) {
            canData.setCheckSumVcu1(StringUtil.toHex2(toInt(packet[index])));
            index++;
        }
        if (bitFlag(data[15], 0)) {
            canData.setReadyLedStatus(toInt(packet[index]));
            index++;
        }
        if (bitFlag(data[16], 7)) {
            canData.setPanelMileage(toLong(packet, index, 4));
            index += 4;
        }
        if (bitFlag(data[16], 6)) {
            canData.setEnduranceMileage(toLong(packet, index, 4));
            index += 4;
        }
        if (bitFlag(data[16], 3)) {
            canData.setChargeDischargeStatus(packet[index] & 0x03);
            index++;
        }
        if (bitFlag(data[16], 2)) {
            canData.setAccStatus(toInt(packet[index]));
            index++;
        }
        if (bitFlag(data[16], 1) && bitFlag(data[16], 0)) {
            double[] coordinates = toCoordinates(packet, index);
            canData.setLongitude(coordinates[0]);
            canData.setLatitude(coordinates[1]);
            index += 8;
        }
        return canData;
    }

    private List<byte[]> splitDataPacket(byte[] data) {
        List<byte[]> packets = new ArrayList<>();
        int packetsNum = toInt(data[17]);
        int singlePacketLength = (data.length - 18) / packetsNum;
        for (int i = 18; i < data.length; i += singlePacketLength) {
            packets.add(ByteUtil.subBytes(data, i, singlePacketLength));
        }
        return packets;
    }

    private EVSecurityStatus buildSecurityStatus(byte data) {
        EVSecurityStatus securityStatus = new EVSecurityStatus();
        securityStatus.setKeyAcc(bitFlag(data, 7));
        securityStatus.setVehicleSecurity(bitFlag(data, 6));
        return securityStatus;
    }

    private EVDoorStatus buildDoorStatus(byte data) {
        EVDoorStatus doorStatus = new EVDoorStatus();
        doorStatus.setLeftFront(bitFlag(data, 7));
        doorStatus.setRightFront(bitFlag(data, 6));
        doorStatus.setLeftBack(bitFlag(data, 5));
        doorStatus.setRightBack(bitFlag(data, 4));
        doorStatus.setTrunk(bitFlag(data, 3));
        doorStatus.setEngine(bitFlag(data, 2));
        return doorStatus;
    }

    private EVWindowStatus buildWindowStatus(byte data) {
        EVWindowStatus windowStatus = new EVWindowStatus();
        windowStatus.setLeftFront(bitFlag(data, 7));
        windowStatus.setRightFront(bitFlag(data, 6));
        windowStatus.setLeftBack(bitFlag(data, 5));
        windowStatus.setRightBack(bitFlag(data, 4));
        windowStatus.setSkylight(bitFlag(data, 3));
        return windowStatus;
    }

    private LockStatus buildLockStatus(byte data) {
        LockStatus lockStatus = new LockStatus();
        lockStatus.setLeftFront(bitFlag(data, 7));
        lockStatus.setRightFront(bitFlag(data, 6));
        lockStatus.setLeftBack(bitFlag(data, 5));
        lockStatus.setRightBack(bitFlag(data, 4));
        lockStatus.setTruck(bitFlag(data, 3));
        return lockStatus;
    }
}
