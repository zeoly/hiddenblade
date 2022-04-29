package com.yahacode.iot.protocol.box.dina.parser;

import com.yahacode.hiddenblade.tool.utils.ByteUtil;
import com.yahacode.iot.protocol.box.dina.data.ExtendEventData;
import com.yahacode.iot.protocol.box.dina.data.GpsData;
import com.yahacode.iot.protocol.box.dina.data.sub.ExtendEvent;
import com.yahacode.iot.protocol.box.dina.data.sub.ExtendEventContent;
import com.yahacode.iot.protocol.box.dina.data.sub.ExtendEventContentObd;
import org.springframework.beans.BeanUtils;

public class ExtendEventDataParser extends AbstractDinaParser<ExtendEventData> {

    GpsDataParser gpsDataParser = new GpsDataParser();

    @Override
    public ExtendEventData parse(byte[] data) {
        ExtendEventData extendEventData = new ExtendEventData();
        GpsData gpsData = gpsDataParser.parse(data);
        BeanUtils.copyProperties(gpsData, extendEventData);
        int index = 26;
        extendEventData.setEventNumber(toInt(data[index]));
        index++;
        for (int i = 0; i < extendEventData.getEventNumber(); i++) {
            ExtendEvent extendEvent = new ExtendEvent();
            extendEvent.setType(ByteUtil.bytesToHexString(ByteUtil.subBytes(data, index, 4)));
            index += 4;
            extendEvent.setLength(toInt(data, index, 2));
            index += 2;
//            extendEvent.setContent(parseContent(ByteUtil.subBytes(data, index, extendEvent.getLength())));
            extendEvent.setContent(ByteUtil.bytesToHexString(ByteUtil.subBytes(data, index, extendEvent.getLength())));
            index += extendEvent.getLength();
            extendEventData.getEventList().add(extendEvent);
        }
        return extendEventData;
    }

    private ExtendEventContent parseContent(byte[] data) {
        ExtendEventContent content = new ExtendEventContent();
        content.setObdNumber(toInt(data[0]));
        int index = 1;
        for (int i = 0; i < content.getObdNumber(); i++) {
            ExtendEventContentObd obd = new ExtendEventContentObd();
            obd.setFlag(toInt(data[index]));
            index++;
            obd.setLength(toInt(data[index]));
            index++;
            obd.setContent(ByteUtil.bytesToHexString(ByteUtil.subBytes(data, index, obd.getLength())));
            index += obd.getLength();
            content.getObdList().add(obd);
        }
        return content;
    }
}
