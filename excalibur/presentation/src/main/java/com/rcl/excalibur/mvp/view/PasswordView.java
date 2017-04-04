package com.rcl.excalibur.mvp.view;


import android.content.Context;
import android.graphics.PorterDuff;
import android.support.v4.content.ContextCompat;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.rcl.excalibur.R;
import com.rcl.excalibur.activity.PasswordActivity;
import com.rcl.excalibur.mvp.view.base.ActivityView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.rcl.excalibur.utils.ValidateFieldsUtils.isValidatePassword;

public class PasswordView extends ActivityView<PasswordActivity> {
    @Bind(R.id.editTextPassword)
    EditText editTextPassword;
    @Bind(R.id.checkboxShowPassword)
    CheckBox checkBoxShowPassword;
    @Bind(R.id.textViewError)
    TextView textViewError;

    public PasswordView(PasswordActivity activity) {
        super(activity);
        ButterKnife.bind(this, activity);
    }

    public void init() {
        final PasswordActivity activity = getActivity();
        if (activity == null) {
            return;
        }

        editTextPassword.setOnFocusChangeListener((v, hasFocus) -> {
            if (hasFocus)
                editTextPassword.setHint("");
            else {
                editTextPassword.setHint(getActivity().getString(R.string.hint_password));
                String error = isValidatePassword(editTextPassword.getText().toString(), getActivity());
                textViewError.setText(error);

                if (!editTextPassword.getText().toString().isEmpty()) {
                    //editTextPassword.setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.white));
                    int color = ContextCompat.getColor(getActivity(), R.color.white);
                    editTextPassword.getBackground().setColorFilter(color, PorterDuff.Mode.SRC_IN);
                }
            }

        });

        checkBoxShowPassword.setOnCheckedChangeListener((buttonView, isChecked) -> setVisiblePassword(isChecked));
    }

    private void setVisiblePassword(boolean isVisiblePassword) {
        if (isVisiblePassword)
            editTextPassword.setTransformationMethod(null);
        else
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
        Log.d("validatepass", isValidatePassword(editTextPassword.getText().toString(), getActivity()));
    }
}
