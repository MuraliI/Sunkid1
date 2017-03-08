package com.rcl.excalibur.domain.repository;


import com.rcl.excalibur.domain.DiscoverItem;

import java.util.List;

import io.reactivex.Observable;

public interface DiscoverItemRepository {

    Observable<List<DiscoverItem>> listBasicBy(String type);

    Observable<DiscoverItem> getFullBy(String discoverItemId);
}
