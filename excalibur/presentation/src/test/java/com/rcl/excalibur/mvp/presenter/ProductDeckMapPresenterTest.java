package com.rcl.excalibur.mvp.presenter;

import com.rcl.excalibur.R;
import com.rcl.excalibur.domain.Product;
import com.rcl.excalibur.domain.ProductType;
import com.rcl.excalibur.internal.di.component.AppComponentTest;
import com.rcl.excalibur.internal.di.component.DaggerAppComponentTest;
import com.rcl.excalibur.internal.di.component.products.ProductsComponentTest;
import com.rcl.excalibur.internal.di.component.products.ProductsDeckMapActivityComponentTest;
import com.rcl.excalibur.internal.di.module.AppModuleTest;
import com.rcl.excalibur.internal.di.module.products.ProductsDatabaseModuleTest;
import com.rcl.excalibur.internal.di.module.products.ProductsDeckMapActivityModuleTest;
import com.rcl.excalibur.internal.di.module.products.ProductsServicesModuleTest;
import com.rcl.excalibur.mvp.view.ProductDeckMapView;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.inject.Inject;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


public class ProductDeckMapPresenterTest {
    @Inject ProductDeckMapPresenter presenter;
    private AppComponentTest appComponentTest;
    private ProductsComponentTest productsComponentTest;
    private ProductsDeckMapActivityComponentTest activityComponentTest;

    @Before
    public void setUp() throws Exception {
        appComponentTest = DaggerAppComponentTest.builder()
                .appModule(new AppModuleTest())
                .build();
        productsComponentTest = appComponentTest.plus(new ProductsServicesModuleTest(),
                new ProductsDatabaseModuleTest());

        activityComponentTest = productsComponentTest.plus(new ProductsDeckMapActivityModuleTest());
        activityComponentTest.inject(this);
    }

    @After
    public void tearDown() throws Exception {
        appComponentTest = null;
        activityComponentTest = null;
        productsComponentTest = null;
    }

    @Test
    public void initTest() throws Exception {
        presenter.init("1L");
        ProductDeckMapView view = presenter.getView();

        Product product = new Product();
        ProductType productType = new ProductType();
        productType.setProductType("SPA");
        product.setProductType(productType);
        product.setProductTitle("Mock Tittle");

        when(presenter.getGetProductDbUseCase().get("1L")).thenReturn(product);

        verify(view).initDeckImage(R.drawable.map_05_fwd);
        verify(view).setProductCoordinate(0f, 0f);
        verify(view).initPopupLayout();
    }
}