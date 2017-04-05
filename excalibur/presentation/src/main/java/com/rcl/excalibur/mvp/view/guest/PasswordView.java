package com.rcl.excalibur.mvp.view.guest;


import android.text.SpannableStringBuilder;
import android.text.method.PasswordTransformationMethod;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.rcl.excalibur.R;
import com.rcl.excalibur.activity.guest.PasswordActivity;
import com.rcl.excalibur.mvp.view.base.ActivityView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PasswordView extends ActivityView<PasswordActivity> {
    @Bind(R.id.edit_create_password) EditText editTextPassword;
    @Bind(R.id.text_show_error) TextView textViewError;
    @Bind(R.id.image_next_screen) ImageView imageViewNext;

    public PasswordView(PasswordActivity activity) {
        super(activity);
        ButterKnife.bind(this, activity);
    }

    public void setHint(String hint) {
        editTextPassword.setHint(hint);
    }

    public void setError(SpannableStringBuilder error) {
        textViewError.setText(error);
    }

    public void setNextButton(boolean isEnable) {
        imageViewNext.setEnabled(isEnable);
    }

    public String getPassword() {
        return editTextPassword.getText().toString();
    }

    public void setVisiblePassword() {
        editTextPassword.setTransformationMethod(null);
    }

    public void setInvisiblePassword() {
        editTextPassword.setTransformationMethod(new PasswordTransformationMethod());
    }

    @OnClick(R.id.image_next_screen)
    public void onClickImageViewNext() {
        //TODO navigate to next Activity
    }
}
