package com.rcl.excalibur.utils;


import android.util.Base64;

import java.io.UnsupportedEncodingException;

public class PutExtraUtils {

    public static String putExtraString(String string )
    {
        byte[] data = new byte[0];
        try {
            data = string.getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return Base64.encodeToString(data, Base64.DEFAULT);
    }
}
