package com.rcl.excalibur.data.service;


import com.rcl.excalibur.data.mapper.ShipStatsDataMapper;
import com.rcl.excalibur.data.service.response.ShipStatsResponse;
import com.rcl.excalibur.domain.ShipStatsInfo;
import com.rcl.excalibur.domain.repository.ShipStatsRepository;
import com.rcl.excalibur.domain.service.ShipStatsServices;

import io.reactivex.Observable;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Response;
import timber.log.Timber;

import static com.rcl.excalibur.data.utils.ServiceUtil.getShipStatsApi;

public class ShipStatsServicesImpl extends BaseDataService<ShipStatsInfo, ShipStatsResponse, Void> implements ShipStatsServices {

    private ShipStatsRepository repository;

    public ShipStatsServicesImpl(ShipStatsRepository shipStatsRepository) {
        super(new ShipStatsDataMapper());
        this.repository = shipStatsRepository;
    }

    @Override
    public void getShipStats(DisposableObserver<Boolean> serviceCallCompletedObserver) {
        Observable.create((ObservableOnSubscribe<Boolean>) observableEmitter -> {
            Call<ShipStatsResponse> call = getShipStatsApi().getShipStats();
            ShipStatsProcessor shipStatsProcessor = new ShipStatsProcessor();
            try {
                shipStatsProcessor.onResponse(call.execute());
            } catch (Exception e) {
                shipStatsProcessor.onFailure(e);
            }
            observableEmitter.onNext(true);
        }).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(serviceCallCompletedObserver);
    }

    private class ShipStatsProcessor {

        void onResponse(Response<ShipStatsResponse> response) {
            // TODO: add validation succesfull response when webservice integrate
            if (response.body() != null) {
                if (repository.get() != null)
                    repository.deleteAll();

                ShipStatsInfo shipStatsInfo = getMapper().transform(response.body(), null);
                repository.create(shipStatsInfo);
            }
        }

        void onFailure(Throwable t) {
            Timber.e("error ShipStatsResponse", t.getMessage());
        }
    }
}
