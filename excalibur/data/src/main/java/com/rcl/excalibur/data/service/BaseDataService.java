package com.rcl.excalibur.data.service;


import com.rcl.excalibur.data.mapper.BaseDataMapper;

public abstract class BaseDataService<I, E, T> {

    private final BaseDataMapper<I, E, T> dataMapper;

    protected BaseDataService(BaseDataMapper dataMapper) {
        this.dataMapper = dataMapper;
    }

    protected BaseDataMapper<I, E, T> getMapper() {
        return dataMapper;
    }

}
