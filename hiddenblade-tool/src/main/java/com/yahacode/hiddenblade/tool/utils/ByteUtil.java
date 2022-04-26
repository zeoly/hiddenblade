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

    public static byte[] subBytes(byte[] data, int start, int length) {
        byte[] result = new byte[length];
        if ((null != data) && (start < data.length) && (start + length <= data.length)) {
            System.arraycopy(data, start, result, 0, length);
        }
        return result;
    }
}
