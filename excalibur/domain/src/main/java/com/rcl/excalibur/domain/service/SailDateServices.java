package com.rcl.excalibur.domain.service;


import com.rcl.excalibur.domain.SailDateEvent;

import java.util.List;

import io.reactivex.Observer;

public interface SailDateServices {

    void getSailDate(Observer<List<SailDateEvent>> observer, String sailId);

}
