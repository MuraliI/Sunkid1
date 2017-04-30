package com.rcl.excalibur.data.service;

import com.rcl.excalibur.data.mapper.MediaItemDataMapper;
import com.rcl.excalibur.data.mapper.MenuDataMapper;
import com.rcl.excalibur.data.repository.MenuDataRepository;
import com.rcl.excalibur.data.service.response.GetMenuResponse;
import com.rcl.excalibur.data.service.response.MenuResponse;
import com.rcl.excalibur.domain.Menu;
import com.rcl.excalibur.domain.service.MenuServices;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import timber.log.Timber;

import static com.rcl.excalibur.data.utils.ServiceUtil.getMenuApi;


public class MenuServicesImpl extends BaseDataService<Menu, MenuResponse, Void> implements MenuServices {

    private static final String SAILING_ID = "AL20170430";
    private final MenuDataRepository repository;

    public MenuServicesImpl(MenuDataRepository repository) {
        super(new MenuDataMapper(new MediaItemDataMapper()));
        this.repository = repository;
    }


    @Override
    public void getMenu() {
        Call<GetMenuResponse> call = getMenuApi().getMenus(SAILING_ID, "GIOV");
        call.enqueue(new Callback<GetMenuResponse>() {
            @Override
            public void onResponse(Call<GetMenuResponse> call, Response<GetMenuResponse> response) {
                if (response.isSuccessful()) {
                    repository.deleteAll();
                    List<Menu> menus = getMapper().transform(response.body().getMenu(), null);
                    for (Menu menu : menus) {
                        repository.create(menu);

                    }
                    repository.getAll();
                }
            }

            @Override
            public void onFailure(Call<GetMenuResponse> call, Throwable t) {
                Timber.e("Error", t.getMessage());
            }
        });
    }
}
