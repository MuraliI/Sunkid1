package com.rcl.excalibur.mvp.presenter.guest;


import com.rcl.excalibur.R;
import com.rcl.excalibur.activity.BaseActivity;
import com.rcl.excalibur.activity.guest.PasswordActivity;
import com.rcl.excalibur.domain.guest.ValidateEmailEvent;
import com.rcl.excalibur.domain.interactor.GetGuestPreferencesUseCase;
import com.rcl.excalibur.domain.service.GuestServices;
import com.rcl.excalibur.mvp.presenter.ActivityPresenter;
import com.rcl.excalibur.mvp.presenter.DefaultPresentObserver;
import com.rcl.excalibur.mvp.view.guest.EmailView;
import com.rcl.excalibur.utils.ActivityUtils;
import com.rcl.excalibur.utils.StringUtils;

public class EmailPresenter implements ActivityPresenter {
    private EmailView view;
    private GetGuestPreferencesUseCase getGuestPreferencesUseCase;
    private GuestServices guestServices;
    //TODO improve this
    private static final String NON_EXISTING_EMAIL = "DoesNotExist";


    public EmailPresenter(EmailView view, GetGuestPreferencesUseCase getGuestPreferencesUseCase, GuestServices guestServices) {
        this.view = view;
        this.getGuestPreferencesUseCase = getGuestPreferencesUseCase;
        this.guestServices = guestServices;
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
            guestServices.validateEmail(new DefaultPresentObserver<ValidateEmailEvent, EmailPresenter>(this) {
                                            @Override
                                            public void onNext(ValidateEmailEvent event) {
                                                if (!NON_EXISTING_EMAIL.equals(event.getMessage())) {
                                                    view.showMessage(R.string.email_exists_message);
                                                    return;
                                                }

                                                getGuestPreferencesUseCase.putEmail(view.getEmail());
                                                final BaseActivity activity = view.getActivity();
                                                if (activity == null) {
                                                    return;
                                                }

                                                ActivityUtils.startActivity(activity, PasswordActivity.getStartIntent(activity));
                                            }

                                        }
                    , view.getEmail());

        }
    }
}
