package com.rcl.excalibur.domain.interactor;


import com.rcl.excalibur.domain.Offering;
import com.rcl.excalibur.domain.Product;
import com.rcl.excalibur.domain.repository.OfferingRepository;

import java.util.Date;
import java.util.List;

import io.reactivex.Observer;

public class GetOfferingsDbUseCase extends UseCaseSync<OfferingRepository> {

    public GetOfferingsDbUseCase(OfferingRepository data) {
        super(data);
    }

    public void getAllForDay(Date date, Observer<List<Offering>> observer) {
        getData().getForDay(date, observer);
    }

    public void getOfferingsForProduct(Product product, Observer<List<Offering>> observer) {
        getData().getOfferingForProduct(product, observer);
    }
}
