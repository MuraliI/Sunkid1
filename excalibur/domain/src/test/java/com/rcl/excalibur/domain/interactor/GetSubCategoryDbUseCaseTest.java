package com.rcl.excalibur.domain.interactor;

import com.rcl.excalibur.domain.repository.SubCategoryRepository;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;


public class GetSubCategoryDbUseCaseTest {

    GetSubCategoryDbUseCase getSubCategoryDbUseCase;
    @Mock SubCategoryRepository subCategoryRepository;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        getSubCategoryDbUseCase = new GetSubCategoryDbUseCase(subCategoryRepository);
    }

    @Test
    public void getData() throws Exception {
        Assert.assertEquals(subCategoryRepository, getSubCategoryDbUseCase.getData());
    }

    @Test
    public void getAll() throws Exception {
        getSubCategoryDbUseCase.getAll();
        Mockito.verify(subCategoryRepository).getAll();
    }

    @Test
    public void get() throws Exception {
        getSubCategoryDbUseCase.get("shorex");
        Mockito.verify(subCategoryRepository).get("shorex");
    }

}