package com.rcl.excalibur.mvp.presenter;


import com.rcl.excalibur.activity.ProductDetailActivity;
import com.rcl.excalibur.domain.Product;
import com.rcl.excalibur.domain.ProductDuration;
import com.rcl.excalibur.domain.ProductType;
import com.rcl.excalibur.domain.interactor.GetOfferingsDbUseCase;
import com.rcl.excalibur.domain.interactor.GetProductDbUseCase;
import com.rcl.excalibur.domain.interactor.GetSaildDateDbUseCase;
import com.rcl.excalibur.mvp.view.ProductDetailView;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;
import static org.mockito.Matchers.anyFloat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ProductDetailPresenterTest {

    private static final String PRODUCT_ID = "1";

    private static final int VERTICAL_OFFSET = 50;
    private static final int TOTAL_SCROLL_RANGE = 100;
    private static final float BLUR_RADIUS = 12.0f;
    private static final int OUT_LOCATION_Y_SHOW = 230;
    private static final int OUT_LOCATION_Y_HIDE = 240;
    private static final int STATUS_BAR_HEIGHT = 195;
    private static final int TOOLBAR_HEIGHT = 85;
    ProductDetailPresenter presenter;
    @Mock ProductDetailView view;
    @Mock GetProductDbUseCase getProductDbUseCase;
    @Mock GetOfferingsDbUseCase getOfferingsDbUseCase;
    @Mock GetSaildDateDbUseCase getSaildDateDbUseCase;
    @Mock ProductDetailActivity activity;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        presenter = new ProductDetailPresenter(view, getProductDbUseCase, getOfferingsDbUseCase, getSaildDateDbUseCase);
        when(view.getActivity()).thenReturn(activity);
    }


    @Test
    public void getBlurRadius() {
        assertEquals(BLUR_RADIUS, presenter.getBlurRadius(VERTICAL_OFFSET, TOTAL_SCROLL_RANGE));
    }

//    @Test
//    protected void checkLocationOnScreenTest() throws Exception {
//        int[] values = {1, 2, 3};
//        presenter.checkLocationOnScreen(values);
//        presenter.isTitleVisible = true;
//        verify(view).hideCollapsingToolbarTitle();
//        presenter.checkLocationOnScreen(values);
//
//        presenter.isTitleVisible = false;
//        verify(view).showCollapsingToolbarTitle();
//
//    }

    @Test
    public void checkLocationOnScreenToShowTitle() {
        int values[] = new int[]{OUT_LOCATION_Y_SHOW, STATUS_BAR_HEIGHT, TOOLBAR_HEIGHT};

        presenter.isTitleVisible = false;
        presenter.checkLocationOnScreen(values);

        verify(view).showCollapsingToolbarTitle();
        assertTrue(presenter.isTitleVisible);
    }

    @Test
    public void checkLocationOnScreenToHideTitle() {
        int values[] = new int[]{OUT_LOCATION_Y_HIDE, STATUS_BAR_HEIGHT, TOOLBAR_HEIGHT};

        presenter.isTitleVisible = true;
        presenter.checkLocationOnScreen(values);

        verify(view).hideCollapsingToolbarTitle();
        assertFalse(presenter.isTitleVisible);
    }


    @Ignore //TODO improve with PowerMock, by TextUtils.isEmpty
    public void initTest() throws Exception {
        Product product = new Product();
        product.setProductId(PRODUCT_ID);
        ProductType productType = new ProductType();
        productType.setProductType("SPA");
        product.setProductType(productType);
        product.setProductTitle("Mock Tittle");
        product.setProductDuration(new ProductDuration());
        when(getProductDbUseCase.get(PRODUCT_ID)).thenReturn(product);

        presenter.init(PRODUCT_ID);
        verify(view).getActivity();
        verify(getProductDbUseCase).get(PRODUCT_ID);

        verify(view).initStatusBarHeight();
        verify(view).setupToolbar();
        verify(view).initAnimation();
        verify(view).setHeroImage(Mockito.anyString());

        verify(view).setViewObserver(Mockito.any(ProductDetailPresenter.LocationOnScreenObserver.class));
        verify(view).setAdapterObserver(Mockito.any(ProductDetailPresenter.FindOnDeckClickObserver.class));
        verify(view).render(Mockito.any(List.class));

    }

    @Test
    public void onBackClickedTest() throws Exception {
        presenter.onBackClicked();
        verify(view).getActivity();
        verify(activity).finish();
    }

    @Test
    public void onOffsetChangedTest() throws Exception {
        presenter.onOffsetChanged(1, 2);
        verify(view).setBlurRadiusOnImage(anyFloat());
    }

}
