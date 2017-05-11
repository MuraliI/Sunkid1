package com.rcl.excalibur.mvp.presenter;

import android.support.v4.view.ViewPager;

import com.rcl.excalibur.R;
import com.rcl.excalibur.activity.DiningMenuActivity;
import com.rcl.excalibur.adapters.DiningMenuPagerAdapter;
import com.rcl.excalibur.domain.interactor.GetMenuDbUseCase;
import com.rcl.excalibur.fragments.MenuListFragment;
import com.rcl.excalibur.mvp.view.DiningMenuView;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class DiningMenuPresenterTest {

    DiningMenuPresenter presenter;

    @Mock DiningMenuView view;
    @Mock GetMenuDbUseCase getMenuDbUseCase;
    String venueCode = "venueCode";
    @Mock DiningMenuActivity activity;
    @Mock ViewPager viewPager;
    @Mock MenuListFragment fragment;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        Mockito.when(view.getActivity()).thenReturn(activity);
        presenter = new DiningMenuPresenter(view, getMenuDbUseCase, venueCode);

    }

    @Test
    public void init() throws Exception {
        presenter.init();
        verify(view).init();
        verify(view).hideProgressBar();
    }

    @Test
    public void onHeaderBackOnClick() throws Exception {
        presenter.onHeaderBackOnClick();
        verify(view).getActivity();
        verify(activity).finish();
        verify(activity).overridePendingTransition(R.anim.slide_in_down, R.anim.slide_out_down);
    }

    @Ignore
    public void createFragmentMenuCaseOk() throws Exception {
//TODO improve with PowerMockd
        List<String> values = new ArrayList<>();
        values.add("menu1");
        values.add("menu2");
        when(getMenuDbUseCase.getAllMenuName(venueCode)).thenReturn(values);
        presenter.createFragmentMenu(viewPager);
        verify(getMenuDbUseCase).getAllMenuName(venueCode);
        verify(view).getFragmentManager();
        verify(viewPager).setAdapter(Mockito.any(DiningMenuPagerAdapter.class));

    }


    @Ignore
    public void createFragmentMenuCaseEmpty() throws Exception {
        //TODO improve with PowerMockd
        when(getMenuDbUseCase.getAllMenuName(venueCode)).thenReturn(null);
        presenter.createFragmentMenu(viewPager);
        verify(getMenuDbUseCase).getAllMenuName(venueCode);
        verify(view).getActivity();
    }


}