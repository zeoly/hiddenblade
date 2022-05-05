package com.yahacode.iot.protocol.box.dina.responder;

import com.yahacode.iot.protocol.box.dina.DinaMessage;

public class SettingParamResponder extends AbstractMessageResponder {

    @Override
    public int getResponseId() {
        return 0x83;
    }

    @Override
    public byte[] getRespondBody(DinaMessage message) {
        byte[] body = new byte[1];
        body[0] = 0x00;
        return body;
    }
}
