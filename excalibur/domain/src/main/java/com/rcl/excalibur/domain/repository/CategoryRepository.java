package com.rcl.excalibur.domain.repository;


import com.rcl.excalibur.domain.Category;

import java.util.List;

import io.reactivex.Observer;

public interface CategoryRepository {

    void create(Category category);

    void getAll(Observer<List<Category>> observer);

    Category get(String id);
}
