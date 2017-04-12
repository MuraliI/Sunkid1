package com.rcl.excalibur.mvp.presenter;

import android.content.Intent;

import com.rcl.excalibur.R;
import com.rcl.excalibur.activity.BaseActivity;
import com.rcl.excalibur.domain.interactor.GetProductsUseCase;
import com.rcl.excalibur.mvp.view.DiscoverTabView;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class DiscoverTabPresenterTest {

    DiscoverTabPresenter presenter;
    @Mock DiscoverTabView view;
    @Mock GetProductsUseCase getProductsUseCase;
    @Mock BaseActivity activity;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        presenter = new DiscoverTabPresenter(view, getProductsUseCase);

        when(view.getActivity()).thenReturn(activity);
    }


    @Test
    public void testOpenListScreen() throws Exception {
        int fragmentToShow = 0;
        presenter.openListScreen(fragmentToShow);

        verify(view).openListScreen(fragmentToShow);
    }

    @Test
    public void testInit() throws Exception {
        presenter.init();
        verify(getProductsUseCase).execute(Mockito.any());
    }

    @Test
    public void testBoatOnClick() throws Exception {
        //TODO improve with powermock
        presenter.countBoatOnClick = 4;
        presenter.boatOnClick();
        verify(view).getActivity();
        verify(activity).startActivity(any(Intent.class));
        verify(activity).overridePendingTransition(R.anim.slide_in_up, R.anim.slide_out_up);
    }


}