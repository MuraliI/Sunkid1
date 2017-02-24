package com.rcl.excalibur.activity;


import android.content.Intent;
import android.os.Bundle;

import com.rcl.excalibur.R;

public class PlansActivity extends BaseActivity {

    public static Intent getStartIntent(final BaseActivity activity) {
        return new Intent(activity, PlansActivity.class);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plans);
    }

}
