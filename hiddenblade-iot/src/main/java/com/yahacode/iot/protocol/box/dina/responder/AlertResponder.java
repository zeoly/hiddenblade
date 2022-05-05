package com.yahacode.iot.protocol.box.dina.responder;

import com.yahacode.hiddenblade.tool.utils.ByteUtil;
import com.yahacode.iot.protocol.box.dina.DinaMessage;

public class AlertResponder extends AbstractMessageResponder {

    @Override
    public int getResponseId() {
        return 0xA4;
    }

    @Override
    public byte[] getRespondBody(DinaMessage message) {
        return ByteUtil.subBytes(message.getData(), 26, 3);
    }
}
