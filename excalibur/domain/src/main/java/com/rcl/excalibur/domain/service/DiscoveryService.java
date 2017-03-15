package com.rcl.excalibur.domain.service;


import io.reactivex.Observer;

public interface DiscoveryService {

    void getItems(Observer observer);

    void get();
}
