package com.rcl.excalibur.domain.repository;


import com.rcl.excalibur.domain.SailDateInfoEvent;


public interface SailDateRepository {

    void create(SailDateInfoEvent sailDateInfoEvent);

     SailDateInfoEvent get(String id);
     void deleteAll();
}
