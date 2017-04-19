package com.rcl.excalibur.data.service;


import com.rcl.excalibur.data.mapper.SailDateInfoDataMapper;
import com.rcl.excalibur.data.repository.SailDateDataRepository;
import com.rcl.excalibur.data.service.response.SailingInfoResponse;
import com.rcl.excalibur.data.service.response.itinerary.SailDateResponse;
import com.rcl.excalibur.domain.SailDateInfo;
import com.rcl.excalibur.domain.service.SailDateServices;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import timber.log.Timber;

import static com.rcl.excalibur.data.utils.ServiceUtil.getSailDateApi;

public class SailDateServicesImpl extends BaseDataService<SailDateInfo, SailingInfoResponse , Void> implements SailDateServices {

    private static final String SAILING_ID = "1492905600000";
    private final SailDateDataRepository repository;

    public SailDateServicesImpl(SailDateDataRepository repository) {
        super(new SailDateInfoDataMapper());
        this.repository = repository;
    }

    @Override
    public void getSailDate() {
        Call<SailDateResponse> call = getSailDateApi().getEvents(SAILING_ID);

        call.enqueue(new Callback<SailDateResponse>() {
            @Override
            public void onResponse(Call<SailDateResponse> call, Response<SailDateResponse> response) {
                // TODO: add validation succesfull response when webservice integrate
                if (response.body() != null) {
                    repository.deleteAll();
                    SailDateInfo dateInfoEntity = getMapper().transform(response.body().getSailingInfo(), null);
                    repository.create(dateInfoEntity);
                }

            }

            @Override
            public void onFailure(Call<SailDateResponse> call, Throwable t) {
                //Handle failure
                Timber.e("error SailDateResponse", t.getMessage());
            }
        });
    }


}
