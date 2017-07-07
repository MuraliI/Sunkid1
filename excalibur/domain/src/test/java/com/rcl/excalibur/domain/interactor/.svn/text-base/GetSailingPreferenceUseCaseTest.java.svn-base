package com.rcl.excalibur.domain.interactor;

import com.rcl.excalibur.domain.preference.SailingPreferences;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

public class GetSailingPreferenceUseCaseTest {

    GetSailingPreferenceUseCase getSailingPreferenceUseCase;
    @Mock SailingPreferences sailingPreferences;
    private final String NEYDAY = "Day 1";


    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        getSailingPreferenceUseCase = new GetSailingPreferenceUseCase(sailingPreferences);

    }

    @Test
    public void putDay() throws Exception {
        getSailingPreferenceUseCase.putDay(NEYDAY);
        Mockito.verify(sailingPreferences).putDay(NEYDAY);
    }

    @Test
    public void getDay() throws Exception {
        Assert.assertEquals(sailingPreferences, getSailingPreferenceUseCase.getData());

    }

}