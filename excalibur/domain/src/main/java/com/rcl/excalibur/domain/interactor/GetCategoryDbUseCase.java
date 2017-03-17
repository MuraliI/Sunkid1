package com.rcl.excalibur.domain.interactor;


import com.rcl.excalibur.domain.Category;
import com.rcl.excalibur.domain.repository.CategoryRepository;

import java.util.List;

import javax.inject.Inject;

public class GetCategoryDbUseCase extends UseCaseSync {

    private final CategoryRepository categoryRepository;

    @Inject
    GetCategoryDbUseCase(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    List<Category> getAll() {
        return categoryRepository.getAll();
    }

    Category get(long categoryId) {
        return categoryRepository.get(categoryId);
    }
}
