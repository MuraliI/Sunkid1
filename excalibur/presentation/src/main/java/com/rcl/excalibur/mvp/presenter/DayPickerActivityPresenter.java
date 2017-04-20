package com.rcl.excalibur.mvp.presenter;


import com.rcl.excalibur.mvp.view.DayPickerActivityView;

public class DayPickerActivityPresenter {

    private DayPickerActivityView view;

    public DayPickerActivityPresenter(DayPickerActivityView view) {
        this.view = view;
    }

    public void init() {
        view.init();
    }
}
