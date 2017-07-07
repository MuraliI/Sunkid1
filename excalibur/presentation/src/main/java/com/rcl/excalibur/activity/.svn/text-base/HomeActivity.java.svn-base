package com.rcl.excalibur.activity;


import android.os.Bundle;

import com.rcl.excalibur.R;
import com.rcl.excalibur.mvp.presenter.HomePresenter;
import com.rcl.excalibur.mvp.view.HomeView;

public class HomeActivity extends BaseActivity {

    protected HomePresenter presenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        presenter = new HomePresenter(new HomeView(this));
    }

}
