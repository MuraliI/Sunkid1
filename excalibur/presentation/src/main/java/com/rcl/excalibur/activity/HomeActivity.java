package com.rcl.excalibur.activity;


import android.os.Bundle;

import com.adobe.mobile.Config;
import com.rcl.excalibur.R;
import com.rcl.excalibur.mvp.presenter.HomePresenter;
import com.rcl.excalibur.mvp.view.HomeView;

import java.util.HashMap;

public class HomeActivity extends BaseActivity {
    private HomePresenter presenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        presenter = new HomePresenter(new HomeView(this));

    }

    @Override
    public void onResume() {
        super.onResume();
        //TODO: create handler for analytics
        HashMap<String, Object> contextData = new HashMap<String, Object>();
        contextData.put("App starting", "App Start");
        Config.collectLifecycleData(this, contextData);
    }
}
