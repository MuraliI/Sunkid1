package com.rcl.excalibur.domain.service;

import com.rcl.excalibur.domain.guest.CreateAccountEvent;

import java.util.List;

import io.reactivex.Observer;

public interface GuestServices {
    void getSecurityQuestions(Observer<List<String>> observer);
    void createAccount(Observer<CreateAccountEvent> observer);
}
