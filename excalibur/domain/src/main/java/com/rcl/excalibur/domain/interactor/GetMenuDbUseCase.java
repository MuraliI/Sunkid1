package com.rcl.excalibur.domain.interactor;


import com.rcl.excalibur.domain.Menu;
import com.rcl.excalibur.domain.MenuItem;
import com.rcl.excalibur.domain.repository.MenuRepository;

import java.util.List;

public class GetMenuDbUseCase extends UseCaseSync<MenuRepository> {

    public GetMenuDbUseCase(MenuRepository data) {
        super(data);
    }

    public List<Menu> getAll() {
        return getData().getAll();
    }

    public List<MenuItem> getAllMenuItemByMenuName(String nameMenu) {
        return getData().getAllMenuItemByMenuName(nameMenu);
    }


}
