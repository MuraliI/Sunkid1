package com.rcl.excalibur.mvp.presenter.guest;


import android.content.Context;
import android.graphics.Typeface;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.style.StyleSpan;

import com.rcl.excalibur.R;
import com.rcl.excalibur.activity.BaseActivity;
import com.rcl.excalibur.activity.guest.SecurityQuestionsActivity;
import com.rcl.excalibur.domain.interactor.GetGuestPreferencesUseCase;
import com.rcl.excalibur.mvp.view.guest.PasswordView;
import com.rcl.excalibur.utils.ActivityUtils;

public class PasswordPresenter {
    private static final int MIN_CHAR = 7;
    private static final String REGEX = ".*[$&+,:;=?@#|/'<>.^*()%!-].*";
    private PasswordView view;
    private GetGuestPreferencesUseCase getGuestPreferencesUseCase;

    public PasswordPresenter(PasswordView view, GetGuestPreferencesUseCase getGuestPreferencesUseCase) {
        this.view = view;
        this.getGuestPreferencesUseCase = getGuestPreferencesUseCase;
    }

    private static SpannableString getSpannableString(String errorString, boolean isError) {
        SpannableString spannableString = new SpannableString(errorString);
        spannableString.setSpan(isError ? new StyleSpan(Typeface.BOLD) : null, 0, spannableString.length(), 0);
        return spannableString;
    }

    public void onHeaderBackOnClick() {
        final BaseActivity activity = view.getActivity();
        if (activity == null) {
            return;
        }
        ActivityUtils.onBackActivity(activity);
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

    private SpannableStringBuilder isValidPassword(String password, Context context) {
        boolean isAtLeast7 = password.length() <= MIN_CHAR;
        boolean hasSpecial = !password.matches(REGEX);

        String minChars = context.getString(R.string.min_characters);
        String specialChars = context.getString(R.string.must_special_character);
        String whiteSpace = context.getString(R.string.space_string);

        SpannableStringBuilder builder = new SpannableStringBuilder();
        builder.append(getSpannableString(minChars, isAtLeast7));
        builder.append(whiteSpace);
        builder.append(getSpannableString(specialChars, hasSpecial));

        return builder;
    }

    private boolean isValidateNext(String password) {
        boolean isAtLeast7 = password.length() <= MIN_CHAR;
        boolean hasSpecial = !password.matches(REGEX);

        return !isAtLeast7 && !hasSpecial;
    }

    public void hideKeyBoard() {
        view.hideKeyboard();
    }

    public boolean isValidData() {
        return view.isValidData();
    }

    public void onClickImageViewNext() {
        final BaseActivity activity = view.getActivity();
        if (activity == null) {
            return;
        }

        getGuestPreferencesUseCase.putPassword(view.getPassword());
        ActivityUtils.startActivity(activity, SecurityQuestionsActivity.getActivityIntent(activity));
    }
}
