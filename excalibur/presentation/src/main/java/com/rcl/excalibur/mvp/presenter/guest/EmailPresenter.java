package com.rcl.excalibur.mvp.presenter.guest;


import com.rcl.excalibur.R;
import com.rcl.excalibur.activity.BaseActivity;
import com.rcl.excalibur.activity.guest.PasswordActivity;
import com.rcl.excalibur.domain.guest.ValidateEmailEvent;
import com.rcl.excalibur.domain.interactor.GetGuestPreferencesUseCase;
import com.rcl.excalibur.domain.service.GuestServices;
import com.rcl.excalibur.mvp.presenter.DefaultPresentObserver;
import com.rcl.excalibur.mvp.view.guest.EmailView;
import com.rcl.excalibur.utils.ActivityUtils;
import com.rcl.excalibur.data.utils.StringUtils;

public class EmailPresenter {
    //TODO improve this
    private static final String EXISTING_EMAIL = "Exists";
    private EmailView view;
    private GetGuestPreferencesUseCase getGuestPreferencesUseCase;
    private GuestServices guestServices;


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
            validateEmailExist();
        }
    }

    private void validateEmailExist() {
        view.manageNavigation(true, EmailView.ACTIVE);
        view.cleanTextViewError();
    }

    public void checkDone() {
        if (view.isPossibleNavigate()) {
            guestServices.validateEmail(new DefaultPresentObserver<ValidateEmailEvent, EmailPresenter>(this) {
                                            @Override
                                            public void onNext(ValidateEmailEvent event) {
                                                if (EXISTING_EMAIL.equals(event.getMessage())) {
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

                                            @Override
                                            public void onError(Throwable e) {
                                                super.onError(e);
                                                view.showMessage(R.string.error_message_no_connection);
                                            }
                                        }
                    , view.getEmail());

        }
    }
}
