package com.rcl.excalibur.domain.repository;


import com.rcl.excalibur.domain.Offering;

import java.util.Date;
import java.util.List;

import io.reactivex.Observer;

public interface OfferingRepository {
    void create(List<Offering> offeringList);

    void getForDay(final Date date, final Observer<List<Offering>> observer);

    void deleteAll();
}
