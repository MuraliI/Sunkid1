package com.rcl.excalibur.domain.repository;


import com.rcl.excalibur.domain.Menu;
import com.rcl.excalibur.domain.MenuItem;

import java.util.List;

public interface MenuRepository {
    void create(Menu menu);

    void deleteAll();

    List<Menu> getAll();

    List<MenuItem> getAllMenuItemByMenuName(String menuName);

}
