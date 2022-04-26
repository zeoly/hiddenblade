package com.yahacode.iot.protocol.box.dina.parser;

import com.yahacode.hiddenblade.tool.utils.ByteUtil;
import com.yahacode.iot.protocol.box.dina.data.DtcData;

public class DtcDataParser extends AbstractDinaParser<DtcData> {

    @Override
    public DtcData parse(byte[] data) {
        DtcData dtcData = new DtcData();
        dtcData.setTimestamp(getTimestamp(data));
        dtcData.setDtcNumber(toInt(data[6]));
        int index = 7;
        for (int i = 0; i < dtcData.getDtcNumber(); i++) {
            dtcData.getDtcList().add(ByteUtil.bytesToHexString(ByteUtil.subBytes(data, index, 5)));
            index += 5;
        }
        return dtcData;
    }
}
