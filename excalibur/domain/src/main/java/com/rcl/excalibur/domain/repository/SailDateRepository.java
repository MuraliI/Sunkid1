package com.rcl.excalibur.domain.repository;


import com.rcl.excalibur.domain.SailDateInfo;


public interface SailDateRepository {

    void create(SailDateInfo sailDateInfoEvent);

     SailDateInfo get(String id);
     void deleteAll();
}
