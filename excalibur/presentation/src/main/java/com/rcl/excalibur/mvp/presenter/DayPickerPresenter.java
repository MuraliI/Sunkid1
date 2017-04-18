package com.rcl.excalibur.mvp.presenter;


import com.rcl.excalibur.activity.BaseActivity;
import com.rcl.excalibur.mvp.view.DayPickerView;

public class DayPickerPresenter  {

    private DayPickerView view;

    public DayPickerPresenter(DayPickerView view) {
        this.view = view;

    }

    public void init() {
        final BaseActivity activity = view.getActivity();
        if (activity == null) {
            return;
        }
        view.setFotterDate("Feb 14 -15");
        view.setDescription("Some description");
    }

}
