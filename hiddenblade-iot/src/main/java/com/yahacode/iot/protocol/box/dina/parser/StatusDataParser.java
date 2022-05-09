package com.yahacode.iot.protocol.box.dina.parser;

import com.yahacode.hiddenblade.tool.utils.ByteUtil;
import com.yahacode.hiddenblade.tool.utils.MathUtil;
import com.yahacode.iot.protocol.box.dina.data.StatusData;
import com.yahacode.iot.protocol.box.dina.data.sub.ModuleStatus;

public class StatusDataParser extends AbstractDinaParser<StatusData> {

    @Override
    public StatusData parse(byte[] data) {
        StatusData statusData = new StatusData();
        statusData.setTimestamp(getTimestamp(data));
        int number = toInt(data[6]);
        int index = 7;
        for (int i = 0; i < number; i++) {
            int statusId = toInt(data[index]);
            index++;
            int length = toInt(data[index]);
            index++;
            switch (statusId) {
                case 0x01:
                    statusData.setGpsStatus(toInt(data[index]));
                    break;
                case 0x02:
                    statusData.setObdStatus(toInt(data[index]));
                    break;
                case 0x03:
                    statusData.setCommStatus(toInt(data[index]));
                    break;
                case 0x11:
                    statusData.setModuleStatus(getModuleStatus(ByteUtil.subBytes(data, index, length)));
                    break;
                case 0x21:
                    statusData.setBatterySet(toInt(data[index]));
                    break;
                case 0x22:
                    statusData.setObdSet(toInt(data[index]));
                    break;
                case 0x23:
                    statusData.setCommSet(toInt(data[index]));
                    break;
                case 0x24:
                    statusData.setGpsSet(toInt(data[index]));
                    break;
                case 0x25:
                    statusData.setUbiSet(toInt(data[index]));
                    break;
                case 0x26:
                    statusData.setSleepDuration(toInt(data, index, 2));
                    break;
                case 0x27:
                    statusData.setGpsDuration(toInt(data, index, 2));
                    break;
                case 0x28:
                    statusData.setObdDuration(toInt(data, index, 2));
                    break;
                case 0x29:
                    statusData.setAccOnVoltThreshold(MathUtil.precision(0.1 * toInt(data, index, 2), 1));
                    break;
                case 0x2A:
                    statusData.setAccOffVoltThreshold(MathUtil.precision(0.1 * toInt(data, index, 2), 1));
                    break;
                case 0x2B:
                    statusData.setLowPowerVoltThreshold(MathUtil.precision(0.1 * toInt(data, index, 2), 1));
                    break;
                case 0x2C:
                    statusData.setWakeupAccThreshold(toInt(data, index, 2));
                    break;
                case 0x2D:
                    statusData.setSleepAccThreshold(toInt(data, index, 2));
                    break;
                case 0x2E:
                    statusData.setRunningAccThreshold(toInt(data, index, 2));
                    break;
                case 0x2F:
                    statusData.setWifiSet(toInt(data[index]));
                    break;
                case 0x31:
                    statusData.setSmsCenterNum(new String(ByteUtil.subBytes(data, index, length)).trim());
                    break;
                case 0x32:
                    statusData.setGwAddress(new String(ByteUtil.subBytes(data, index, length)).trim());
                    break;
                case 0x33:
                    statusData.setApnInfo(new String(ByteUtil.subBytes(data, index, length)).trim());
                    break;
                case 0x34:
                    statusData.setUpgradeAddress(new String(ByteUtil.subBytes(data, index, length)).trim());
                    break;
                case 0x35:
                    statusData.setSoftwareVersion(new String(ByteUtil.subBytes(data, index, length)).trim());
                    break;
                case 0x36:
                    statusData.setHardwareVersion(new String(ByteUtil.subBytes(data, index, length)).trim());
                    break;
                case 0x37:
                    statusData.setInternationalVersionSet(toInt(data[index]));
                    break;
                case 0x41:
                    statusData.setTrafficModule(toInt(data[index]));
                    break;
                case 0x51:
                    statusData.setColdStartTime(toInt(data, index, 2));
                    break;
                case 0x52:
                    statusData.setWarmStartTime(toInt(data, index, 2));
                    break;
                case 0x53:
                    statusData.setBufferNum(toInt(data, index, 2));
                    break;
                case 0x54:
                    statusData.setSubmeterPositioning(toInt(data[index]));
                    break;
                case 0x61:
                    statusData.setTransEncryption(toInt(data[index]));
                    break;
                case 0x62:
                    statusData.setProtocolType(toInt(data[index]));
                    break;
                default:
                    break;
            }
            index += length;
        }
        return statusData;
    }

    private ModuleStatus getModuleStatus(byte[] data) {
        ModuleStatus moduleStatus = new ModuleStatus();
        moduleStatus.setGps(String.valueOf((data[0] >> 6) & 0x03));
        moduleStatus.setComm(String.valueOf((data[0] >> 4) & 0x03));
        moduleStatus.setFlash(String.valueOf((data[0] >> 2) & 0x03));
        moduleStatus.setThreeD(String.valueOf(data[0] & 0x03));
        moduleStatus.setObd(String.valueOf((data[1] >> 6) & 0x03));
        moduleStatus.setBattery(String.valueOf((data[1] >> 4) & 0x03));
        moduleStatus.setWifi(String.valueOf((data[1] >> 2) & 0x03));
        moduleStatus.setTf(String.valueOf(data[1] & 0x03));
        moduleStatus.setCamera1(String.valueOf((data[2] >> 6) & 0x03));
        moduleStatus.setCamera2(String.valueOf((data[2] >> 4) & 0x03));
        return moduleStatus;
    }
}
