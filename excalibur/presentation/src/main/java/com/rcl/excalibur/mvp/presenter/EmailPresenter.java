package com.rcl.excalibur.mvp.presenter;


import com.rcl.excalibur.activity.BaseActivity;
import com.rcl.excalibur.mvp.view.EmailView;
import com.rcl.excalibur.utils.ActivityUtils;

public class EmailPresenter implements ActivityPresenter {

    private EmailView view;

    public EmailPresenter(EmailView view) {
        this.view = view;
        init();
    }

    public void onHeaderBackOnClick() {
        final BaseActivity activity = view.getActivity();
        if (activity == null) {
            return;
        }
        ActivityUtils.onBackActivity(activity);
    }

    public void init() {
        view.init();
    }

    @Override
    public EmailView getView() {
        return view;
    }

}
