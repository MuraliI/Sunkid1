package com.rcl.excalibur.domain.repository;


import com.rcl.excalibur.domain.DiscoverItem;

import java.util.List;

public interface DiscoverItemRepository {

    List<DiscoverItem> listAll(String type);

    DiscoverItem get(int id);

    void create(List<DiscoverItem> discoverItems);
}
