package com.rcl.excalibur.domain.service;


import io.reactivex.observers.DisposableObserver;

public interface DiscoverServices {

    void getProducts(DisposableObserver<Boolean> productsObtained);

    void getCategories();

    void getPromotionMessages();

    void getEntertainments();

    void getSpas();

    void getExcursion();

    void getDinings();

    void getActivities();

    void getSubCategories();

}
