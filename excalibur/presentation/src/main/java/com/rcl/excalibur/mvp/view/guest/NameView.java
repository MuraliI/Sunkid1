package com.rcl.excalibur.mvp.view.guest;


import android.support.annotation.StringRes;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.rcl.excalibur.R;
import com.rcl.excalibur.activity.guest.NameActivity;
import com.rcl.excalibur.mvp.view.base.ActivityView;

import butterknife.Bind;
import butterknife.ButterKnife;

public class NameView extends ActivityView<NameActivity, Void> {
    @Bind(R.id.full_name) EditText fullName;
    @Bind(R.id.name_error) TextView error;
    @Bind(R.id.next_button) ImageView nextButton;

    public NameView(NameActivity activity) {
        super(activity);
        ButterKnife.bind(this, activity);
    }

    public String getFullName() {
        return fullName.getText().toString();
    }

    public void changeValue(final String value) {
        final int selectionEnd = fullName.getSelectionEnd();
        fullName.setText(value);
        fullName.setSelection(selectionEnd);
    }

    public void setNextButton(boolean isEnable) {
        nextButton.setEnabled(isEnable);
    }

    public void showError(@StringRes int message) {
        error.setText(message);
    }
}
