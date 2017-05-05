package com.rcl.excalibur.domain.interactor;

import com.rcl.excalibur.domain.repository.MenuRepository;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static junit.framework.Assert.assertEquals;

public class GetMenuDbUseCaseTest {
    GetMenuDbUseCase useCase;
    @Mock MenuRepository repository;
    private final String MENU_NAME = "Dinner";

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        useCase = new GetMenuDbUseCase(repository);
    }
    @Test
    public void getData() throws Exception {
        assertEquals(repository, useCase.getData());
    }
    @Test
    public void getAll() throws Exception {
        repository.getAll();
        Mockito.verify(repository).getAll();

    }

    @Test
    public void getAllMenuItemByMenuName() throws Exception {
        useCase.getAllMenuItemByMenuName(MENU_NAME);
        Mockito.verify(repository).getAllMenuItemByMenuName(MENU_NAME);
    }

    @Test
    public void get() throws Exception {
        useCase.getAllMenuName();
        Mockito.verify(repository).getAllMenuName();
    }
}