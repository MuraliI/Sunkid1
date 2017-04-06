package com.rcl.excalibur.domain.preference;


public interface GuestPreference {

    void putName(String value);

    String getName();

    void putLastname(String value);

    String getLastname();

    void putPassword(String value);

    String getPassword();

    void putEmail(String value);

    String getEmail();

    void putQuestion(String value);

    String getQuestion();

    void getAnswer(String value);

    String getAnswer();

    void putVersion(String value);

    String getVersion();

    void putAcceptTime(long value);

    long getAcceptTime();

    void putBrand(String value);

    String getBrand();

}
