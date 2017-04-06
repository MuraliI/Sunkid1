package com.rcl.excalibur.mvp.presenter.guest;

import com.rcl.excalibur.activity.BaseActivity;
import com.rcl.excalibur.activity.TriptychHomeActivity;
import com.rcl.excalibur.mvp.presenter.ActivityPresenter;
import com.rcl.excalibur.mvp.view.guest.CreateAccountDoneView;

import static com.rcl.excalibur.utils.ActivityUtils.startActivity;

public class CreateAccountDonePresenter implements ActivityPresenter {
    private CreateAccountDoneView view;

    public CreateAccountDonePresenter(CreateAccountDoneView view) {
        this.view = view;
    }

    public void onActivityClick() {
        final BaseActivity activity = view.getActivity();
        if (activity == null) {
            return;
        }

        startActivity(activity, TriptychHomeActivity.getStartIntent(activity));
    }

    @Override
    public CreateAccountDoneView getView() {
        return view;
    }
}
