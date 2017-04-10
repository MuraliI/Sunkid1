package com.rcl.excalibur.domain.interactor;

import com.rcl.excalibur.domain.repository.CategoryRepository;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.verify;

public class GetCategoryDbUseCaseTest {
    @Mock CategoryRepository categoryRepository;
    GetCategoryDbUseCase useCase;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        useCase = new GetCategoryDbUseCase(categoryRepository);
    }

    @Test
    public void getAll() throws Exception {
        useCase.getAll();
        verify(categoryRepository).getAll();
    }

    @Test
    public void get() throws Exception {
        final String categoryId = "1";
        useCase.get(categoryId);
        verify(categoryRepository).get(categoryId);
    }
}