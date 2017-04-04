package com.rcl.excalibur.mvp.presenter;


import com.rcl.excalibur.R;
import com.rcl.excalibur.activity.BaseActivity;
import com.rcl.excalibur.mvp.view.EmailView;
import com.rcl.excalibur.utils.ActivityUtils;
import com.rcl.excalibur.utils.StringUtils;

public class EmailPresenter implements ActivityPresenter {

    private EmailView view;

    public EmailPresenter(EmailView view) {
        this.view = view;
        init();
    }

    public void onHeaderBackOnClick() {
        final BaseActivity activity = view.getActivity();
        if (activity == null) {
            return;
        }
        ActivityUtils.onBackActivity(activity);
    }

    public void init() {
        view.init();
    }

    public void verifyEmail(String email) {
        if ("".equalsIgnoreCase(email)) {
            view.manageNavigation(false);
        } else if (email.length() >= 100) {
            view.setLabelError("Email must to be smaller than 100 characters");
        } else if (!StringUtils.isValidEmail(email)) {
            view.setLabelError("Incorrect email format");
        } else {
            validateEmailExist(email);
        }
    }

    @Override
    public EmailView getView() {
        return view;
    }

    private void validateEmailExist(String email) {
        view.manageNavigation(true);
        //TODO consume web service to verify if email already exist
    }

    public void setFocus(boolean hasFocus) {
        final BaseActivity activity = view.getActivity();
        if (activity == null) {
            return;
        }
        view.setHint(activity.getString(hasFocus ? R.string.empty_string : R.string.title_hint_email_address));
    }
}
