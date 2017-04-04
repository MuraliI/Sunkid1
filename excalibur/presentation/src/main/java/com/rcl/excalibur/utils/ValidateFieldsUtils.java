package com.rcl.excalibur.utils;


import android.content.Context;
import android.graphics.Typeface;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.style.StyleSpan;

import com.rcl.excalibur.R;

public final class ValidateFieldsUtils {
    private static final int MIN_CHAR = 7;

    private ValidateFieldsUtils() {
    }

    public static SpannableStringBuilder isValidatePassword(String password, Context context) {
        boolean hasUppercase = password.equals(password.toLowerCase());
        boolean hasLowercase = password.equals(password.toUpperCase());
        boolean isAtLeast7 = password.length() <= MIN_CHAR;
        boolean hasSpecial = !password.matches(".*[!@#$%^&*].*");

        String minChars = context.getString(R.string.min_characters);
        String uppercaseChars = context.getString(R.string.must_uppercase);
        String lowercaseChars = context.getString(R.string.must_lowercase);
        String specialChars = context.getString(R.string.must_special_character);
        String whiteSpace = context.getString(R.string.space_string);

        SpannableStringBuilder builder = new SpannableStringBuilder();
        builder.append(getSpannableString(minChars, isAtLeast7));
        builder.append(whiteSpace);
        builder.append(getSpannableString(uppercaseChars, hasUppercase));
        builder.append(whiteSpace);
        builder.append(getSpannableString(lowercaseChars, hasLowercase));
        builder.append(whiteSpace);
        builder.append(getSpannableString(specialChars, hasSpecial));

        return builder;
    }

    public static boolean isValidateNext(String password) {
        boolean hasUppercase = password.equals(password.toLowerCase());
        boolean hasLowercase = password.equals(password.toUpperCase());
        boolean isAtLeast7 = password.length() <= MIN_CHAR;
        boolean hasSpecial = !password.matches(".*[!@#$%^&*].*");

        return !isAtLeast7 && !hasUppercase && !hasLowercase && !hasSpecial;
    }

    private static SpannableString getSpannableString(String errorString, boolean isError) {
        SpannableString spannableString = new SpannableString(errorString);
        spannableString.setSpan(isError ? new StyleSpan(Typeface.BOLD) : null, 0, spannableString.length(), 0);
        return spannableString;
    }
}
