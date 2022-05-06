package com.yahacode.iot.protocol.box.dina.parser;

import com.yahacode.hiddenblade.tool.utils.MathUtil;
import com.yahacode.iot.protocol.box.dina.data.LowVoltageParamData;

public class LowVoltageAlertParamDataParser extends AbstractDinaParser<LowVoltageParamData> {

    @Override
    public LowVoltageParamData parse(byte[] data) {
        LowVoltageParamData lowVoltageParamData = new LowVoltageParamData();
        lowVoltageParamData.setEnable(toInt(data[1]) == 1);
        lowVoltageParamData.setAlertVoltage(MathUtil.precision(0.1 * toInt(data, 1, 2), 1));
        return lowVoltageParamData;
    }
}
