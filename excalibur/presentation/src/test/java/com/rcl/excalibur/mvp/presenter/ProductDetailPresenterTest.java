package com.rcl.excalibur.mvp.presenter;


import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

public class ProductDetailPresenterTest {

    private static final Long PRODUCT_ID = 1L;
    //    AppComponentTest appComponentTest;
//    ProductsComponentTest productsComponentTest;
//    ProductsListComponentTest productsListComponentTest;
//    ProductDetailComponentTest productDetailComponentTest;
//    ProductDetailActivityComponentTest productDetailActivityComponentTest;
    private static final int VERTICAL_OFFSET = 50;
    private static final int TOTAL_SCROLL_RANGE = 100;
    private static final float BLUR_RADIUS = 12.0f;
    private static final int OUT_LOCATION_Y_SHOW = 230;
    private static final int OUT_LOCATION_Y_HIDE = 240;
    private static final int STATUS_BAR_HEIGHT = 195;
    private static final int TOOLBAR_HEIGHT = 85;
    ProductDetailPresenter presenter;

    @Before
    public void setUp() {
//        appComponentTest = DaggerAppComponentTest.builder().appModule(new AppModuleTest()).build();
//        productsComponentTest = appComponentTest.plus(new ProductsServicesModuleTest(), new ProductsDatabaseModuleTest());
//        productsListComponentTest = productsComponentTest.plus(new ProductsListModuleTest());
//        productDetailComponentTest = productsListComponentTest.plus(new ProductDetailModuleTest(PRODUCT_ID));
//        productDetailActivityComponentTest = productDetailComponentTest.plus(new ProductDetailActivityModuleTest());
//        productDetailActivityComponentTest.inject(this);
    }

    @Test
    public void getBlurRadius() {
        assertEquals(BLUR_RADIUS, presenter.getBlurRadius(VERTICAL_OFFSET, TOTAL_SCROLL_RANGE));
    }

    @Test
    public void checkLocationOnScreenToShowTitle() {
//        ProductDetailView view = presenter.getView();
//        int values[] = new int[]{OUT_LOCATION_Y_SHOW, STATUS_BAR_HEIGHT, TOOLBAR_HEIGHT};
//
//        presenter.isTitleVisible = false;
//        presenter.checkLocationOnScreen(values);
//
//        verify(view).showCollapsingToolbarTitle();
//        assertTrue(presenter.isTitleVisible);
    }

    @Test
    public void checkLocationOnScreenToHideTitle() {
//        ProductDetailView view = presenter.getView();
//        int values[] = new int[]{OUT_LOCATION_Y_HIDE, STATUS_BAR_HEIGHT, TOOLBAR_HEIGHT};
//
//        presenter.isTitleVisible = true;
//        presenter.checkLocationOnScreen(values);
//
//        verify(view).hideCollapsingToolbarTitle();
//        assertFalse(presenter.isTitleVisible);
    }
}
