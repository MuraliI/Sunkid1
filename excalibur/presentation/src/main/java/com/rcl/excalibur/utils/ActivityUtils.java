package com.rcl.excalibur.utils;


import android.content.Intent;

import com.rcl.excalibur.R;
import com.rcl.excalibur.activity.BaseActivity;

public final class ActivityUtils {
    private ActivityUtils() {
    }

    public static void startActivity(BaseActivity activity, final Intent intent) {
        activity.startActivity(intent);
        activity.overridePendingTransition(R.anim.slide_in_up, R.anim.slide_out_up);
    }
}
