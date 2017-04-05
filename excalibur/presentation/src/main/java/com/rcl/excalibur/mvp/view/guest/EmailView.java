package com.rcl.excalibur.mvp.view.guest;


import android.content.Context;
import android.view.View;
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
import io.reactivex.Observable;

public class EmailView extends ActivityView<EmailActivity> {
    @Bind(R.id.email_layout) RelativeLayout emailLayout;
    @Bind(R.id.editTextEmail) EditText editTextEmail;
    @Bind(R.id.textViewEmailAddressError) TextView textViewEmailAddressError;
    @Bind(R.id.imageViewNext) ImageView imageViewNext;
    @Bind(R.id.imageViewBack) ImageView imageViewBack;


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

    @OnClick(R.id.email_layout)
    public void onClickEmailLayout() {
        hideKeyboard();
    }

    @OnClick(R.id.imageViewNext)
    public void onClickImageViewNext() {
        //TODO navigate to Password Activity
    }


    public void manageNavigation(boolean status) {
        imageViewNext.setEnabled(status);
        if (status) {
            imageViewNext.setAlpha(1f);
            cleanTextViewError();
        } else {
            imageViewNext.setAlpha(0.24f);
        }
    }

    public void setLabelError(String errorText) {
        textViewEmailAddressError.setText(errorText);
        manageNavigation(false);
    }

    public void setHint(String hint) {
        editTextEmail.setHint(hint);
        hideKeyboard();
    }

    private void cleanTextViewError() {
        textViewEmailAddressError.setText(R.string.empty_string);
    }

    private void hideKeyboard() {
        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(emailLayout.getWindowToken(), 0);
    }
}
