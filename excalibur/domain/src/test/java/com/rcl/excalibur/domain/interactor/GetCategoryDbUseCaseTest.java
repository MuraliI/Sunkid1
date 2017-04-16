package com.rcl.excalibur.domain.interactor;

import com.rcl.excalibur.domain.Category;
import com.rcl.excalibur.domain.repository.CategoryRepository;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import io.reactivex.observers.TestObserver;

import static org.mockito.Mockito.verify;

public class GetCategoryDbUseCaseTest {
    @Mock CategoryRepository categoryRepository;
    GetCategoryDbUseCase useCase;
    TestObserver<List<Category>> observer;

    @Before
    public void setUp() throws Exception {
        observer = new TestObserver<>();
        MockitoAnnotations.initMocks(this);
        useCase = new GetCategoryDbUseCase(categoryRepository);
    }

    @Test
    public void getData() throws Exception {
        Assert.assertEquals(categoryRepository, useCase.getData());
    }

    @Test
    public void getAll() throws Exception {
        useCase.getAll(observer);
        verify(categoryRepository).getAll(observer);
    }

    @Test
    public void get() throws Exception {
        final String categoryId = "1";
        useCase.get(categoryId);
        verify(categoryRepository).get(categoryId);
    }
}