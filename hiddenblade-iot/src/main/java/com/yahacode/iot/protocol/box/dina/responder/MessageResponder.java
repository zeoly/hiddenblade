package com.yahacode.iot.protocol.box.dina.responder;

import com.yahacode.iot.protocol.box.dina.DinaMessage;

public interface MessageResponder {

    byte[] respond(DinaMessage message);
}
