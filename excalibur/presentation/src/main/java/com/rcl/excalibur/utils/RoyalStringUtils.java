package com.rcl.excalibur.utils;


import android.os.Build;
import android.text.Html;
import android.text.Spanned;

public final class RoyalStringUtils {

    private RoyalStringUtils() {

    }

    @SuppressWarnings("deprecation")
    public static Spanned fromHtml(String htmlFormattedString) {
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.N) {
            return Html.fromHtml(htmlFormattedString, Html.FROM_HTML_MODE_COMPACT);
        } else {
            return Html.fromHtml(htmlFormattedString);
        }
    }
}
