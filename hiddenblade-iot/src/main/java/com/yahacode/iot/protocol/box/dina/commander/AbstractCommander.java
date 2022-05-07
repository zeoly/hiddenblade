package com.yahacode.iot.protocol.box.dina.commander;

import com.yahacode.hiddenblade.tool.utils.ByteUtil;
import com.yahacode.iot.protocol.TerminalCommander;
import com.yahacode.iot.protocol.box.dina.DinaMessage;
import com.yahacode.iot.protocol.box.dina.data.BaseCommand;

import java.util.Random;

public abstract class AbstractCommander<T extends BaseCommand> implements TerminalCommander<T> {

    @Override
    public byte[] encode(T baseCommand) {
        byte[] commandBody = getCommandBody(baseCommand);
        byte[] command = new byte[DinaMessage.MIN_LENGTH + commandBody.length];
        command[0] = (byte) DinaMessage.HEADER;
        command[1] = (byte) DinaMessage.HEADER;
        command[2] = (byte) (new Random().nextInt(256) & 0xff);
        command[3] = (byte) getCommandId();
        command[4] = (byte) ((Long.parseLong(baseCommand.getDeviceId()) >> 40) & 0xff);
        command[5] = (byte) ((Long.parseLong(baseCommand.getDeviceId()) >> 32) & 0xff);
        command[6] = (byte) ((Long.parseLong(baseCommand.getDeviceId()) >> 24) & 0xff);
        command[7] = (byte) ((Long.parseLong(baseCommand.getDeviceId()) >> 16) & 0xff);
        command[8] = (byte) ((Long.parseLong(baseCommand.getDeviceId()) >> 8) & 0xff);
        command[9] = (byte) (Long.parseLong(baseCommand.getDeviceId()) & 0xff);
        command[10] = (byte) ((commandBody.length >> 8) & 0xff);
        command[11] = (byte) (commandBody.length & 0xff);
        System.arraycopy(commandBody, 0, command, 12, commandBody.length);
        command[command.length - 2] = ByteUtil.parityCheck(command, command.length - 3);
        command[command.length - 1] = (byte) DinaMessage.FOOTER;
        return command;
    }

    public abstract T getData(String deviceId, String str);

    public abstract byte[] getCommandBody(T command);

    public abstract int getCommandId();
}
