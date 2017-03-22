package com.rcl.excalibur.mvp.presenter;


import com.rcl.excalibur.R;
import com.rcl.excalibur.activity.BaseActivity;
import com.rcl.excalibur.domain.DiscoverItem;
import com.rcl.excalibur.fragments.DiscoverItemListFragment;
import com.rcl.excalibur.internal.di.component.ApplicationComponent;
import com.rcl.excalibur.mapper.DiscoverModelDataMapper;
import com.rcl.excalibur.model.DiscoverItemModel;
import com.rcl.excalibur.mvp.view.ProductsListView;

import org.junit.Assert;
import org.junit.Ignore;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

//TODO to completed
//@RunWith(RobolectricGradleTestRunner.class)
//@Config(constants = BuildConfig.class, sdk = 21, application = TestMyApplication.class)
public class DiscoverPresenterTest {

    private static final int TYPE = 0;
    DiscoverItemListPresenter presenter;
    @Mock ProductsListView view;
    @Mock BaseActivity activity;
    @Mock ApplicationComponent applicationComponent;
    @Mock DiscoverModelDataMapper discoverModelDataMapper;
//    @Mock GetDiscoverItemBasicList getDiscoverItemBasicList;

//    @Before
//    public void setUp() {
//        MockitoAnnotations.initMocks(this);
//        ((RCLApp) RuntimeEnvironment.application).getApplicationComponent().inject(this);
//
//        when(view.getActivity()).thenReturn(activity);
//        when(activity.getApplicationComponent()).thenReturn(applicationComponent);
//        doNothing().when(getDiscoverItemBasicList).execute(any(DiscoverItemListPresenter.DiscoverListObserver.class), any(GetDiscoverItemBasicList.Params.class));
//        presenter = new DiscoverItemListPresenter(TYPE, view);
//        presenter.discoverModelDataMapper = discoverModelDataMapper;
//        presenter.getDiscoverItemBasicList = getDiscoverItemBasicList;
//    }

    @Ignore
    public void init() throws Exception {
        //init()
        verify(view).setAdapterObserver(any(DiscoverItemListPresenter.AdapterObserver.class));
        verify(view).init();
//        verify(getDiscoverItemBasicList).execute(any(DiscoverItemListPresenter.DiscoverListObserver.class), any(GetDiscoverItemBasicList.Params.class));
        //initInjection()
        verify(applicationComponent).inject(presenter);
    }

    @Ignore
    public void getType() throws Exception {

        when(activity.getString(R.string.category_royal_activity)).thenReturn("Activities");
        when(activity.getString(R.string.category_entertainment)).thenReturn("Ent");
        when(activity.getString(R.string.category_shorex)).thenReturn("Shorex");
        when(activity.getString(R.string.category_spa)).thenReturn("Spa");
        when(activity.getString(R.string.category_dining)).thenReturn("Dining");
        when(activity.getString(R.string.category_shopping)).thenReturn("Shopping");

        Assert.assertEquals("Activities", presenter.getType(activity, DiscoverItemListFragment.ROYAL_ACTIVITY));
        Assert.assertEquals("Ent", presenter.getType(activity, DiscoverItemListFragment.ENTERTAINMENT));
        Assert.assertEquals("Shorex", presenter.getType(activity, DiscoverItemListFragment.SHOREX));
        Assert.assertEquals("Spa", presenter.getType(activity, DiscoverItemListFragment.SPA));
        Assert.assertEquals("Dining", presenter.getType(activity, DiscoverItemListFragment.DINING));
        Assert.assertEquals("Shopping", presenter.getType(activity, DiscoverItemListFragment.SHOPPING));
    }

    @Ignore
    public void showCollectionInView() throws Exception {
        List<DiscoverItem> list = new ArrayList();
        Collection<DiscoverItemModel> listResult = new ArrayList();
        when(discoverModelDataMapper.transform(list)).thenReturn(listResult);

        presenter.showCollectionInView(list);
        verify(discoverModelDataMapper).transform(list);
        verify(view).addAll(listResult);
    }
}