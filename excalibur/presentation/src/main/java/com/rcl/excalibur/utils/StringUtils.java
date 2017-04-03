package com.rcl.excalibur.utils;


import android.os.Build;
import android.text.Html;
import android.text.Spanned;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class StringUtils {

    private static final String EMAILPATTERN = "^[A-Z0-9_%+-]+@[A-Z0-9]+\\.[A-Z]{2,6}$";

    private StringUtils() {

    }

    @SuppressWarnings("deprecation")
    public static Spanned fromHtml(String htmlFormattedString) {
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.N) {
            return Html.fromHtml(htmlFormattedString, Html.FROM_HTML_MODE_COMPACT);
        } else {
            return Html.fromHtml(htmlFormattedString);
        }
    }

    public static String getPriceFormated(float price) {
        if (price - (int) price > 0) {
            return Float.toString(price);
        }
        return Integer.toString((int) price);
    }

    public static boolean isValidEmail(String email) {
        Pattern validEmailAddressRegex = Pattern.compile(EMAILPATTERN, Pattern.CASE_INSENSITIVE);
        Matcher matcher = validEmailAddressRegex.matcher(email);
        return matcher.find();
    }
}
