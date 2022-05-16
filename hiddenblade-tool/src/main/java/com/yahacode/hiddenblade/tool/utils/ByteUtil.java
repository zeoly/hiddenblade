package com.yahacode.hiddenblade.tool.utils;

public class ByteUtil {

    public static byte[] hexStringToBytes(String hexString) {
        int len = hexString.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(hexString.charAt(i), 16) << 4) + Character.digit(hexString.charAt(i + 1), 16));
        }
        return data;
    }

    public static String bytesToHexString(byte[] bytes) {
        StringBuffer stringBuffer = new StringBuffer(bytes.length);
        for (int i = 0; i < bytes.length; i++) {
            String s = Integer.toHexString(0xff & bytes[i]);
            if (s.length() < 2) {
                stringBuffer.append("0");
            }
            stringBuffer.append(s.toUpperCase());
        }
        return stringBuffer.toString();
    }

    public static String formatBytes(byte[] bytes) {
        if (bytes == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("\n");
        int line = (bytes.length % 16 == 0) ? bytes.length / 16 : bytes.length / 16 + 1;
        for (int i = 0; i < line; i++) {
            int length = 16;
            byte[] lineBytes = new byte[length];
            if (i == line - 1) {
                length = bytes.length - length * i;
            }
            System.arraycopy(bytes, i * 16, lineBytes, 0, length);
            String lineHex = bytesToHexString(lineBytes);
            for (int x = 0; x < lineHex.length(); x += 2) {
                sb.append(lineHex, x, x + 2).append(" ");
            }
            sb.append("    ").append(replaceControlChar(lineBytes)).append("\n");
        }
        return sb.toString();
    }

    private static String replaceControlChar(byte[] data) {
        for (int i = 0; i < data.length; i++) {
            if ((data[i] < 32) || (data[i] == 127)) {
                data[i] = 63;
            }
        }
        return new String(data);
    }

    public static byte[] subBytes(byte[] data, int start, int length) {
        byte[] result = new byte[length];
        if ((null != data) && (start < data.length) && (start + length <= data.length)) {
            System.arraycopy(data, start, result, 0, length);
        }
        return result;
    }

    public static byte parityCheck(byte[] data, int endIndex) {
        byte parity = 0;
        for (int i = 0; i <= endIndex; i++) {
            parity = (byte) (parity ^ data[i]);
        }
        return parity;
    }

    public static String getEighthBit(int b) {
        b |= 256;
        String str = Integer.toBinaryString(b);
        int len = str.length();
        return str.substring(len - 8, len);
    }
}
