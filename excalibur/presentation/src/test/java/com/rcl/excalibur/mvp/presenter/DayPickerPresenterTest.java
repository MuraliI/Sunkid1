package com.rcl.excalibur.mvp.presenter;

import android.content.res.Resources;
import android.text.TextUtils;

import com.rcl.excalibur.R;
import com.rcl.excalibur.activity.BaseActivity;
import com.rcl.excalibur.domain.SailDateEvent;
import com.rcl.excalibur.domain.SailDateInfo;
import com.rcl.excalibur.domain.SailDateItinerary;
import com.rcl.excalibur.domain.SailPort;
import com.rcl.excalibur.domain.interactor.GetSaildDateDbUseCase;
import com.rcl.excalibur.domain.interactor.GetSailingPreferenceUseCase;
import com.rcl.excalibur.mvp.view.DayPickerView;
import com.rcl.excalibur.utils.DateUtils;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(PowerMockRunner.class)
@PrepareForTest({TextUtils.class, DateUtils.class})
public class DayPickerPresenterTest {

    DayPickerPresenter presenter;
    @Mock DayPickerView view;
    @Mock GetSailingPreferenceUseCase getSailingPreferenceUseCase;
    @Mock GetSaildDateDbUseCase getSaildDateDbUseCase;
    @Mock BaseActivity activity;
    @Mock Resources resources;

    private SailDateInfo sailDateInfo;

    private SailDateItinerary sailDateItinerary;
    private List<SailDateEvent> sailDateEvents;

    private SailDateEvent event1;
    private SailDateEvent event2;

    private SailPort sailPort1;
    private SailPort sailPort2;
    @Before
    public void setUp() throws Exception {
        PowerMockito.mockStatic(TextUtils.class);
        PowerMockito.mockStatic(DateUtils.class);


        MockitoAnnotations.initMocks(this);
        presenter = new DayPickerPresenter(view,
                getSailingPreferenceUseCase,
                getSaildDateDbUseCase);

        sailDateInfo = new SailDateInfo();
        sailDateItinerary = new SailDateItinerary();
        sailDateItinerary.setDescription("7 NIGHT EASTERN CARIBBEAN CRUISE");

        sailDateEvents = new ArrayList<>();
        event1 = new SailDateEvent();
        event1.setDay("1");
        sailPort1 = new SailPort();
        sailPort1.setPortCode("FLL");
        sailPort1.setPortName("FORT LAUDERDALE, FLORIDA");
        sailPort1.setPortType("EMBARK");
        sailPort1.setArrivalDate("04/23/2017");
        sailPort1.setDepartureDate("04/23/2017");
        sailPort1.setArrivalTime("0");
        sailPort1.setDepartureTime("163000");
        event1.setPort(sailPort1);

        event2 = new SailDateEvent();
        event2.setDay("2");
        sailPort2 = new SailPort();
        sailPort2.setPortCode("NAS");
        sailPort2.setPortName("NASSAU, BAHAMAS");
        sailPort2.setPortType("DOCKED");
        sailPort2.setArrivalDate("04/24/2017");
        sailPort2.setDepartureDate("04/24/2017");
        sailPort2.setArrivalTime("70000");
        sailPort2.setDepartureTime("140000");
        event2.setPort(sailPort2);
        sailDateEvents.add(event1);
        sailDateEvents.add(event2);
        sailDateItinerary.setEvents(sailDateEvents);
        sailDateInfo.setDuration("7");
        sailDateInfo.setShipCode("AL");
        sailDateInfo.setItinerary(sailDateItinerary);
        when(view.getActivity()).thenReturn(activity);
        when(resources.getString(R.string.day_title)).thenReturn("Day ");
        when(resources.getString(R.string.hardcoded_day_picker_ship_name)).thenReturn("Allure of Seas");
        when(view.getActivity().getResources()).thenReturn(resources);
        when(DateUtils.getFormatedDates("0","1",resources)).thenReturn("Abi -Ene");
        when(getSaildDateDbUseCase.get()).thenReturn(sailDateInfo);
        when(getSailingPreferenceUseCase.getDay()).thenReturn("1");


    }

    @Test
    public void init() throws Exception {
        verify(view).getActivity();
        presenter.init();
        verify(getSaildDateDbUseCase).get();
        verify(getSailingPreferenceUseCase).getDay();
        verify(view.getActivity().getResources());
    }


}