package com.rcl.excalibur.data.service;

import com.rcl.excalibur.data.mapper.WeatherCurrentResponseDataMapper;
import com.rcl.excalibur.data.service.response.WeatherCurrentResponse;
import com.rcl.excalibur.data.service.response.WeatherInfoResponse;
import com.rcl.excalibur.domain.ShipLocationInfo;
import com.rcl.excalibur.domain.ShipStatsInfo;
import com.rcl.excalibur.domain.WeatherCurrent;
import com.rcl.excalibur.domain.repository.WeatherCurrentRepository;
import com.rcl.excalibur.domain.service.WeatherServices;

import io.reactivex.Observable;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import timber.log.Timber;

import static com.rcl.excalibur.data.utils.ServiceUtil.getWeatherApi;

public class WeatherInfoServicesImpl extends BaseDataService<WeatherCurrent, WeatherCurrentResponse, Void> implements WeatherServices {

    //FIXME when service return value
    private static final String DURATION_HARDCODED = "2";
    WeatherCurrentRepository repository;

    public WeatherInfoServicesImpl(WeatherCurrentRepository repository) {
        super(new WeatherCurrentResponseDataMapper());
        this.repository = repository;
    }

    @Override
    public void weatherInfo(ShipStatsInfo shipStatsInfo, DisposableObserver<Boolean> observer) {

        Observable.create((ObservableOnSubscribe<Boolean>) observableEmitter -> {

            ShipLocationInfo shipLocationInfo = shipStatsInfo.getShipLocation();
            Call<WeatherInfoResponse> call = getWeatherApi().weatherInfo(String.valueOf(shipLocationInfo.getLatitude()),
                    String.valueOf(shipLocationInfo.getLongitude()), DURATION_HARDCODED);

            call.enqueue(new Callback<WeatherInfoResponse>() {
                @Override
                public void onResponse(Call<WeatherInfoResponse> call, Response<WeatherInfoResponse> response) {
                    if (!response.isSuccessful()) {
                        return;
                    }

                    WeatherCurrent weatherCurrent = getMapper().transform(response.body().getCurrent(), null);
                    repository.deleteAll();
                    repository.create(weatherCurrent);
                    observableEmitter.onNext(true);
                    observableEmitter.onComplete();
                }

                @Override
                public void onFailure(Call<WeatherInfoResponse> call, Throwable t) {
                    Timber.e("Weather error", t.getMessage());
                    observableEmitter.onError(t);
                }
            });


        }).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);


    }
}
