package com.rcl.excalibur.mvp.presenter;


import com.rcl.excalibur.R;
import com.rcl.excalibur.activity.BaseActivity;
import com.rcl.excalibur.mvp.view.guest.PasswordView;
import com.rcl.excalibur.mvp.view.base.ActivityView;
import com.rcl.excalibur.utils.ActivityUtils;

public class PasswordPresenter implements ActivityPresenter {
    private PasswordView view;

    public PasswordPresenter(PasswordView view) {
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


    private void init() {
        view.init();
    }

    public void setFocus(boolean hasFocus) {
        final BaseActivity activity = view.getActivity();
        if (activity == null) {
            return;
        }
        view.setHint(activity.getString(hasFocus ? R.string.empty_string : R.string.hint_password));

//        String error = isValidatePassword(editTextPassword.getText().toString(), getActivity());
//        textViewError.setText(error);
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
}
