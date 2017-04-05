package com.rcl.excalibur.mvp.view.guest;


import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.rcl.excalibur.R;
import com.rcl.excalibur.activity.guest.EmailActivity;
import com.rcl.excalibur.mvp.view.base.ActivityView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class EmailView extends ActivityView<EmailActivity, Void> {
    @Bind(R.id.email_layout) RelativeLayout emailLayout;
    @Bind(R.id.edit_email) EditText editTextEmail;
    @Bind(R.id.text_show_error) TextView textViewEmailAddressError;
    @Bind(R.id.image_next_screen) ImageView imageViewNext;
    @Bind(R.id.image_back_screen) ImageView imageViewBack;


    public EmailView(EmailActivity activity) {
        super(activity);
        ButterKnife.bind(this, activity);
    }

    public void init() {
        final EmailActivity activity = getActivity();
        if (activity == null) {
            return;
        }
        imageViewNext.setEnabled(false);
    }

    @OnClick(R.id.image_next_screen)
    public void onClickImageViewNext() {
        //TODO navigate to Password Activity
    }


    public void manageNavigation(boolean status) {
        imageViewNext.setEnabled(status);
        if (status)
            cleanTextViewError();
    }

    public String getEmail() {
        return editTextEmail.getText().toString();
    }

    public void setLabelError(String errorText) {
        textViewEmailAddressError.setText(errorText);
        manageNavigation(false);
    }

    public void setHint(String hint) {
        editTextEmail.setHint(hint);
    }

    private void cleanTextViewError() {
        textViewEmailAddressError.setText(R.string.empty_string);
    }

}
