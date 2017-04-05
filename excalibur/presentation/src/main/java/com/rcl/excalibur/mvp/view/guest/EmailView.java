package com.rcl.excalibur.mvp.view.guest;


import android.content.Context;
import android.view.inputmethod.InputMethodManager;
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
    private boolean isPossibleNavigate;
    public static final float ACTIVE = 1f;
    public static final float INACTIVE = 0.24f;


    public EmailView(EmailActivity activity) {
        super(activity);
        ButterKnife.bind(this, activity);
    }

    public void init() {
        final EmailActivity activity = getActivity();
        if (activity == null) {
            return;
        }

    }

    public boolean getIsposibleNavigate() {
        return isPossibleNavigate;
    }

    @OnClick(R.id.email_layout)
    void onClickEmailLayout() {
        hideKeyboard();
    }

    public void navigate() {
        //TODO navigate to Password Activity
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

    public void setHint(String hint) {
        editTextEmail.setHint(hint);
        hideKeyboard();
    }

    public void cleanTextViewError() {
        textViewEmailAddressError.setText(R.string.empty_string);
    }

    private void hideKeyboard() {
        final EmailActivity activity = getActivity();
        if (activity == null) {
            return;
        }
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(emailLayout.getWindowToken(), 0);

    }
}
