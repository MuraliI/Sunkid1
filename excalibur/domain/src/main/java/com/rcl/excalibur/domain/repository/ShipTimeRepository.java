package com.rcl.excalibur.domain.repository;


import com.rcl.excalibur.domain.ShipTime;

public interface ShipTimeRepository {

    ShipTime get();

    void update(String value);

}
