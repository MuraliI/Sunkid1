package com.rcl.excalibur.mvp.view.guest;


import android.content.Context;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.rcl.excalibur.R;
import com.rcl.excalibur.activity.guest.PasswordActivity;
import com.rcl.excalibur.mvp.view.base.ActivityView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PasswordView extends ActivityView<PasswordActivity> {
    @Bind(R.id.editTextPassword) EditText editTextPassword;
    @Bind(R.id.checkboxShowPassword) CheckBox checkBoxShowPassword;
    @Bind(R.id.textViewError) TextView textViewError;

    public PasswordView(PasswordActivity activity) {
        super(activity);
        ButterKnife.bind(this, activity);
    }

    public void init() {
        final PasswordActivity activity = getActivity();
        if (activity == null) {
            return;
        }
    }



    public void setHint(String hint) {
        editTextPassword.setHint(hint);
    }

    public void setVisiblePassword() {
        editTextPassword.setTransformationMethod(null);
    }

    public void setInvisiblePassword() {
        editTextPassword.setTransformationMethod(new PasswordTransformationMethod());
    }


    @OnClick(R.id.password_layout)
    public void onClickEmailLayout() {
        View view = getActivity().getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    @OnClick(R.id.imageViewNext)
    public void onClickImageViewNext() {
    }
}
