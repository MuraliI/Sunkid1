package com.rcl.excalibur.activity;

import android.content.Intent;
import android.os.Bundle;

import com.rcl.excalibur.R;
import com.rcl.excalibur.mvp.presenter.DayPickerActivityPresenter;
import com.rcl.excalibur.mvp.view.DayPickerActivityView;

public class DayPickerActivity extends BaseActivity {

    DayPickerActivityPresenter presenter;

    public static Intent getStartIntent(BaseActivity activity) {
        return new Intent(activity, DayPickerActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day_picker);
        presenter = new DayPickerActivityPresenter(new DayPickerActivityView(this));
        presenter.init();
    }
}
