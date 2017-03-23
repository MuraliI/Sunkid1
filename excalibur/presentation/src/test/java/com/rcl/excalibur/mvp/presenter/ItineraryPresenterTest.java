package com.rcl.excalibur.mvp.presenter;

import com.rcl.excalibur.R;
import com.rcl.excalibur.mvp.view.ItineraryView;
import com.rcl.excalibur.utils.GreetingUtils;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.verify;

public class ItineraryPresenterTest {

    ItineraryPresenter presenter;
    @Mock ItineraryView view;
    @Mock GreetingUtils getCurrentTime;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        presenter = new ItineraryPresenter(1, view);
    }

    @Test
    public void getGreeting() throws Exception {
        //when(getCurrentTime.getGreeting()).thenReturn(R.string.title_good_afternoon);
        presenter.getGreeting();
        verify(view).setGreetingText(R.string.title_good_afternoon);
    }


}