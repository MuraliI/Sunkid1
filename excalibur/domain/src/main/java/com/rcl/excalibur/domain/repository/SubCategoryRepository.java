package com.rcl.excalibur.domain.repository;


import com.rcl.excalibur.domain.SubCategory;

import java.util.List;

public interface SubCategoryRepository {

    void create(SubCategory subCategory);

    void create(List<SubCategory> subCategories);

    List<SubCategory> getAll();

    SubCategory get(String id);

    void deleteAll();
}
