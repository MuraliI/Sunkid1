package com.rcl.excalibur.mvp.view;


import android.widget.TextView;

import com.rcl.excalibur.R;
import com.rcl.excalibur.fragments.DayPickerFragment;
import com.rcl.excalibur.mvp.view.base.FragmentView;

import butterknife.Bind;

public class DayPickerView extends FragmentView<DayPickerFragment, Void, Void> {
    @Bind(R.id.day_picker_startandend_date) TextView textDayPicker;

    protected DayPickerView(DayPickerFragment fragment) {
        super(fragment);
    }

    public void setDate(String date) {
        textDayPicker.setText(date);
    }
}
