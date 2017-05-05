package com.rcl.excalibur.domain.service;


import com.rcl.excalibur.domain.Menu;

import java.util.List;

import io.reactivex.observers.DisposableObserver;

public interface MenuServices {

    void getMenu(DisposableObserver<List<Menu>> observer, String venueCode);
}
