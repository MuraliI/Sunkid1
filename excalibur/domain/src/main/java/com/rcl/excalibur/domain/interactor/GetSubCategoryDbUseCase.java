package com.rcl.excalibur.domain.interactor;


import com.rcl.excalibur.domain.SubCategory;
import com.rcl.excalibur.domain.repository.SubCategoryRepository;

import java.util.List;

import io.reactivex.Observer;

public class GetSubCategoryDbUseCase extends UseCaseSync<SubCategoryRepository> {

    public GetSubCategoryDbUseCase(SubCategoryRepository subCategoryRepository) {
        super(subCategoryRepository);
    }

    public void getAll(Observer<List<SubCategory>> observer) {
        getData().getAll(observer);
    }

    public SubCategory get(String subCategoryId) {
        return getData().get(subCategoryId);
    }

}
