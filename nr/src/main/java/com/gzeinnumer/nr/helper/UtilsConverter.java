package com.gzeinnumer.nr.helper;

import android.nfc.Tag;

public class UtilsConverter {

    // Convert data RFID to ID (hex)
    private static String toHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (int i = bytes.length - 1; i >= 0; --i) {
            int b = bytes[i] & 0xff;
            if (b < 0x10)
                sb.append('0');
            sb.append(Integer.toHexString(b));
            if (i > 0) {
                sb.append(" ");
            }
        }
        return sb.toString();
    }

    // Convert data RFID to ID (reversed hex)
    private static String toReversedHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < bytes.length; ++i) {
            if (i > 0) {
                sb.append(" ");
            }
            int b = bytes[i] & 0xff;
            if (b < 0x10)
                sb.append('0');
            sb.append(Integer.toHexString(b));
        }
        return sb.toString();
    }

    // Convert data RFID to ID (dec)
    private static long toDec(byte[] bytes) {
        long result = 0;
        long factor = 1;
        for (int i = 0; i < bytes.length; ++i) {
            long value = bytes[i] & 0xffl;
            result += value * factor;
            factor *= 256l;
        }
        return result;
    }

    // Convert data RFID to ID (reversed dec)
    private static long toReversedDec(byte[] bytes) {
        long result = 0;
        long factor = 1;
        for (int i = bytes.length - 1; i >= 0; --i) {
            long value = bytes[i] & 0xffL;
            result += value * factor;
            factor *= 256L;
        }
        return result;
    }

    // Get ID
    public static String getIdHex(Tag tag) {
        return toHex(tag.getId())
                .replace(" ", "");
    }

    public static String getIdReversedHex(Tag tag) {
        return toReversedHex(tag.getId())
                .replace(" ", "");
    }

    public static String getIdDec(Tag tag) {
        return String.valueOf(toDec(tag.getId()));
    }

    public static String getIdReversedDec(Tag tag) {
        return String.valueOf(toReversedDec(tag.getId()));
    }
    // End get ID
}
