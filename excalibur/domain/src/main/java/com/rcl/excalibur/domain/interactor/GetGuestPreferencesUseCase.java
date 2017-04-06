package com.rcl.excalibur.domain.interactor;


import com.rcl.excalibur.domain.preference.GuestPreference;

import java.util.Set;

public class GetGuestPreferencesUseCase extends UseCaseSync {

    private final GuestPreference guestPreference;

    public GetGuestPreferencesUseCase(GuestPreference guestPreference) {
        this.guestPreference = guestPreference;
    }

    public void putName(String value) {
        guestPreference.putName(value);
    }

    public String getName() {
        return guestPreference.getName();
    }

    public void putLastname(String value) {
        guestPreference.putLastname(value);
    }

    public String getLastname() {
        return guestPreference.getLastname();
    }

    public void putPassword(String value) {
        guestPreference.putPassword(value);
    }

    public String getPassword() {
        return guestPreference.getPassword();
    }

    public void putEmail(String value) {
        guestPreference.putEmail(value);
    }

    public String getEmail() {
        return guestPreference.getEmail();
    }

    public void putQuestions(Set<String> values) {
        guestPreference.putQuestions(values);
    }

    public Set<String> getQuestions() {
        return guestPreference.getQuestions();
    }

    public void putAnswers(Set<String> values) {
        guestPreference.getAnswers(values);
    }

    public Set<String> getAnswers() {
        return guestPreference.getAnswers();
    }

    public void putVersion(String value) {
        guestPreference.putVersion(value);
    }

    public String getVersion() {
        return guestPreference.getVersion();
    }

    public void putAcceptTime(long value) {
        guestPreference.putAcceptTime(value);
    }

    public long getAcceptTime() {
        return guestPreference.getAcceptTime();
    }

    public void putBrand(String value) {
        guestPreference.putBrand(value);
    }

    public String getBrand() {
        return guestPreference.getBrand();
    }

}
