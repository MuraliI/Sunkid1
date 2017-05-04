package com.rcl.excalibur.data.service;


import com.rcl.excalibur.data.mapper.ShipStatsDataMapper;
import com.rcl.excalibur.data.service.response.ShipStatsResponse;
import com.rcl.excalibur.domain.ShipStatsInfo;
import com.rcl.excalibur.domain.repository.ShipStatsRepository;
import com.rcl.excalibur.domain.service.ShipStatsServices;

import retrofit2.Call;
import retrofit2.Response;
import timber.log.Timber;

import static com.rcl.excalibur.data.utils.ServiceUtil.getShipStatsApi;

public class ShipStatsServicesImpl extends BaseDataService<ShipStatsInfo, ShipStatsResponse, Void> implements ShipStatsServices {

    private ShipStatsRepository repository;

    protected ShipStatsServicesImpl(ShipStatsRepository shipStatsRepository) {
        super(new ShipStatsDataMapper());
        this.repository = shipStatsRepository;
    }

    @Override
    public void getShipStats() {
        new Thread(() -> {

            Call<ShipStatsResponse> call = getShipStatsApi().getShipStats();
            ShipStatsProcessor shipStatsProcessor = new ShipStatsProcessor();
            try {
                shipStatsProcessor.onResponse(call.execute());

            } catch (Exception e) {
                shipStatsProcessor.onFailure(e);
            }

        }).start();
    }

    private class ShipStatsProcessor {

        void onResponse(Response<ShipStatsResponse> response) {
            // TODO: add validation succesfull response when webservice integrate
            if (response.body() != null) {
                repository.deleteAll();
                ShipStatsInfo shipStatsInfo = getMapper().transform(response.body(), null);
                repository.create(shipStatsInfo);
            }
        }

        void onFailure(Throwable t) {
            Timber.e("error SailDateResponse", t.getMessage());
        }
    }
}
