package com.rcl.excalibur.domain.preference;


import java.util.Set;

public interface GuestPreference {

    void putName(String value);

    String getName();

    void putLastname(String value);

    String getLastname();

    void putPassword(String value);

    String getPassword();

    void putEmail(String value);

    String getEmail();

    void putQuestions(Set<String> values);

    Set<String> getQuestions();

    void getAnswers(Set<String> values);

    Set<String> getAnswers();

    void putVersion(String value);

    String getVersion();

    void putAcceptTime(long value);

    long getAcceptTime();

    void putBrand(String value);

    String getBrand();

}
