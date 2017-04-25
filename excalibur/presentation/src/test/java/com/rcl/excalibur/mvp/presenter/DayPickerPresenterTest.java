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

    private final String description = "7 NIGHT EASTERN CARIBBEAN CRUISE";
    private final String shipCode = "AL";
    private final String duration = "7";


    private final String event1Day = "1";
    private final String event1PortCode = "FLL";
    private final String event1PortName = "FORT LAUDERDALE, FLORIDA";
    private final String event1PortType = "EMBARK";
    private final String event1PortArrivalDate = "04/23/2017";
    private final String event1PortDepartureDate = "04/23/2017";
    private final String event1PortArrivalTime = "0";
    private final String event1PortDepartureTime = "163000";

    private final String event2Day = "2";
    private final String event2PortCode = "NAS";
    private final String event2PortName = "NASSAU, BAHAMAS";
    private final String event2PortType = "DOCKED";
    private final String event2PortArrivalDate = "04/24/2017";
    private final String event2PortDepartureDate = "04/24/2017";
    private final String event2PortArrivalTime = "70000";
    private final String event2PortDepartureTime = "140000";



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
        sailDateItinerary.setDescription(description);

        sailDateEvents = new ArrayList<>();
        event1 = new SailDateEvent();
        event1.setDay(event1Day);
        sailPort1 = new SailPort();
        sailPort1.setPortCode(event1PortCode);
        sailPort1.setPortName(event1PortName);
        sailPort1.setPortType(event1PortType);
        sailPort1.setArrivalDate(event1PortArrivalDate);
        sailPort1.setDepartureDate(event1PortDepartureDate);
        sailPort1.setArrivalTime(event1PortArrivalTime);
        sailPort1.setDepartureTime(event1PortDepartureTime);
        event1.setPort(sailPort1);

        event2 = new SailDateEvent();
        event2.setDay(event2Day);
        sailPort2 = new SailPort();
        sailPort2.setPortCode(event2PortCode);
        sailPort2.setPortName(event2PortName);
        sailPort2.setPortType(event2PortType);
        sailPort2.setArrivalDate(event2PortArrivalDate);
        sailPort2.setDepartureDate(event2PortDepartureDate);
        sailPort2.setArrivalTime(event2PortArrivalTime);
        sailPort2.setDepartureTime(event2PortDepartureTime);
        event2.setPort(sailPort2);
        sailDateEvents.add(event1);
        sailDateEvents.add(event2);
        sailDateItinerary.setEvents(sailDateEvents);
        sailDateInfo.setDuration(duration);
        sailDateInfo.setShipCode(shipCode);
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