package com.rcl.excalibur.mvp.presenter;


import com.rcl.excalibur.internal.di.component.AppComponentTest;
import com.rcl.excalibur.internal.di.component.DaggerAppComponentTest;
import com.rcl.excalibur.internal.di.component.detail.ProductDetailActivityComponentTest;
import com.rcl.excalibur.internal.di.component.detail.ProductDetailComponentTest;
import com.rcl.excalibur.internal.di.component.products.ProductsComponentTest;
import com.rcl.excalibur.internal.di.component.products.ProductsListComponentTest;
import com.rcl.excalibur.internal.di.module.AppModuleTest;
import com.rcl.excalibur.internal.di.module.detail.ProductDetailActivityModuleTest;
import com.rcl.excalibur.internal.di.module.detail.ProductDetailModuleTest;
import com.rcl.excalibur.internal.di.module.products.ProductsDatabaseModuleTest;
import com.rcl.excalibur.internal.di.module.products.ProductsListModuleTest;
import com.rcl.excalibur.internal.di.module.products.ProductsServicesModuleTest;
import com.rcl.excalibur.mvp.view.ProductDetailView;

import org.junit.Before;
import org.junit.Test;

import javax.inject.Inject;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;
import static org.mockito.Mockito.verify;

public class ProductDetailPresenterTest {

    @Inject ProductDetailPresenter presenter;
    AppComponentTest appComponentTest;
    ProductsComponentTest productsComponentTest;
    ProductsListComponentTest productsListComponentTest;
    ProductDetailComponentTest productDetailComponentTest;
    ProductDetailActivityComponentTest productDetailActivityComponentTest;

    private static final String PRODUCT_ID = "1L";

    private static final int VERTICAL_OFFSET = 50;
    private static final int TOTAL_SCROLL_RANGE = 100;
    private static final float BLUR_RADIUS = 12.0f;

    private static final int OUT_LOCATION_Y_SHOW = 230;
    private static final int OUT_LOCATION_Y_HIDE = 240;
    private static final int STATUS_BAR_HEIGHT = 195;
    private static final int TOOLBAR_HEIGHT = 85;

    @Before
    public void setUp() {
        appComponentTest = DaggerAppComponentTest.builder().appModule(new AppModuleTest()).build();
        productsComponentTest = appComponentTest.plus(new ProductsServicesModuleTest(), new ProductsDatabaseModuleTest());
        productsListComponentTest = productsComponentTest.plus(new ProductsListModuleTest());
        productDetailComponentTest = productsListComponentTest.plus(new ProductDetailModuleTest(PRODUCT_ID));
        productDetailActivityComponentTest = productDetailComponentTest.plus(new ProductDetailActivityModuleTest());
        productDetailActivityComponentTest.inject(this);
    }

    @Test
    public void getBlurRadius() {
        assertEquals(BLUR_RADIUS, presenter.getBlurRadius(VERTICAL_OFFSET, TOTAL_SCROLL_RANGE));
    }

    @Test
    public void checkLocationOnScreenToShowTitle() {
        ProductDetailView view = presenter.getView();
        int values[] = new int[]{OUT_LOCATION_Y_SHOW, STATUS_BAR_HEIGHT, TOOLBAR_HEIGHT};

        presenter.isTitleVisible = false;
        presenter.checkLocationOnScreen(values);

        verify(view).showCollapsingToolbarTitle();
        assertTrue(presenter.isTitleVisible);
    }

    @Test
    public void checkLocationOnScreenToHideTitle() {
        ProductDetailView view = presenter.getView();
        int values[] = new int[]{OUT_LOCATION_Y_HIDE, STATUS_BAR_HEIGHT, TOOLBAR_HEIGHT};

        presenter.isTitleVisible = true;
        presenter.checkLocationOnScreen(values);

        verify(view).hideCollapsingToolbarTitle();
        assertFalse(presenter.isTitleVisible);
    }
}
