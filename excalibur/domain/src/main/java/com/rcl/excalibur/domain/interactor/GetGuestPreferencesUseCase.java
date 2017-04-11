package com.rcl.excalibur.domain.interactor;


import com.rcl.excalibur.domain.preference.GuestPreference;


public class GetGuestPreferencesUseCase extends UseCaseSync<GuestPreference> {


    public GetGuestPreferencesUseCase(GuestPreference guestPreference) {
        super(guestPreference);
    }

    public void putName(String value) {
        getData().putName(value);
    }

    public String getName() {
        return getData().getName();
    }

    public void putLastname(String value) {
        getData().putLastName(value);
    }

    public String getLastname() {
        return getData().getLastName();
    }

    public void putPassword(String value) {
        getData().putPassword(value);
    }

    public String getPassword() {
        return getData().getPassword();
    }

    public void putEmail(String value) {
        getData().putEmail(value);
    }

    public String getEmail() {
        return getData().getEmail();
    }

    public void putQuestion(String value) {
        getData().putQuestion(value);
    }

    public String getQuestion() {
        return getData().getQuestion();
    }

    public void putAnswer(String value) {
        getData().getAnswer(value);
    }

    public String getAnswer() {
        return getData().getAnswer();
    }

    public void putVersion(String value) {
        getData().putVersion(value);
    }

    public String getVersion() {
        return getData().getVersion();
    }

    public void putAcceptTime(long value) {
        getData().putAcceptTime(value);
    }

    public long getAcceptTime() {
        return getData().getAcceptTime();
    }

    public void putBrand(String value) {
        getData().putBrand(value);
    }

    public String getBrand() {
        return getData().getBrand();
    }

}
