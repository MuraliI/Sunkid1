package com.rcl.excalibur.mvp.view;


import android.widget.TextView;

import com.rcl.excalibur.R;
import com.rcl.excalibur.fragments.DayPickerFragment;
import com.rcl.excalibur.mvp.view.base.FragmentView;

import butterknife.Bind;
import butterknife.ButterKnife;

public class DayPickerView extends FragmentView<DayPickerFragment, Void, Void> {

    @Bind(R.id.day_picker_startandend_date) TextView fotterDate;
    @Bind(R.id.day_picker_header_description) TextView dateDescription;

    public DayPickerView(DayPickerFragment fragment) {
        super(fragment);
        ButterKnife.bind(this, fragment.getView());
    }

    public void setFotterDate(String date) {
        fotterDate.setText(date);
    }
    public void setDescription(String description) {
        dateDescription.setText(description);
    }
}

