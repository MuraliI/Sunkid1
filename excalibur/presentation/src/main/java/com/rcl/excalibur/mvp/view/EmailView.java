package com.rcl.excalibur.mvp.view;


import android.content.Context;
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
import com.rcl.excalibur.utils.StringUtils;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

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

        editTextEmail.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                // TODO Auto-generated method stub
                if (event.getKeyCode() == 66 && event.getAction() == KeyEvent.ACTION_UP) {
                    String email = editTextEmail.getText().toString().replaceAll("\n", "");

                    if ("".equalsIgnoreCase(email)) {
                        imageViewNext.setClickable(false);
                        return false;
                    } else if (email.length() > 100) {
                        textViewEmailAddressError.setText("Email must to be smaller than 100 characters");
                        imageViewNext.setClickable(false);
                    } else if (!StringUtils.isValidEmail(email)) {
                        textViewEmailAddressError.setText("Incorrect email format");
                        imageViewNext.setClickable(false);
                    } else {

                    }

                }
                return false;
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
    public  void onClickimageViewNext() {

        //TODO navigate to password activity
        int v=0;
        v++;
    }

    private void validateEmailExist(String email) {
        imageViewNext.setClickable(true);
    }


}
