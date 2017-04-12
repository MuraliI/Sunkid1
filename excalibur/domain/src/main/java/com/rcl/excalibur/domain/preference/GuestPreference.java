package com.rcl.excalibur.domain.preference;


public interface GuestPreference {

    void putName(String value);

    String getName();

    void putLastName(String value);

    String getLastName();

    void putPassword(String value);

    String getPassword();

    void putEmail(String value);

    String getEmail();

    void putQuestion(String value);

    String getQuestion();

    void putAnswer(String value);

    String getAnswer();

    void putVersion(String value);

    String getVersion();

    void putAcceptTime(long value);

    long getAcceptTime();

    void putBrand(String value);

    String getBrand();

}
