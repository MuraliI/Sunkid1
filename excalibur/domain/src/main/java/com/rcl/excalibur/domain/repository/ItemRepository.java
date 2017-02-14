package com.rcl.excalibur.domain.repository;


import com.rcl.excalibur.domain.Item;

import java.util.List;

import io.reactivex.Observable;

public interface ItemRepository {

    Observable<List<Item>> items();
}
