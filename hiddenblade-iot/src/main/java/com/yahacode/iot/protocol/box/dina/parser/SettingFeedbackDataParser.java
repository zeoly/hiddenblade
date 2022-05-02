package com.yahacode.iot.protocol.box.dina.parser;

import com.yahacode.iot.protocol.box.dina.data.SettingFeedbackData;

public class SettingFeedbackDataParser extends AbstractDinaParser<SettingFeedbackData> {

    @Override
    public SettingFeedbackData parse(byte[] data) {
        SettingFeedbackData settingFeedbackData = new SettingFeedbackData();
        if (toInt(data[0]) == 0) {
            settingFeedbackData.setSuccess(Boolean.TRUE);
        }
        if (toInt(data[0]) == 1) {
            settingFeedbackData.setSuccess(Boolean.FALSE);
        }
        return settingFeedbackData;
    }
}
