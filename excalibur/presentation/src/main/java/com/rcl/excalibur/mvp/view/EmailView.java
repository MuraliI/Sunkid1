package com.rcl.excalibur.mvp.view;


import android.content.Context;
import android.graphics.PorterDuff;
import android.support.v4.content.ContextCompat;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.rcl.excalibur.R;
import com.rcl.excalibur.activity.EmailActivity;
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
        editTextEmail.setOnKeyListener((v, keyCode, event) -> {
            if (event.getKeyCode() == 66 && event.getAction() == KeyEvent.ACTION_UP) {
                verifyEmail();
            }
            return false;
        });

        editTextEmail.setOnFocusChangeListener((v, hasFocus) -> {
            if (hasFocus)
                editTextEmail.setHint("");
            else {
                editTextEmail.setHint(getActivity().getString(R.string.title_hint_email_address));

                if (!editTextEmail.getText().toString().isEmpty()) {
                    int color = ContextCompat.getColor(getActivity(), R.color.white);
                    editTextEmail.getBackground().setColorFilter(color, PorterDuff.Mode.SRC_IN);
                }
            }

        });
    }

    @OnClick(R.id.email_layout)
    public void onClickEmailLayout() {
        View view = getActivity().getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    @OnClick(R.id.imageViewNext)
    public void onClickImageViewNext() {
        verifyEmail();
    }

    private void verifyEmail() {
        String email = editTextEmail.getText().toString().replaceAll("\n", "");
        Observable.just(email).subscribe(viewObserver);
    }

    public void manageNavigation(boolean status) {
        imageViewNext.setEnabled(status);
        if (status) {
            imageViewNext.setAlpha(1f);
            textViewEmailAddressError.setText("");
        } else {
            imageViewNext.setAlpha(0.24f);
        }
    }

    public void setLabelError(String errorText) {
        textViewEmailAddressError.setText(errorText);
        manageNavigation(false);
    }
}
