package com.rcl.excalibur.domain.repository;


import com.rcl.excalibur.domain.Offering;
import com.rcl.excalibur.domain.Product;

import java.util.Date;
import java.util.List;

public interface OfferingRepository {
    void create(List<Offering> offeringList);

    List<Offering> getOfferingsForDay(final Date date);

    List<Offering> getOfferingsForProduct(final Product product);

    void deleteAll();
}
