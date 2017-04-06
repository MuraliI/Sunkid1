package com.rcl.excalibur.data;

import android.content.Context;
import android.support.annotation.NonNull;

import com.rcl.excalibur.domain.preference.GuestPreference;

import java.util.Set;

import static android.content.Context.MODE_PRIVATE;


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
        super(context.getSharedPreferences(GUEST_PREFERENCES, MODE_PRIVATE));
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
    public void putLastname(@NonNull String value) {
        put(KEY_LASTNAME, value);
    }

    @Override
    public String getLastname() {
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
    public void putQuestions(Set<String> values) {
        put(KEY_QUESTIONS, values);
    }

    @Override
    public Set<String> getQuestions() {
        return getStringSet(KEY_QUESTIONS);
    }

    @Override
    public void getAnswers(Set<String> values) {
        put(KEY_ANSWERS, values);
    }

    @Override
    public Set<String> getAnswers() {
        return getStringSet(KEY_ANSWERS);
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
