package com.rcl.excalibur.mvp.presenter;


import com.rcl.excalibur.fragments.DayPickerFragment;
import com.rcl.excalibur.mvp.view.base.FragmentView;

public class DayPickerPresenter extends FragmentView<DayPickerFragment, Void, Void> {

    public DayPickerPresenter(DayPickerFragment fragment) {
        super(fragment);
    }

}
