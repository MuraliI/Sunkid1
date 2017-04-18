package com.rcl.excalibur.domain.repository;


import com.rcl.excalibur.domain.SubCategory;

import java.util.List;

import io.reactivex.Observer;

public interface SubCategoryRepository {

    void create(SubCategory subCategory);

    void create(List<SubCategory> subCategories);

    void getAll(Observer<List<SubCategory>> observer);

    SubCategory get(String id);

    void deleteAll();
}
