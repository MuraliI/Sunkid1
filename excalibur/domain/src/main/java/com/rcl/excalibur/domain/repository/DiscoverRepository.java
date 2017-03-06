package com.rcl.excalibur.domain.repository;


import com.rcl.excalibur.domain.DiscoverItem;

import java.util.List;

import io.reactivex.Observable;

public interface DiscoverRepository {

    Observable<List<DiscoverItem>> listBy(String type);
}
