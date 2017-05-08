package com.rcl.excalibur.domain.repository;


import com.rcl.excalibur.domain.ShipStatsInfo;

import java.util.List;

public interface ShipStatsRepository {
    void create(ShipStatsInfo shipStatsInfo);

    ShipStatsInfo get();

    List<ShipStatsInfo> getAll();

    void deleteAll();
}
