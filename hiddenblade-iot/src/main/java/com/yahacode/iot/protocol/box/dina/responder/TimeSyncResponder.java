package com.yahacode.iot.protocol.box.dina.responder;

import com.yahacode.iot.protocol.box.dina.DinaMessage;

import java.time.LocalDateTime;

public class TimeSyncResponder extends AbstractMessageResponder {

    @Override
    public int getResponseId() {
        return 0x84;
    }

    @Override
    public byte[] getRespondBody(DinaMessage message) {
        byte[] body = new byte[6];
        LocalDateTime now = LocalDateTime.now().minusHours(8);
        body[0] = (byte) (now.getHour() & 0xff);
        body[1] = (byte) (now.getMinute() & 0xff);
        body[2] = (byte) (now.getSecond() & 0xff);
        body[3] = (byte) ((now.getYear() - 2000) & 0xff);
        body[4] = (byte) (now.getMonthValue() & 0xff);
        body[5] = (byte) (now.getDayOfMonth() & 0xff);
        return body;
    }
}
