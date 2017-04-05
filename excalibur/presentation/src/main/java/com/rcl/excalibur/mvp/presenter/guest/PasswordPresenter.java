package com.rcl.excalibur.mvp.presenter.guest;


import android.content.Context;
import android.graphics.Typeface;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.style.StyleSpan;

import com.rcl.excalibur.R;
import com.rcl.excalibur.activity.BaseActivity;
import com.rcl.excalibur.mvp.presenter.ActivityPresenter;
import com.rcl.excalibur.mvp.view.base.ActivityView;
import com.rcl.excalibur.mvp.view.guest.PasswordView;
import com.rcl.excalibur.utils.ActivityUtils;

public class PasswordPresenter implements ActivityPresenter {
    private static final int MIN_CHAR = 7;
    private static final String REGEX = ".*[$&+,:;=?@#|/'<>.^*()%!-].*";
    private PasswordView view;

    public PasswordPresenter(PasswordView view) {
        this.view = view;
    }

    public void onHeaderBackOnClick() {
        final BaseActivity activity = view.getActivity();
        if (activity == null) {
            return;
        }
        ActivityUtils.onBackActivity(activity);
    }

    public void setFocus(boolean hasFocus) {
        final BaseActivity activity = view.getActivity();
        if (activity == null) {
            return;
        }
        view.setHint(activity.getString(hasFocus ? R.string.empty_string : R.string.hint_password));
        verifyPassword();
    }

    public void verifyPassword() {
        final BaseActivity activity = view.getActivity();
        if (activity == null) {
            return;
        }
        view.setError(isValidPassword(view.getPassword(), activity));
        view.setNextButton(isValidateNext(view.getPassword()));
    }

    public void setVisibilityPassword(boolean isChecked) {
        if (isChecked)
            view.setVisiblePassword();
        else
            view.setInvisiblePassword();
    }

    @Override
    public ActivityView getView() {
        return view;
    }

    private SpannableStringBuilder isValidPassword(String password, Context context) {
        boolean hasUppercase = password.equals(password.toLowerCase());
        boolean hasLowercase = password.equals(password.toUpperCase());
        boolean isAtLeast7 = password.length() <= MIN_CHAR;
        boolean hasSpecial = !password.matches(REGEX);

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

    private boolean isValidateNext(String password) {
        boolean hasUppercase = password.equals(password.toLowerCase());
        boolean hasLowercase = password.equals(password.toUpperCase());
        boolean isAtLeast7 = password.length() <= MIN_CHAR;
        boolean hasSpecial = !password.matches(REGEX);

        return !isAtLeast7 && !hasUppercase && !hasLowercase && !hasSpecial;
    }

    private static SpannableString getSpannableString(String errorString, boolean isError) {
        SpannableString spannableString = new SpannableString(errorString);
        spannableString.setSpan(isError ? new StyleSpan(Typeface.BOLD) : null, 0, spannableString.length(), 0);
        return spannableString;
    }
}
