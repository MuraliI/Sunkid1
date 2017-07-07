package com.rcl.excalibur.data.service;


import com.rcl.excalibur.data.mapper.SailDateInfoDataMapper;
import com.rcl.excalibur.data.repository.SailDateDataRepository;
import com.rcl.excalibur.data.service.response.SailingInfoResponse;
import com.rcl.excalibur.data.service.response.itinerary.SailDateResponse;
import com.rcl.excalibur.domain.SailDateInfo;
import com.rcl.excalibur.domain.service.SailDateServices;

import retrofit2.Call;
import retrofit2.Response;
import timber.log.Timber;

import static com.rcl.excalibur.data.utils.ServiceUtil.getSailDateApi;

public class SailDateServicesImpl extends BaseDataService<SailDateInfo, SailingInfoResponse, Void> implements SailDateServices {

    public static final String SAIL_DATE = "20170702";

    private final SailDateDataRepository repository;

    public SailDateServicesImpl(SailDateDataRepository repository) {
        super(new SailDateInfoDataMapper());
        this.repository = repository;
    }

    @Override
    public void getSailDate() {
        new Thread(() -> {

            Call<SailDateResponse> call = getSailDateApi().getEvents(SAIL_DATE);
            SailDateProcessor sailDateProcessor = new SailDateProcessor();
            try {
                sailDateProcessor.onResponse(call.execute());

            } catch (Exception e) {
                sailDateProcessor.onFailure(e);
            }

        }).start();
    }

    private class SailDateProcessor {

        void onResponse(Response<SailDateResponse> response) {
            // TODO: add validation succesfull response when webservice integrate
            if (response.body() != null) {
                repository.deleteAll();
                SailDateInfo dateInfoEntity = getMapper().transform(response.body().getSailingInfo(), null);
                repository.create(dateInfoEntity);
            }

        }

        void onFailure(Throwable t) {
            Timber.e("error SailDateResponse", t.getMessage());
        }
    }
}
