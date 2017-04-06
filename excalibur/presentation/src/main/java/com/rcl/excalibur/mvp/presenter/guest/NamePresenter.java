package com.rcl.excalibur.mvp.presenter.guest;


import android.text.TextUtils;

import com.rcl.excalibur.activity.guest.EmailActivity;
import com.rcl.excalibur.activity.guest.NameActivity;
import com.rcl.excalibur.domain.interactor.GetGuestPreferencesUseCase;
import com.rcl.excalibur.mvp.presenter.ActivityPresenter;
import com.rcl.excalibur.mvp.view.base.ActivityView;
import com.rcl.excalibur.mvp.view.guest.NameView;
import com.rcl.excalibur.utils.ActivityUtils;
import com.rcl.excalibur.utils.StringUtils;

import static com.rcl.excalibur.utils.StringUtils.SPLIT_SEPARATOR;
import static com.rcl.excalibur.utils.StringUtils.capitalizeAllWords;
import static com.rcl.excalibur.utils.StringUtils.removeBarreled;

public class NamePresenter implements ActivityPresenter {
    private static final String BRAND = "r";
    private static final String VERSION = "1.0";
    private static final int LIMIT_MAX = 50;
    boolean canChange = true;
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

        view.disableNextButton();
    }

    @Override
    public ActivityView getView() {
        return view;
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
            if (!validate(valueCapitalize)) {
                view.disableNextButton();
                return;
            }
            view.enableNextButton();

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
        if (length > LIMIT_MAX) {
            return false;
        }
        return true;
    }


    public void onNextClick() {
        final NameActivity activity = view.getActivity();
        if (activity == null) {
            return;
        }
        //TODO view this repeat the name and the lastname
        getGuestPreferencesUseCase.putName(view.getFullName());
        getGuestPreferencesUseCase.putLastname(view.getFullName());
        ActivityUtils.startActivity(activity, EmailActivity.getStartIntent(activity));
    }
}