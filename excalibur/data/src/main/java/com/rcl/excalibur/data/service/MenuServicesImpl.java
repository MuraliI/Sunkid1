package com.rcl.excalibur.data.service;

import com.rcl.excalibur.data.mapper.MediaItemDataMapper;
import com.rcl.excalibur.data.mapper.MenuDataMapper;
import com.rcl.excalibur.data.repository.MenuDataRepository;
import com.rcl.excalibur.data.service.response.GetMenuResponse;
import com.rcl.excalibur.data.service.response.MenuResponse;
import com.rcl.excalibur.domain.Menu;
import com.rcl.excalibur.domain.service.MenuServices;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import timber.log.Timber;

import static com.rcl.excalibur.data.utils.ServiceUtil.getMenuApi;


public class MenuServicesImpl extends BaseDataService<Menu, MenuResponse, Void> implements MenuServices {

    private static final String SAILING_ID = "1492905600000";
    private final MenuDataRepository repository;

    public MenuServicesImpl(MenuDataRepository repository) {
        super(new MenuDataMapper(new MediaItemDataMapper()));
        this.repository = repository;
    }


    @Override
    public void getMenu(DisposableObserver<List<Menu>> observer, String venueCode) {
        Observable<List<Menu>> observable = Observable.create(emitter -> {
            Call<GetMenuResponse> call = getMenuApi().getMenus(SAILING_ID, venueCode);
            call.enqueue(new Callback<GetMenuResponse>() {
                @Override
                public void onResponse(Call<GetMenuResponse> call, Response<GetMenuResponse> response) {
                    if (response.isSuccessful()) {
                        List<Menu> menus = getMapper().transform(response.body().getMenu(), null);
                        repository.deleteAll();
                        for (Menu menu : menus) {
                            repository.create(menu);
                        }
                        emitter.onComplete();
                    } else {
                        emitter.onError(null);
                    }
                }

                @Override
                public void onFailure(Call<GetMenuResponse> call, Throwable t) {
                    Timber.e("error GetMenuResponse", t.getMessage());
                    emitter.onError(t);

                }
            });
        });

        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }
}
