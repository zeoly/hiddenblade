package com.yahacode.iot.protocol.box.dina.parser;

import com.yahacode.hiddenblade.tool.utils.ByteUtil;
import com.yahacode.hiddenblade.tool.utils.MathUtil;
import com.yahacode.iot.protocol.box.dina.data.CanData;
import com.yahacode.iot.protocol.box.dina.data.sub.DoorStatus;
import com.yahacode.iot.protocol.box.dina.data.sub.Gear;
import com.yahacode.iot.protocol.box.dina.data.sub.LightStatus;
import com.yahacode.iot.protocol.box.dina.data.sub.LockStatus;
import com.yahacode.iot.protocol.box.dina.data.sub.MileageType;
import com.yahacode.iot.protocol.box.dina.data.sub.SecurityStatus;
import com.yahacode.iot.protocol.box.dina.data.sub.WindowStatus;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class CanDataParser extends AbstractDinaParser<List<CanData>> {

    @Override
    public List<CanData> parse(byte[] data) {
        List<CanData> list = new ArrayList<>();
        LocalDateTime timestamp = getTimestamp(data);
        List<byte[]> packets = splitDataPacket(data);
        int timeInterval = 6 / packets.size();
        for (int i = 0; i < packets.size(); i++) {
            byte[] packet = packets.get(i);
            CanData canData = getCanData(packet, data);
            canData.setTimestamp(timestamp.plus(1000 * timeInterval * i, ChronoUnit.MILLIS));
            list.add(canData);
        }
        return list;
    }

    private CanData getCanData(byte[] packet, byte[] data) {
        CanData canData = new CanData();
        int index = 0;
        if ((data[6] & 0x80) == 0x80) {
            canData.setSecurityStatus(buildSecurityStatus(packet[index]));
            index++;
        }
        if ((data[6] & 0x40) == 0x40) {
            canData.setDoorStatus(buildDoorStatus(packet[index]));
            index++;
        }
        if ((data[6] & 0x20) == 0x20) {
            canData.setWindowStatus(buildWindowStatus(packet[index]));
            index++;
        }
        if ((data[6] & 0x10) == 0x10) {
            canData.setLockStatus(buildLockStatus(packet[index]));
        }
        if ((data[6] & 0x08) == 0x08) {
            canData.setLightStatus(buildLightStatus(packet[index]));
            index++;
        }
        if ((data[7] & 0x80) == 0x80) {
            canData.setMalfunctionIndicatorStatus((packet[index] & 0x01) == 0x01);
            index++;
        }
        if ((data[7] & 0x40) == 0x40) {
            canData.setDtcNum(toInt(packet[index]));
            index++;
        }
        if ((data[7] & 0x20) == 0x20) {
            canData.setObdType(getObdType(packet[index]));
            index++;
        }
        if ((data[7] & 0x10) == 0x10) {
            canData.setVoltage(MathUtil.precision(0.1 * toLong(packet, index, 2), 1));
            index += 2;
        }
        if ((data[7] & 0x08) == 0x08) {
            canData.setEngineRpm(toInt(packet, index, 2));
            index += 2;
        }
        if ((data[7] & 0x04) == 0x04) {
            canData.setSpeed(toInt(packet[index]));
            index++;
        }
        if ((data[7] & 0x02) == 0x02) {
            canData.setIntakeAirTemp(toInt(packet[index]) - 40);
            index++;
        }
        if ((data[7] & 0x01) == 0x01) {
            canData.setCoolantTemp(toInt(packet[index]) - 40);
            index++;
        }
        if ((data[8] & 0x80) == 0x80) {
            canData.setEnvironmentTemp(toInt(packet[index]) - 40);
            index++;
        }
        if ((data[8] & 0x40) == 0x40) {
            canData.setIntakePressure(toInt(packet[index]));
            index++;
        }
        if ((data[8] & 0x20) == 0x20) {
            canData.setFuelPressure(toInt(packet, index, 2));
            index += 2;
        }
        if ((data[8] & 0x10) == 0x10) {
            canData.setBarometricPressure(toInt(packet[index]));
            index++;
        }
        if ((data[8] & 0x08) == 0x08) {
            canData.setAirflow(MathUtil.precision(0.1 * toLong(packet, index, 2), 1));
            index += 2;
        }
        if ((data[8] & 0x04) == 0x04) {
            canData.setThrottlePosition(MathUtil.precision(0.1 * toLong(packet, index, 2), 1));
            index += 2;
        }
        if ((data[8] & 0x02) == 0x02) {
            canData.setAccPedalPosition(MathUtil.precision(0.1 * toLong(packet, index, 2), 1));
            index += 2;
        }
        if ((data[8] & 0x01) == 0x01) {
            canData.setEngineRunTime(toInt(packet, index, 2));
            index += 2;
        }
        if ((data[9] & 0x80) == 0x80) {
            canData.setFaultMileage(toLong(packet, index, 4));
            index += 4;
        }
        if ((data[9] & 0x40) == 0x40) {
            double innage = 0.1 * ((packet[index] & 0x7f) << 8 | toInt(packet[index + 1]));
            String symbol = (packet[index] & 0x80) == 0x80 ? "L" : "%";
            canData.setInnage(MathUtil.precision(innage, 1) + symbol);
            index += 2;
        }
        if ((data[9] & 0x20) == 0x20) {
            canData.setEngineLoad(toInt(packet[index]));
            index++;
        }
        if ((data[9] & 0x10) == 0x10) {
            canData.setLongTermFuelTrimBank(MathUtil.precision(0.1 * toLong(packet, index, 2) - 100, 1));
            index += 2;
        }
        if ((data[9] & 0x08) == 0x08) {
            canData.setSparkAngleBeforeTdc(MathUtil.precision(0.1 * toLong(packet, index, 2) - 40, 1));
            index += 2;
        }
        if ((data[9] & 0x04) == 0x04) {
            canData.setPanelMileage(toLong(packet, index, 4));
            index += 4;
        }
        if ((data[9] & 0x04) == 0x02) {
            canData.setGear(Gear.ofCode(toInt(packet[index])));
            index++;
        }
        if ((data[11] & 0x40) == 0x40) {
            canData.setTirePressureLF(toInt(packet, index, 2));
            index += 2;
        }
        if ((data[11] & 0x20) == 0x20) {
            canData.setTirePressureRF(toInt(packet, index, 2));
            index += 2;
        }
        if ((data[11] & 0x10) == 0x10) {
            canData.setTirePressureLB(toInt(packet, index, 2));
            index += 2;
        }
        if ((data[11] & 0x08) == 0x08) {
            canData.setTirePressureRB(toInt(packet, index, 2));
            index += 2;
        }
        if ((data[11] & 0x04) == 0x04) {
            canData.setMileageType(MileageType.ofCode(toInt(packet[index])));
            index++;
        }
        if ((data[11] & 0x02) == 0x02) {
            canData.setTotalMileage(toLong(packet, index, 4));
            index += 4;
        }
        if ((data[11] & 0x01) == 0x01) {
            canData.setTotalFuelConsumption(toLong(packet, index, 4));
        }
        return canData;
    }

    private List<byte[]> splitDataPacket(byte[] data) {
        List<byte[]> packets = new ArrayList<>();
        int packetsNum = toInt(data[12]);
        int singlePacketLength = (data.length - 13) / packetsNum;
        for (int i = 13; i < data.length; i += singlePacketLength) {
            packets.add(ByteUtil.subBytes(data, i, singlePacketLength));
        }
        return packets;
    }

    private SecurityStatus buildSecurityStatus(byte data) {
        SecurityStatus securityStatus = new SecurityStatus();
        securityStatus.setAcc((data & 0x80) == 0x80);
        securityStatus.setLock((data & 0x40) == 0x40);
        securityStatus.setArming((data & 0x20) == 0x20);
        securityStatus.setParkingBrake((data & 0x10) == 0x10);
        return securityStatus;
    }

    private DoorStatus buildDoorStatus(byte data) {
        DoorStatus doorStatus = new DoorStatus();
        doorStatus.setLeftFront((data & 0x80) == 0x80);
        doorStatus.setRightFront((data & 0x40) == 0x40);
        doorStatus.setLeftBack((data & 0x20) == 0x20);
        doorStatus.setRightBack((data & 0x10) == 0x10);
        doorStatus.setTrunk((data & 0x08) == 0x08);
        return doorStatus;
    }

    private WindowStatus buildWindowStatus(byte data) {
        WindowStatus windowStatus = new WindowStatus();
        windowStatus.setLeftFront((data & 0x80) == 0x80);
        windowStatus.setRightFront((data & 0x40) == 0x40);
        windowStatus.setLeftBack((data & 0x20) == 0x20);
        windowStatus.setRightBack((data & 0x10) == 0x10);
        return windowStatus;
    }

    private LockStatus buildLockStatus(byte data) {
        LockStatus lockStatus = new LockStatus();
        lockStatus.setLeftFront((data & 0x80) == 0x80);
        lockStatus.setRightFront((data & 0x40) == 0x40);
        lockStatus.setLeftBack((data & 0x20) == 0x20);
        lockStatus.setRightBack((data & 0x10) == 0x10);
        lockStatus.setTruck((data & 0x08) == 0x08);
        return lockStatus;
    }

    private LightStatus buildLightStatus(byte data) {
        LightStatus lightStatus = new LightStatus();
        lightStatus.setHighBeam((data & 0x80) == 0x80);
        lightStatus.setLowBeam((data & 0x40) == 0x40);
        lightStatus.setLeftSignal((data & 0x20) == 0x20);
        lightStatus.setRightSignal((data & 0x10) == 0x10);
        lightStatus.setReversingLight((data & 0x08) == 0x08);
        lightStatus.setBrakeLight((data & 0x04) == 0x04);
        lightStatus.setFogLight((data & 0x02) == 0x02);
        lightStatus.setClearanceLight((data & 0x01) == 0x01);
        return lightStatus;
    }

    private String getObdType(byte code) {
        switch (code) {
            case 0x01:
                return "OBD II";
            case 0x02:
                return "OBD";
            case 0x03:
                return "OBD and OBD II";
            case 0x04:
                return "OBD I";
            case 0x05:
                return "NO OBD";
            case 0x06:
                return "EOBD";
            case 0x07:
                return "EOBD and OBD II";
            case 0x08:
                return "EOBD and OBD";
            case 0x09:
                return "EOBD,OBD and OBD II";
            case 0x0A:
                return "JOBD";
            case 0x0B:
                return "JOBD and OBD II";
            case 0x0C:
                return "JOBD and EOBD";
            case 0x0D:
                return "JOBD,EOBD and OBD II";
            case 0x0E:
                return "EURO IV B1";
            case 0x0F:
                return "EURO V B2";
            case 0x10:
                return "EURO C";
            case 0x11:
                return "EMD";
            case 0x12:
                return "SAE J1939";
            default:
                return "---";
        }
    }
}
