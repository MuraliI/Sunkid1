package com.rcl.excalibur.domain.interactor;


import com.rcl.excalibur.domain.SubCategory;
import com.rcl.excalibur.domain.repository.SubCategoryRepository;

import java.util.List;

public class GetSubCategoryDbUseCase extends UseCaseSync<SubCategoryRepository> {

    public GetSubCategoryDbUseCase(SubCategoryRepository subCategoryRepository) {
        super(subCategoryRepository);
    }

    public List<SubCategory> getAll() {
        return getData().getAll();
    }

    public SubCategory get(String subCategoryId) {
        return getData().get(subCategoryId);
    }

}
