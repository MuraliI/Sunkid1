package com.rcl.excalibur.activity;


import android.content.Intent;
import android.os.Bundle;

import com.rcl.excalibur.R;
import com.rcl.excalibur.mvp.presenter.TriptycHomePresenter;
import com.rcl.excalibur.mvp.view.TriptyHomechView;

public class TriptychHomeActivity extends BaseActivity {

    private TriptycHomePresenter presenter;

    public static Intent getStartIntent(final BaseActivity activity) {
        return new Intent(activity, TriptychHomeActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_triptych_home_screen);

        presenter = new TriptycHomePresenter(new TriptyHomechView(this));
    }
}
