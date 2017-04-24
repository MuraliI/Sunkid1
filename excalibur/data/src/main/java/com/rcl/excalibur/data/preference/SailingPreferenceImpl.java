package com.rcl.excalibur.data.preference;

import android.content.Context;
import android.support.annotation.NonNull;

import com.rcl.excalibur.domain.preference.SailingPreferences;

public class SailingPreferenceImpl extends BasePreferenceImpl implements SailingPreferences {

    private static final String SAILING_PREFERENCES = "sailing_pref";
    private static final String KEY_DAY = "day";


    public SailingPreferenceImpl(Context context) {
        super(context, SAILING_PREFERENCES);
    }

    @Override
    public void putDay(@NonNull String value) {
        put(KEY_DAY, value);
    }

    @Override
    public String getDay() {
        return getString(KEY_DAY);
    }
}
