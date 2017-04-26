package com.rcl.excalibur.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.rcl.excalibur.R;
import com.rcl.excalibur.mvp.presenter.VoyageMapPresenter;
import com.rcl.excalibur.utils.ActivityUtils;

public class VoyageMapActivity extends BaseActivity {

    VoyageMapPresenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voyage_map);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        ActivityUtils.onBackActivityWithFade(this);
    }

    public static Intent getStartIntent(BaseActivity activity) {
        return new Intent(activity, VoyageMapActivity.class);
    }
}
