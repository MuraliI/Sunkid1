package com.rcl.excalibur.utils;


import android.util.Base64;

import java.io.UnsupportedEncodingException;

import timber.log.Timber;

public final class IntentExtraUtils {


    private static final String OUTPUT_CHARSET = "UTF-8";

    private IntentExtraUtils() {
    }

    public static String encodePutExtraString(String string) {
        byte[] data = new byte[0];
        try {
            data = string.getBytes(OUTPUT_CHARSET);
        } catch (UnsupportedEncodingException e) {
            Timber.e("error encoding String", e.getMessage());
        }
        return Base64.encodeToString(data, Base64.DEFAULT);
    }

    public static String decodeGetExtraString(String string) {
        byte[] data = Base64.decode(string, Base64.DEFAULT);
        String stringExtra = null;
        try {
            stringExtra = new String(data, OUTPUT_CHARSET);
        } catch (UnsupportedEncodingException e) {
            Timber.e("error decoding String", e.getMessage());
        }
        return stringExtra;
    }
}
