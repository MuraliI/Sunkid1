package com.rcl.excalibur.domain.repository;


import com.rcl.excalibur.domain.Menu;

import java.util.List;

public interface MenuRepository {
    void create(Menu menu);

    void deleteAll();

    List<Menu> getAll();

}
