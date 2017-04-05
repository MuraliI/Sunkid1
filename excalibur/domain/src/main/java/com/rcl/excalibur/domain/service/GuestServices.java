package com.rcl.excalibur.domain.service;

import java.util.List;

import io.reactivex.Observer;

public interface GuestServices {
    void getSecurityQuestions(Observer<List<String>> observer);
}
