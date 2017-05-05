package com.rcl.excalibur.domain.service;


import com.rcl.excalibur.domain.Category;

import java.util.List;

import io.reactivex.observers.DisposableObserver;

public interface CategoryServices {

    void getCategories(DisposableObserver<List<Category>> observer, String sailingId);
}
