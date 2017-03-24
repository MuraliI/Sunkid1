package com.rcl.excalibur.activity;


import android.content.Intent;
import android.os.Bundle;

import com.rcl.excalibur.R;
import com.rcl.excalibur.mvp.presenter.TriptychHomePresenter;
import com.rcl.excalibur.mvp.view.TriptychHomechView;

public class TriptychHomeActivity extends BaseActivity {

    private TriptychHomePresenter presenter;

    public static Intent getStartIntent(final BaseActivity activity) {
        return new Intent(activity, TriptychHomeActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_triptych_home_screen);

        presenter = new TriptychHomePresenter(new TriptychHomechView(this));
    }
}
