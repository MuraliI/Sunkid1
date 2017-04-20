package com.rcl.excalibur.domain.interactor;


import com.rcl.excalibur.domain.ShipTime;
import com.rcl.excalibur.domain.repository.ShipTimeRepository;

public class GetShipTimeDbUseCase extends UseCaseSync<ShipTimeRepository> {

    public GetShipTimeDbUseCase(ShipTimeRepository shipTimeRepository) {
        super(shipTimeRepository);
    }

    public ShipTime get() {
        return getData().get();
    }

}
