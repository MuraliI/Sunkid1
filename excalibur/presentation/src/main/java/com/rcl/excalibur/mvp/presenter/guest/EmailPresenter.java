package com.rcl.excalibur.mvp.presenter.guest;


import com.rcl.excalibur.R;
import com.rcl.excalibur.activity.BaseActivity;
import com.rcl.excalibur.mvp.presenter.ActivityPresenter;
import com.rcl.excalibur.mvp.view.guest.EmailView;
import com.rcl.excalibur.utils.ActivityUtils;
import com.rcl.excalibur.utils.StringUtils;

public class EmailPresenter implements ActivityPresenter {
    private EmailView view;


    public EmailPresenter(EmailView view) {
        this.view = view;
    }

    public void onHeaderBackOnClick() {
        final BaseActivity activity = view.getActivity();
        if (activity == null) {
            return;
        }
        ActivityUtils.onBackActivity(activity);
    }

    private void init() {
        view.init();
    }

    public void verifyEmail() {
        String email = view.getEmail();
        final BaseActivity activity = view.getActivity();
        if (activity == null) {
            return;
        }

        if (email.isEmpty()) {
            view.cleanTextViewError();
            view.manageNavigation(false, EmailView.INACTIVE);
        } else if (!StringUtils.isValidEmail(email)) {
            view.setLabelError(activity.getString(R.string.incorrect_email_format));
        } else {
            validateEmailExist(email);
        }
    }

    @Override
    public EmailView getView() {
        return view;
    }


    private void validateEmailExist(String email) {
        view.manageNavigation(true, EmailView.ACTIVE);
        view.cleanTextViewError();
        //TODO consume web service to verify if email already exist
    }

    public void setFocus(boolean hasFocus) {
        final BaseActivity activity = view.getActivity();
        if (activity == null) {
            return;
        }
        view.setHint(activity.getString(hasFocus ? R.string.empty_string : R.string.title_hint_email_address));
    }

    public void checkDone() {
         if (view.getIsposibleNavigate()) {
             view.navigate();
         }
    }
}
