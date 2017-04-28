package com.rcl.excalibur.data.utils;


import android.os.Build;
import android.support.annotation.NonNull;
import android.text.Html;
import android.text.Spanned;
import android.text.TextUtils;
import android.util.Base64;

import java.io.UnsupportedEncodingException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import timber.log.Timber;

public final class StringUtils {

    public static final String SPLIT_SEPARATOR = " ";
    public static final String REGEX_FULL_NAME = "^[a-zA-Z0-9 - ! # $ % & ' * + - / = ? ^ _ ` { | } ~]*$";
    private static final String EMAILPATTERN = "^[A-Z0-9_%+-]+@[A-Z0-9]+\\.[A-Z]{2,6}$";
    private static final String OUTPUT_CHARSET = "UTF-8";
    public static final String ERROR_ENCODING_STRING = "error encoding String";
    public static final String ERROR_DECODING_STRING = "error decoding String";

    private StringUtils() {

    }

    public static boolean applyRegex(@NonNull final String regex, @NonNull final String value) {
        final Pattern p = Pattern.compile(regex);
        final Matcher m = p.matcher(value);
        return m.matches();
    }

    @SuppressWarnings("deprecation")
    public static Spanned fromHtml(String htmlFormattedString) {
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.N) {
            return Html.fromHtml(htmlFormattedString, Html.FROM_HTML_MODE_COMPACT);
        } else {
            return Html.fromHtml(htmlFormattedString);
        }
    }

    public static String getPriceFormatted(float price) {
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

    public static String removeBarreled(String value) {
        if (TextUtils.isEmpty(value)) {
            return null;
        }
        return value.replace("-", " ");

    }

    public static String capitalizeAllWords(final String value) {
        if (TextUtils.isEmpty(value)) {
            return null;
        }

        final StringBuilder builder = new StringBuilder();
        final String[] values = value.split(SPLIT_SEPARATOR);
        for (int i = 0; i < values.length; i++) {
            String v = values[i];
            if (TextUtils.isEmpty(v)) {
                continue;
            }
            if (i > 0) {
                builder.append(SPLIT_SEPARATOR);
            }
            builder.append(v.substring(0, 1).toUpperCase());
            builder.append(v.substring(1).toLowerCase());
        }
        if (value.endsWith(SPLIT_SEPARATOR)) {
            builder.append(SPLIT_SEPARATOR);
        }
        return builder.toString();

    }

    public static String encodeString(String string) {
        if (TextUtils.isEmpty(string)) {
            return null;
        }

        try {
            byte[] data = string.getBytes(OUTPUT_CHARSET);
            return Base64.encodeToString(data, Base64.DEFAULT);

        } catch (UnsupportedEncodingException e) {
            Timber.e(ERROR_ENCODING_STRING, e.getMessage());
        }
        return null;
    }

    public static String decodeString(String string) {
        if (string == null) {
            return null;
        }

        byte[] data = Base64.decode(string, Base64.DEFAULT);
        String stringExtra = null;
        try {
            stringExtra = new String(data, OUTPUT_CHARSET);
        } catch (UnsupportedEncodingException e) {
            Timber.e(ERROR_DECODING_STRING, e.getMessage());
        }
        return stringExtra;
    }
}
