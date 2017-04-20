package com.rcl.excalibur.domain.repository;


import com.rcl.excalibur.domain.SailDateInfo;

import java.util.List;


public interface SailDateRepository {

    void create(SailDateInfo sailDateInfoEvent);

    SailDateInfo get();

    List<SailDateInfo> getAll();

    void deleteAll();
}
