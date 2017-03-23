package com.rcl.excalibur.utils;


import android.app.Activity;
import android.content.Intent;

import com.rcl.excalibur.R;

public final class ActivityUtils {
    private ActivityUtils() {
    }

    public static void startActivity(Activity activity, final Intent intent) {
        activity.startActivity(intent);
        activity.overridePendingTransition(R.anim.slide_in_up, R.anim.slide_out_up);
    }

    public static void onBackActivity(Activity activity) {
        activity.finish();
        activity.overridePendingTransition(R.anim.slide_in_down, R.anim.slide_out_down);
    }

}
