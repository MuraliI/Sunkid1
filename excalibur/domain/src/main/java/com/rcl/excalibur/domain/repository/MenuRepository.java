package com.rcl.excalibur.domain.repository;


import com.rcl.excalibur.domain.SailDateInfo;

public interface MenuRepository {
    void create(SailDateInfo sailDateInfoEvent);
    void deleteAll();
}
