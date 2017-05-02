package com.rcl.excalibur.data.service;

import com.rcl.excalibur.data.mapper.MediaItemDataMapper;
import com.rcl.excalibur.data.mapper.MenuDataMapper;
import com.rcl.excalibur.data.repository.MenuDataRepository;
import com.rcl.excalibur.data.service.response.GetMenuResponse;
import com.rcl.excalibur.data.service.response.MenuResponse;
import com.rcl.excalibur.domain.Menu;
import com.rcl.excalibur.domain.service.MenuServices;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;
import timber.log.Timber;

import static com.rcl.excalibur.data.utils.ServiceUtil.getMenuApi;


public class MenuServicesImpl extends BaseDataService<Menu, MenuResponse, Void> implements MenuServices {

    private static final String SAILING_ID = "AL20170702";
    private final MenuDataRepository repository;

    public MenuServicesImpl(MenuDataRepository repository) {
        super(new MenuDataMapper(new MediaItemDataMapper()));
        this.repository = repository;
    }


    @Override
    public void getMenu() {
        new Thread(() -> {
            Call<GetMenuResponse> call = getMenuApi().getMenus(SAILING_ID, "GIOV");
            MenuProcessor menuProcessor = new MenuProcessor();
            try {
                menuProcessor.onResponse(call.execute());
            } catch (IOException e) {
                menuProcessor.onFailure(e);
            }
        }).start();
    }

    private class MenuProcessor {
        void onResponse(Response<GetMenuResponse> response) {
            if (response.body() != null) {
                List<Menu> menus = getMapper().transform(response.body().getMenu(), null);
                for (Menu menu : menus) {
                    repository.create(menu);

                }
            }
        }

        void onFailure(Throwable t) {
            Timber.e("error GetMenuResponse", t.getMessage());
        }
    }
}
