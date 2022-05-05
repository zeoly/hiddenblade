package com.yahacode.iot.protocol.box.dina.responder;

import com.yahacode.hiddenblade.tool.utils.ByteUtil;
import com.yahacode.iot.protocol.box.dina.DinaMessage;

public abstract class AbstractMessageResponder implements MessageResponder {

    @Override
    public byte[] respond(DinaMessage message) {
        byte[] body = getRespondBody(message);
        byte[] response = new byte[DinaMessage.MIN_LENGTH + body.length];

        response[0] = (byte) DinaMessage.HEADER;
        response[1] = (byte) DinaMessage.HEADER;
        response[2] = (byte) (message.getSerial() & 0xff);
        response[3] = (byte) getResponseId();
        response[4] = (byte) ((Long.parseLong(message.getDeviceId()) >> 40) & 0xff);
        response[5] = (byte) ((Long.parseLong(message.getDeviceId()) >> 32) & 0xff);
        response[6] = (byte) ((Long.parseLong(message.getDeviceId()) >> 24) & 0xff);
        response[7] = (byte) ((Long.parseLong(message.getDeviceId()) >> 16) & 0xff);
        response[8] = (byte) ((Long.parseLong(message.getDeviceId()) >> 8) & 0xff);
        response[9] = (byte) (Long.parseLong(message.getDeviceId()) & 0xff);
        response[10] = (byte) ((body.length >> 8) & 0xff);
        response[11] = (byte) (body.length & 0xff);
        System.arraycopy(body, 0, response, 12, body.length);
        response[response.length - 2] = ByteUtil.parityCheck(response, response.length - 3);
        response[response.length - 1] = (byte) DinaMessage.FOOTER;
        return response;
    }

    public abstract int getResponseId();

    public abstract byte[] getRespondBody(DinaMessage message);
}
