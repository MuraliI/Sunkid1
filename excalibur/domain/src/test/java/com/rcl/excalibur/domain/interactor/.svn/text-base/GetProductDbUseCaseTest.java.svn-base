package com.rcl.excalibur.domain.interactor;

import com.rcl.excalibur.domain.repository.ProductRepository;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

public class GetProductDbUseCaseTest {

    @Mock ProductRepository productRepository;
    GetProductDbUseCase getProductDbUseCase;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        getProductDbUseCase = new GetProductDbUseCase(productRepository);
    }

    @Test
    public void getData() throws Exception {
        Assert.assertEquals(productRepository, getProductDbUseCase.getData());
    }

    @Test
    public void getAll() throws Exception {
        getProductDbUseCase.getAll();
        Mockito.verify(productRepository).getAll();
    }

    @Test
    public void get() throws Exception {
        getProductDbUseCase.get("1");
        Mockito.verify(productRepository).get("1");
    }

    @Test
    public void getByCategory() throws Exception {
        getProductDbUseCase.getByCategory("type", 1, 0);
        Mockito.verify(productRepository).getByCategory("type", 1, 0);
    }

    @Test
    public void getByChildCategory() throws Exception {
        getProductDbUseCase.getByChildCategory("type", 1, 0);
        Mockito.verify(productRepository).getByChildCategory("type", 1, 0);
    }


}