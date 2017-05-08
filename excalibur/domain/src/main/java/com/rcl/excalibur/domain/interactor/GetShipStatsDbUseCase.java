package com.rcl.excalibur.domain.interactor;


import com.rcl.excalibur.domain.ShipStatsInfo;
import com.rcl.excalibur.domain.repository.ShipStatsRepository;

public class GetShipStatsDbUseCase extends UseCaseSync<ShipStatsRepository> {


    public GetShipStatsDbUseCase(ShipStatsRepository shipStatsRepository) {
        super(shipStatsRepository);
    }

    public ShipStatsInfo get() {
        return getData().get();
    }
}
