package com.rcl.excalibur.utils;


import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.view.View;

import com.rcl.excalibur.R;

public final class ActivityUtils {
    private ActivityUtils() {
    }

    public static void startActivity(Activity activity, final Intent intent) {
        activity.startActivity(intent);
        activity.overridePendingTransition(R.anim.slide_in_up, R.anim.slide_out_up);
    }

    public static void startActivityWithSharedElement(Activity previousActivity
            , final Intent intent
            , View sharedElement
            , String sharedElelemntName) {

        ActivityOptions activityOptionsCompat = ActivityOptions
                .makeSceneTransitionAnimation(previousActivity
                        , sharedElement
                        , sharedElelemntName);

        previousActivity.startActivity(intent, activityOptionsCompat.toBundle());
    }

    public static void onBackActivity(Activity activity) {
        activity.finish();
        activity.overridePendingTransition(R.anim.slide_in_down, R.anim.slide_out_down);
    }

}
