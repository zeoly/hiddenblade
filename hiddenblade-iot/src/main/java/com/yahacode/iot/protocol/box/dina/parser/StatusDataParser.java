package com.yahacode.iot.protocol.box.dina.parser;

import com.yahacode.hiddenblade.tool.utils.ByteUtil;
import com.yahacode.iot.protocol.box.dina.data.StatusData;
import com.yahacode.iot.protocol.box.dina.data.sub.Status;

public class StatusDataParser extends AbstractDinaParser<StatusData> {

    @Override
    public StatusData parse(byte[] data) {
        StatusData statusData = new StatusData();
        statusData.setTimestamp(getTimestamp(data));
        statusData.setStatusNumber(toInt(data[6]));
        int index = 7;
        for (int i = 0; i < statusData.getStatusNumber(); i++) {
            Status status = new Status();
            status.setStatusId(toInt(data[index]));
            index++;
            status.setLength(toInt(data[index]));
            index++;
            status.setContent(ByteUtil.bytesToHexString(ByteUtil.subBytes(data, index, status.getLength())));
            index += status.getLength();
            statusData.getStatusList().add(status);
        }
        return statusData;
    }
}
