package com.rcl.excalibur.domain.interactor;


import com.rcl.excalibur.domain.Offering;
import com.rcl.excalibur.domain.Product;
import com.rcl.excalibur.domain.repository.OfferingRepository;

import java.util.Date;
import java.util.List;

public class GetOfferingsDbUseCase extends UseCaseSync<OfferingRepository> {

    public GetOfferingsDbUseCase(OfferingRepository data) {
        super(data);
    }

    public List<Offering> getAllForDay(Date date) {
        return getData().getForDay(date);
    }

    public List<Offering> getOfferingsForProduct(Product product) {
        return getData().getOfferingForProduct(product);
    }
}
