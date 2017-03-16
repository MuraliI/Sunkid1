package com.rcl.excalibur.domain.interactor;


import com.rcl.excalibur.domain.Category;
import com.rcl.excalibur.domain.repository.CategoryRepository;

import java.util.List;

import javax.inject.Inject;

public class CategoryDbUseCase extends UseCaseSync {

    private final CategoryRepository categoryRepository;

    @Inject
    CategoryDbUseCase(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public void create(final Category category) {
        categoryRepository.create(category);
    }

    List<Category> getAll() {
        return categoryRepository.getAll();
    }

    Category get(long categoryId) {
        return categoryRepository.get(categoryId);
    }
}
