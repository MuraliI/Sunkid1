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
import retrofit2.Callback;
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
            call.enqueue(new Callback<ShipStatsResponse>() {
                @Override
                public void onResponse(Call<ShipStatsResponse> call, Response<ShipStatsResponse> response) {
                    if (response.body() != null) {
                        if (repository.get() != null)
                            repository.deleteAll();

                        ShipStatsInfo shipStatsInfo = getMapper().transform(response.body(), null);
                        repository.create(shipStatsInfo);

                        observableEmitter.onNext(true);
                    }
                }

                @Override
                public void onFailure(Call<ShipStatsResponse> call, Throwable t) {
                    Timber.e("error ShipStatsResponse", t.getMessage());
                    observableEmitter.onNext(false);
                }
            });
        }).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(serviceCallCompletedObserver);
    }

}
