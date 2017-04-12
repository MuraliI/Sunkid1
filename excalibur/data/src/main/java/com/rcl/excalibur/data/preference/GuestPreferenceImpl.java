package com.rcl.excalibur.data.preference;

import android.content.Context;
import android.support.annotation.NonNull;

import com.rcl.excalibur.domain.preference.GuestPreference;


public class GuestPreferenceImpl extends BasePreferenceImpl implements GuestPreference {

    private static final String GUEST_PREFERENCES = "guest_pref";
    private static final String KEY_NAME = "name";
    private static final String KEY_LASTNAME = "lastname";
    private static final String KEY_PASSWORD = "password";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_BRAND = "brand";
    private static final String KEY_VERSION = "version";
    private static final String KEY_QUESTIONS = "questions";
    private static final String KEY_ANSWERS = "answers";
    private static final String KEY_ACCEPT_TIME = "accept_time";

    public GuestPreferenceImpl(Context context) {
        super(context, GUEST_PREFERENCES);
    }

    @Override
    public void putName(@NonNull String value) {
        put(KEY_NAME, value);
    }

    @Override
    public String getName() {
        return getString(KEY_NAME);
    }

    @Override
    public void putLastName(@NonNull String value) {
        put(KEY_LASTNAME, value);
    }

    @Override
    public String getLastName() {
        return getString(KEY_LASTNAME);
    }

    @Override
    public void putPassword(String value) {
        put(KEY_PASSWORD, value);
    }

    @Override
    public String getPassword() {
        return getString(KEY_PASSWORD);
    }

    @Override
    public void putEmail(String value) {
        put(KEY_EMAIL, value);
    }

    @Override
    public String getEmail() {
        return getString(KEY_EMAIL);
    }

    @Override
    public void putQuestion(String value) {
        put(KEY_QUESTIONS, value);
    }

    @Override
    public String getQuestion() {
        return getString(KEY_QUESTIONS);
    }

    @Override
    public void putAnswer(String value) {
        put(KEY_ANSWERS, value);
    }

    @Override
    public String getAnswer() {
        return getString(KEY_ANSWERS);
    }

    @Override
    public void putVersion(String value) {
        put(KEY_VERSION, value);
    }

    @Override
    public String getVersion() {
        return getString(KEY_VERSION);
    }

    @Override
    public void putAcceptTime(long value) {
        put(KEY_ACCEPT_TIME, value);
    }

    @Override
    public long getAcceptTime() {
        return getLong(KEY_ACCEPT_TIME);
    }

    @Override
    public void putBrand(String value) {
        put(KEY_BRAND, value);
    }

    @Override
    public String getBrand() {
        return getString(KEY_BRAND);
    }
}
