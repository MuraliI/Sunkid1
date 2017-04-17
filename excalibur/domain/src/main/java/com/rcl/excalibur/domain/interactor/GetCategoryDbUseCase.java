package com.rcl.excalibur.domain.interactor;


import com.rcl.excalibur.domain.Category;
import com.rcl.excalibur.domain.repository.CategoryRepository;

import java.util.List;

import io.reactivex.Observer;

public class GetCategoryDbUseCase extends UseCaseSync<CategoryRepository> {

    public GetCategoryDbUseCase(CategoryRepository categoryRepository) {
        super(categoryRepository);
    }

    void getAll(Observer<List<Category>> observer) {
        getData().getAll(observer);
    }

    Category get(String categoryId) {
        return getData().get(categoryId);
    }
}
