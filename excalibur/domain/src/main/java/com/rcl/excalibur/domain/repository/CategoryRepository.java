package com.rcl.excalibur.domain.repository;


import com.rcl.excalibur.domain.Category;

import java.util.List;

public interface CategoryRepository {

    void create(Category category);

    void create(List<Category> categories);

    List<Category> getAll();

    Category get(String id);
}
