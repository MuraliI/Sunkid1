package com.rcl.excalibur.data.service;


import com.rcl.excalibur.data.mapper.BaseDataMapper;

public abstract class BaseDataService<I, E> {

    private final BaseDataMapper<I, E> dataMapper;

    protected BaseDataService(BaseDataMapper dataMapper) {
        this.dataMapper = dataMapper;
    }

    protected BaseDataMapper<I, E> getMapper() {
        return dataMapper;
    }

}
