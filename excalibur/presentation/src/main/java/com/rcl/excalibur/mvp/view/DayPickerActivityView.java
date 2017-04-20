package com.rcl.excalibur.mvp.view;


import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.FrameLayout;

import com.rcl.excalibur.R;
import com.rcl.excalibur.activity.DayPickerActivity;
import com.rcl.excalibur.fragments.DayPickerFragment;
import com.rcl.excalibur.mvp.view.base.ActivityView;

import butterknife.Bind;

public class DayPickerActivityView extends ActivityView<DayPickerActivity, Void, Void> {

    @Bind(R.id.contentFrame) FrameLayout contentFrame;
    FragmentManager fargmentManager;

    public DayPickerActivityView(DayPickerActivity activity) {
        super(activity);
    }

    public void init() {
        final DayPickerActivity activity = getActivity();
        if (activity == null) {
            return;
        }
        fargmentManager = activity.getSupportFragmentManager();
        FragmentTransaction transaction = fargmentManager.beginTransaction();
        transaction.add(R.id.contentFrame, new DayPickerFragment());
        transaction.commit();
    }
}
