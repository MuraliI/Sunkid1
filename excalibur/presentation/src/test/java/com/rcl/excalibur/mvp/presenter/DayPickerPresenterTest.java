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
import org.junit.Ignore;
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

    private final String DESCRIPTION = "7 NIGHT EASTERN CARIBBEAN CRUISE";
    private final String SHIPCODE = "AL";
    private final String DURATION = "7";



    private final String EVENT1_DAY = "1";
    private final String EVENT1_PORT_CODE = "FLL";
    private final String EVENT1_PORT_NAME = "FORT LAUDERDALE, FLORIDA";
    private final String EVENT1_PORT_TYPE = "EMBARK";
    private final String EVENT1_PORT_ARRIVAL_DATE = "04/23/2017";
    private final String EVENT1_PORT_DEPARTURE_DATE = "04/23/2017";
    private final String EVENT1_PORT_ARRIVAL_TIME = "0";
    private final String EVENT1_PORT_DEPARTURE_TIME = "163000";

    private final String EVENT2_DAY = "2";
    private final String EVENT2_PORT_CODE = "NAS";
    private final String EVENT2_PORT_NAME = "NASSAU, BAHAMAS";
    private final String EVENT2_PORT_TYPE = "DOCKED";
    private final String EVENT2_PORT_ARRIVAL_DATE = "04/24/2017";
    private final String EVENT2_PORT_DEPARTURE_DATE = "04/24/2017";
    private final String EVENT2_PORT_ARRIVAL_TIME = "70000";
    private final String EVENT2_PORT_DEPARTURE_TIME = "140000";



    @Before
    public void setUp() throws Exception {
        PowerMockito.mockStatic(TextUtils.class);
     //   PowerMockito.mockStatic(DateUtils.class);


        MockitoAnnotations.initMocks(this);
        presenter = new DayPickerPresenter(view,
                getSailingPreferenceUseCase,
                getSaildDateDbUseCase);

        sailDateInfo = new SailDateInfo();
        sailDateItinerary = new SailDateItinerary();
        sailDateItinerary.setDescription(DESCRIPTION);

        sailDateEvents = new ArrayList<>();
        event1 = new SailDateEvent();
        event1.setDay(EVENT1_DAY);
        sailPort1 = new SailPort();
        sailPort1.setPortCode(EVENT1_PORT_CODE);
        sailPort1.setPortName(EVENT1_PORT_NAME);
        sailPort1.setPortType(EVENT1_PORT_TYPE);
        sailPort1.setArrivalDate(EVENT1_PORT_ARRIVAL_DATE);
        sailPort1.setDepartureDate(EVENT1_PORT_DEPARTURE_DATE);
        sailPort1.setArrivalTime(EVENT1_PORT_ARRIVAL_TIME);
        sailPort1.setDepartureTime(EVENT1_PORT_DEPARTURE_TIME);
        event1.setPort(sailPort1);

        event2 = new SailDateEvent();
        event2.setDay(EVENT2_DAY);
        sailPort2 = new SailPort();
        sailPort2.setPortCode(EVENT2_PORT_CODE);
        sailPort2.setPortName(EVENT2_PORT_NAME);
        sailPort2.setPortType(EVENT2_PORT_TYPE);
        sailPort2.setArrivalDate(EVENT2_PORT_ARRIVAL_DATE);
        sailPort2.setDepartureDate(EVENT2_PORT_DEPARTURE_DATE);
        sailPort2.setArrivalTime(EVENT2_PORT_ARRIVAL_TIME);
        sailPort2.setDepartureTime(EVENT2_PORT_DEPARTURE_TIME);
        event2.setPort(sailPort2);
        sailDateEvents.add(event1);
        sailDateEvents.add(event2);
        sailDateItinerary.setEvents(sailDateEvents);
        sailDateInfo.setDuration(DURATION);
        sailDateInfo.setShipCode(SHIPCODE);
        sailDateInfo.setItinerary(sailDateItinerary);
        when(view.getActivity()).thenReturn(activity);
        when(resources.getString(R.string.day_title)).thenReturn("Day ");
        when(resources.getString(R.string.hardcoded_day_picker_ship_name)).thenReturn("Allure of Seas");
        when(view.getActivity().getResources()).thenReturn(resources);
        when(DateUtils.getFormatedDates("0","1",resources)).thenReturn("Abi -Ene");
        when(getSaildDateDbUseCase.get()).thenReturn(sailDateInfo);
        when(getSailingPreferenceUseCase.getDay()).thenReturn("1");


    }

    @Ignore
    @Test
    public void init() throws Exception {
        verify(view).getActivity();
        presenter.init();
        verify(getSaildDateDbUseCase).get();
        verify(getSailingPreferenceUseCase).getDay();
        verify(view.getActivity().getResources());
    }


}