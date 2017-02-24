package com.rcl.excalibur.mvp.presenter;

import com.rcl.excalibur.domain.Item;
import com.rcl.excalibur.activity.LoadFromDBActivity;
import com.rcl.excalibur.internal.di.component.ApplicationComponent;
import com.rcl.excalibur.mapper.ItemModelDataMapper;
import com.rcl.excalibur.model.ItemModel;
import com.rcl.excalibur.mvp.view.LoadFromDBView;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Collection;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


public class LoadFromDBPresenterTest {

    LoadFromDBPresenter presenter;
    @Mock LoadFromDBView view;
    @Mock LoadFromDBActivity activity;
    @Mock ApplicationComponent applicationComponent;
    @Mock ItemModelDataMapper itemModelDataMapper;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        when(view.getActivity()).thenReturn(activity);
        when(activity.getApplicationComponent()).thenReturn(applicationComponent);
        presenter = new LoadFromDBPresenter(view);
        presenter.itemModelDataMapper = itemModelDataMapper;
    }

    @Test
    public void initTest() throws Exception {
        verify(applicationComponent).inject(presenter);
        verify(view).setAdapterObserver(any(LoadFromDBPresenter.AdapterObserver.class));
        verify(view).setViewObserver(any(LoadFromDBPresenter.ViewObserver.class));
    }

    @Test
    public void showUsersCollectionInViewTest() throws Exception {
        Collection<Item> list = new ArrayList();
        Collection<ItemModel> listResult = new ArrayList();
        when(itemModelDataMapper.transform(list)).thenReturn(listResult);

        presenter.showUsersCollectionInView(list);
        verify(itemModelDataMapper).transform(list);
        verify(view).render(listResult);
    }

}