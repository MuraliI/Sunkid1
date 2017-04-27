package com.rcl.excalibur.mvp.view.guest;


import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.rcl.excalibur.R;
import com.rcl.excalibur.activity.guest.EmailActivity;
import com.rcl.excalibur.mvp.view.base.ActivityView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class EmailView extends ActivityView<EmailActivity, Void, Void> {
    public static final float ACTIVE = 1f;
    public static final float INACTIVE = 0.24f;
    @BindView(R.id.email_layout) RelativeLayout emailLayout;
    @BindView(R.id.edit_email) EditText editTextEmail;
    @BindView(R.id.text_show_error) TextView textViewEmailAddressError;
    @BindView(R.id.image_next_screen) ImageView imageViewNext;
    @BindView(R.id.image_back_screen) ImageView imageViewBack;
    private boolean isPossibleNavigate;


    public EmailView(EmailActivity activity) {
        super(activity);
        ButterKnife.bind(this, activity);
    }

    public boolean isPossibleNavigate() {
        return isPossibleNavigate;
    }

    @OnClick(R.id.email_layout)
    void onClickEmailLayout() {
        hideKeyboard();
    }

    public void manageNavigation(boolean status, float alpha) {
        isPossibleNavigate = status;
        imageViewNext.setAlpha(alpha);
    }

    public String getEmail() {
        return editTextEmail.getText().toString();
    }


    public void setLabelError(String errorText) {
        textViewEmailAddressError.setText(errorText);
        manageNavigation(false, INACTIVE);
    }

    public void cleanTextViewError() {
        textViewEmailAddressError.setText(R.string.empty_string);
    }
}
