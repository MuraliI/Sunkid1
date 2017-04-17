package com.rcl.excalibur.domain.repository;


import com.rcl.excalibur.domain.Promotion;

import java.util.List;

import io.reactivex.Observer;

public interface PromotionRepository {

    void create(Promotion category);

    void getAll(Observer<List<Promotion>> observer);

    Promotion get(String id);
}
