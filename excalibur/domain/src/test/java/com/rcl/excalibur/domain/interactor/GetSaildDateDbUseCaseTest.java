package com.rcl.excalibur.domain.interactor;

import com.rcl.excalibur.domain.repository.SailDateRepository;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.*;

public class GetSaildDateDbUseCaseTest {

    GetSaildDateDbUseCase useCase;
    @Mock SailDateRepository repository;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        useCase = new GetSaildDateDbUseCase(repository);

    }

    @Test
    public void get() throws Exception {
        Assert.assertEquals(repository, useCase.getData());
    }

}