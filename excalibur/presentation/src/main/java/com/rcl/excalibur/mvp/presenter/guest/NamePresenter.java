package com.rcl.excalibur.mvp.presenter.guest;


import android.text.TextUtils;

import com.rcl.excalibur.activity.guest.NameActivity;
import com.rcl.excalibur.mvp.presenter.ActivityPresenter;
import com.rcl.excalibur.mvp.view.base.ActivityView;
import com.rcl.excalibur.mvp.view.guest.NameView;
import com.rcl.excalibur.utils.StringUtils;

import static com.rcl.excalibur.utils.StringUtils.SPLIT_SEPARATOR;
import static com.rcl.excalibur.utils.StringUtils.capitalizeAllWords;
import static com.rcl.excalibur.utils.StringUtils.removeBarreled;

public class NamePresenter implements ActivityPresenter {
    private static final int LIMIT_MAX = 50;
    boolean canChange = true;
    private NameView view;

    public NamePresenter(NameView view) {
        this.view = view;
    }

    public void init() {
        view.disableNextButton();
    }

    @Override
    public ActivityView getView() {
        return view;
    }

    public void onArrowBack() {
        final NameActivity nameActivity = view.getActivity();
        if (nameActivity == null) {
            return;
        }
        nameActivity.finish();
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
//            view.showError(R.string.guest_error_name_empty);
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
//            view.showError();
            return false;
        }
        return true;
    }


}
