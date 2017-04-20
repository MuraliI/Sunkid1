package com.rcl.excalibur.mvp.presenter;

import android.content.Context;

import com.rcl.excalibur.R;
import com.rcl.excalibur.domain.interactor.GetShipTimeDbUseCase;
import com.rcl.excalibur.domain.interactor.GetShipTimeUseCase;
import com.rcl.excalibur.mvp.view.ShipTimeView;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static com.rcl.excalibur.mvp.presenter.ShipTimePresenter.TYPE_BLUE;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.verify;

public class ShipTimePresenterTest {
    ShipTimePresenter presenter;
    @Mock ShipTimeView view;


    @Mock GetShipTimeUseCase getShipTimeUseCase;
    @Mock GetShipTimeDbUseCase getShipTimeDbUseCase;
    @Mock Context context;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        presenter = new ShipTimePresenter(view, getShipTimeUseCase, getShipTimeDbUseCase);
    }

    @Test
    public void init() throws Exception {
        String type = "1";
        presenter.init(type);
        verify(view).applyType(R.color.white, R.color.black);
        verify(getShipTimeUseCase).execute(any());

    }

    @Ignore
    public void register() throws Exception {
//TODO implement with Power static by SchedulerManager
        presenter.register();
        verify(getShipTimeDbUseCase).get();
        verify(view).display(anyString());

    }

    @Ignore
    public void unregister() throws Exception {
//TODO implement with Power static by SchedulerManager
    }

    @Test
    public void applyType() throws Exception {
        presenter.applyType(TYPE_BLUE);
        verify(view).applyType(R.color.bg_color, R.color.white);
    }

}