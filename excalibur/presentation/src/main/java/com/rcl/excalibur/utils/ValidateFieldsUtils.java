package com.rcl.excalibur.utils;


import android.content.Context;

import com.rcl.excalibur.R;

public final class ValidateFieldsUtils {
    private static final int MIN_CHAR = 7;
    private static final String WHITESPACE = " ";

    private ValidateFieldsUtils() {
    }

    public static String isValidatePassword(String password, Context context) {
        boolean hasUppercase = password.equals(password.toLowerCase());
        boolean hasLowercase = password.equals(password.toUpperCase());
        boolean isAtLeast7   = password.length() <= MIN_CHAR;
        boolean hasSpecial   = !password.matches(".*[!@#$%^&*].*");

        StringBuilder stringBuilder = new StringBuilder();

        if (hasUppercase)
            stringBuilder.append(context.getString(R.string.must_uppercase)).append(WHITESPACE);

        if (hasLowercase)
            stringBuilder.append(context.getString(R.string.must_lowercase)).append(WHITESPACE);

        if (isAtLeast7)
            stringBuilder.append(context.getString(R.string.min_characters)).append(WHITESPACE);

        if (hasSpecial)
            stringBuilder.append(context.getString(R.string.must_special_character)).append(WHITESPACE);


        return stringBuilder.toString().trim();
    }
}
