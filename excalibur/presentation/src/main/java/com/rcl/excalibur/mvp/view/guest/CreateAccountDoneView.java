package com.rcl.excalibur.mvp.view.guest;

import com.rcl.excalibur.activity.guest.CreateAccountDoneActivity;
import com.rcl.excalibur.mvp.view.base.ActivityView;

import butterknife.ButterKnife;

public class CreateAccountDoneView extends ActivityView<CreateAccountDoneActivity, Void> {


    public CreateAccountDoneView(CreateAccountDoneActivity activity) {
        super(activity);
        ButterKnife.bind(this, activity);
    }
}
