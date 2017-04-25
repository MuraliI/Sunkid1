package com.rcl.excalibur.data.preference;

import android.content.Context;

import com.rcl.excalibur.domain.preference.DiscoverPreference;


public class DiscoverPreferenceImpl extends BasePreferenceImpl implements DiscoverPreference {

    private static final String DISCOVER_PREFERENCES = "discover_pref";
    private static final String KEY_CALLED_SERVICE = "called_serviece";

    public DiscoverPreferenceImpl(Context context) {
        super(context, DISCOVER_PREFERENCES);
    }

    @Override
    public void putCalledService(boolean value) {
        put(KEY_CALLED_SERVICE, value);
    }

    @Override
    public boolean isCalledService() {
        return getBoolean(KEY_CALLED_SERVICE);
    }
}
