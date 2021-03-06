package com.rcl.excalibur.mvp.presenter.guest;


import android.text.TextUtils;

import com.rcl.excalibur.activity.guest.EmailActivity;
import com.rcl.excalibur.activity.guest.NameActivity;
import com.rcl.excalibur.data.utils.StringUtils;
import com.rcl.excalibur.domain.interactor.GetGuestPreferencesUseCase;
import com.rcl.excalibur.mvp.view.guest.NameView;
import com.rcl.excalibur.utils.ActivityUtils;

import static com.rcl.excalibur.data.utils.StringUtils.SPLIT_SEPARATOR;
import static com.rcl.excalibur.data.utils.StringUtils.capitalizeAllWords;
import static com.rcl.excalibur.data.utils.StringUtils.removeBarreled;

public class NamePresenter {
    protected static final String BRAND = "r";
    protected static final String VERSION = "1.0";
    protected static final int LIMIT_MAX = 50;
    protected boolean canChange = true;
    private NameView view;
    private GetGuestPreferencesUseCase getGuestPreferencesUseCase;

    public NamePresenter(NameView view, GetGuestPreferencesUseCase getGuestPreferencesUseCase) {
        this.view = view;
        this.getGuestPreferencesUseCase = getGuestPreferencesUseCase;
    }

    public void init() {
        getGuestPreferencesUseCase.putBrand(BRAND);
        getGuestPreferencesUseCase.putVersion(VERSION);
        getGuestPreferencesUseCase.putAcceptTime(System.currentTimeMillis());
        view.setNextButton(false);
    }

    public void onArrowBack() {
        final NameActivity activity = view.getActivity();
        if (activity == null) {
            return;
        }
        activity.finish();
    }

    public void onNameChanged() {
        if (!canChange) {
            return;
        }
        try {
            canChange = false;
            final String valueWithoutBarreled = removeBarreled(view.getFullName());
            final String valueCapitalize = capitalizeAllWords(valueWithoutBarreled);

            view.changeValue(valueCapitalize);
            view.setNextButton(validate(valueCapitalize));
        } finally {
            canChange = true;
        }
    }


    private boolean validate(String value) {

        if (TextUtils.isEmpty(value)) {
            return false;
        }
//        A Guest shall be able to enter a first name composed of any English characters, given that they are not all whitespace characters
//        A Guest shall be able to enter a last name composed of any English characters, given that they are not all whitespace characters
        if (!StringUtils.applyRegex(StringUtils.REGEX_FULL_NAME, value)) {
//            view.showError(R.string.guest_error_name_empty);
            return false;
        }
        final String[] values = value.split(SPLIT_SEPARATOR);
        int length = 0;
        for (String v : values) {
            length += v.length();
        }
//        A Guest shall be able to enter a first name that is 50 or less characters
        return length <= LIMIT_MAX;
    }

    public void hideKeyboard() {
        view.hideKeyboard();
    }


    public void onNextClick() {
        String fullName = view.getFullName();
        if (!validate(fullName)) {
            return;
        }
        final NameActivity activity = view.getActivity();
        if (activity == null) {
            return;
        }
        //TODO view this repeat the name and the lastname
        getGuestPreferencesUseCase.putName(fullName);
        getGuestPreferencesUseCase.putLastname(fullName);
        ActivityUtils.startActivity(activity, EmailActivity.getStartIntent(activity));
    }
}
