package com.yahacode.iot.protocol.box.dina.responder;

import com.yahacode.iot.protocol.box.dina.DinaMessage;

public class CommonResponder extends AbstractMessageResponder {

    @Override
    public int getResponseId() {
        return 0x80;
    }

    @Override
    public byte[] getRespondBody(DinaMessage message) {
        byte[] body = new byte[1];
        body[0] = (byte) (message.getFunctionId() & 0xff);
        return body;
    }
}
