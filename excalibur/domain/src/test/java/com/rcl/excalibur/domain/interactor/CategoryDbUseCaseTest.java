package com.rcl.excalibur.domain.interactor;

import com.rcl.excalibur.domain.Category;
import com.rcl.excalibur.domain.repository.CategoryRepository;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.verify;

public class CategoryDbUseCaseTest {
    @Mock CategoryRepository categoryRepository;
    CategoryDbUseCase useCase;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        useCase = new CategoryDbUseCase(categoryRepository);
    }

    @Test
    public void create() throws Exception {
        final Category category = new Category();
        useCase.create(category);
        verify(categoryRepository).create(category);
    }

    @Test
    public void getAll() throws Exception {
        useCase.getAll();
        verify(categoryRepository).getAll();
    }

    @Test
    public void get() throws Exception {
        final long categoryId = 1;
        useCase.get(categoryId);
        verify(categoryRepository).get(categoryId);
    }
}