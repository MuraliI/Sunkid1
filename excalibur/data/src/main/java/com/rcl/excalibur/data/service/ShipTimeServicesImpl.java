package com.rcl.excalibur.data.service;


import com.rcl.excalibur.data.service.response.ShipTimeResponse;
import com.rcl.excalibur.domain.repository.ShipTimeRepository;
import com.rcl.excalibur.domain.service.ShipTimeServices;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import timber.log.Timber;

import static com.rcl.excalibur.data.utils.ServiceUtil.getShipTimeApi;

public class ShipTimeServicesImpl extends BaseDataService<String, String, Void> implements ShipTimeServices {

    private final ShipTimeRepository shipTimeRepository;

    public ShipTimeServicesImpl(ShipTimeRepository shipTimeRepository) {
        super(null);
        this.shipTimeRepository = shipTimeRepository;
    }


    @Override
    public void getShipTime() {

        Call<ShipTimeResponse> call = getShipTimeApi().getShipTime();

        call.enqueue(new Callback<ShipTimeResponse>() {
            @Override
            public void onResponse(Call<ShipTimeResponse> call, Response<ShipTimeResponse> response) {
                if (!response.isSuccessful()) {
                    return;
                }
                shipTimeRepository.update(response.body().getUtcTimezoneOffset());
            }

            @Override
            public void onFailure(Call<ShipTimeResponse> call, Throwable t) {
                Timber.e("error", t.getMessage());
            }
        });
    }
}
