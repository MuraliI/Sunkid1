package com.rcl.excalibur.domain.repository;


import com.rcl.excalibur.domain.Menu;
import com.rcl.excalibur.domain.MenuItem;

import java.util.List;

public interface MenuRepository {
    void create(Menu menu, String venueCode);

    void deleteAll();

    void deleteAllByVenueCode(String venueCode);

    List<Menu> getAll();

    List<Menu> getAllByVenueCode(String venueCode);

    List<MenuItem> getAllMenuItemByMenuName(String menuName);

    List<String> getAllMenuName(String venueCode);

}
