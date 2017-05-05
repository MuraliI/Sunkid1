package com.rcl.excalibur.domain.service;


import com.rcl.excalibur.domain.SubCategory;

import java.util.List;

import io.reactivex.observers.DisposableObserver;

public interface SubCategoryServices {

    void getSubCategories(DisposableObserver<List<SubCategory>> observer, String sailingId);
}
