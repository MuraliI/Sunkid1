package com.rcl.excalibur.domain.interactor;


import com.rcl.excalibur.domain.Category;
import com.rcl.excalibur.domain.repository.CategoryRepository;

import java.util.List;

public class GetCategoryDbUseCase extends UseCaseSync<CategoryRepository> {

    public GetCategoryDbUseCase(CategoryRepository categoryRepository) {
        super(categoryRepository);
    }

    List<Category> getAll() {
        return getData().getAll();
    }

    public Category get(String categoryId) {
        return getData().get(categoryId);
    }
}
