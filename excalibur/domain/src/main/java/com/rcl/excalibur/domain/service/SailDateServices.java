package com.rcl.excalibur.domain.service;


import com.rcl.excalibur.domain.SailDateInfoEvent;

import io.reactivex.Observer;

public interface SailDateServices {

    void getSailDate(Observer<SailDateInfoEvent> observer, String sailId);
    void getSailDate(String sailId);
}
