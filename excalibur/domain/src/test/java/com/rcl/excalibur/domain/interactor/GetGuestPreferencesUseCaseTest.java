package com.rcl.excalibur.domain.interactor;

import com.rcl.excalibur.domain.preference.GuestPreference;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

public class GetGuestPreferencesUseCaseTest {
    GetGuestPreferencesUseCase getGuestPreferencesUseCase;
    @Mock GuestPreference guestPreference;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        getGuestPreferencesUseCase = new GetGuestPreferencesUseCase(guestPreference);
    }

    @Test
    public void getData() throws Exception {
        Assert.assertEquals(guestPreference, getGuestPreferencesUseCase.getData());
    }

    @Test
    public void putName() throws Exception {
        String value = "1";
        getGuestPreferencesUseCase.putName(value);
        Mockito.verify(guestPreference).putName(value);
    }

    @Test
    public void putLastname() throws Exception {
        String value = "1";
        getGuestPreferencesUseCase.putLastname(value);
        Mockito.verify(guestPreference).putLastName(value);
    }

    @Test
    public void putPassword() throws Exception {
        String value = "1";
        getGuestPreferencesUseCase.putPassword(value);
        Mockito.verify(guestPreference).putPassword(value);
    }


    @Test
    public void putEmail() throws Exception {
        String value = "1";
        getGuestPreferencesUseCase.putEmail(value);
        Mockito.verify(guestPreference).putEmail(value);
    }


    @Test
    public void putQuestion() throws Exception {
        String value = "1";
        getGuestPreferencesUseCase.putQuestion(value);
        Mockito.verify(guestPreference).putQuestion(value);
    }


    @Test
    public void putAnswer() throws Exception {
        String value = "1";
        getGuestPreferencesUseCase.putAnswer(value);
        Mockito.verify(guestPreference).putAnswer(value);
    }


    @Test
    public void putVersion() throws Exception {
        String value = "1";
        getGuestPreferencesUseCase.putVersion(value);
        Mockito.verify(guestPreference).putVersion(value);
    }


    @Test
    public void putAcceptTime() throws Exception {
        long value = 1234L;
        getGuestPreferencesUseCase.putAcceptTime(value);
        Mockito.verify(guestPreference).putAcceptTime(value);

    }


    @Test
    public void putBrand() throws Exception {
        String value = "1";
        getGuestPreferencesUseCase.putBrand(value);
        Mockito.verify(guestPreference).putBrand(value);
    }

    @Test
    public void getBrand() throws Exception {
        getGuestPreferencesUseCase.getBrand();
        Mockito.verify(guestPreference).getBrand();
    }


    @Test
    public void getEmail() throws Exception {
        getGuestPreferencesUseCase.getEmail();
        Mockito.verify(guestPreference).getEmail();

    }

    @Test
    public void getName() throws Exception {
        getGuestPreferencesUseCase.getName();
        Mockito.verify(guestPreference).getName();

    }


    @Test
    public void getLastname() throws Exception {
        getGuestPreferencesUseCase.getLastname();
        Mockito.verify(guestPreference).getLastName();

    }

    @Test
    public void getPassword() throws Exception {
        getGuestPreferencesUseCase.getPassword();
        Mockito.verify(guestPreference).getPassword();

    }

    @Test
    public void getQuestion() throws Exception {
        getGuestPreferencesUseCase.getQuestion();
        Mockito.verify(guestPreference).getQuestion();

    }

    @Test
    public void getAnswer() throws Exception {
        getGuestPreferencesUseCase.getAnswer();
        Mockito.verify(guestPreference).getAnswer();

    }

    @Test
    public void getVersion() throws Exception {
        getGuestPreferencesUseCase.getVersion();
        Mockito.verify(guestPreference).getVersion();

    }

    @Test
    public void getAcceptTime() throws Exception {
        getGuestPreferencesUseCase.getAcceptTime();
        Mockito.verify(guestPreference).getAcceptTime();

    }

}