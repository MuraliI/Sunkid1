package com.rcl.excalibur.domain.repository;


import com.rcl.excalibur.domain.Promotion;

import java.util.List;

public interface PromotionRepository {

    void create(Promotion category);

    List<Promotion> getAll();

    Promotion get(long id);
}
