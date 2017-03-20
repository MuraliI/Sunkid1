package com.rcl.excalibur.utils.analytics;

import android.app.Activity;
import android.content.Context;

import com.adobe.mobile.Analytics;
import com.adobe.mobile.Config;
import com.rcl.excalibur.model.DiscoverItemModel;

import java.util.HashMap;

public final class AnalyticsUtils {

    public static void initializeAnalyticsTool(Context context) {
        Config.setContext(context);
    }

    public static void startAnalytics(Activity activity) {
        Config.collectLifecycleData(activity);
    }

    public static void stopAnalytics() {
        Config.pauseCollectingLifecycleData();
    }

    public static void trackState(String stateName) {
        Analytics.trackState(stateName, null);
    }

    public static void trackEvent(AnalyticEvent analyticEvent) {
        HashMap<String, Object> contextData = new HashMap<String, Object>();
        contextData.putAll(analyticEvent.getKeyValues());
        Analytics.trackAction(analyticEvent.getEventName(), contextData);
    }

    private AnalyticsUtils() {

    }

    public AnalyticEvent getDiscoverDetailEvent(DiscoverItemModel discoverItemModel) {
        String title = "";

        return new AnalyticEvent(title).addKeyValue(AnalyticsConstants.KEY_DISCOVER_ITEM_DETAILS_ACTIVITY_TYPE,
                discoverItemModel.getType());
    }


}
