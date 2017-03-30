package com.rcl.excalibur.mvp.presenter;

import com.rcl.excalibur.BaseTest;
import com.rcl.excalibur.R;
import com.rcl.excalibur.domain.Product;
import com.rcl.excalibur.domain.ProductType;
import com.rcl.excalibur.domain.interactor.GetProductDbUseCase;
import com.rcl.excalibur.mvp.view.ProductDeckMapView;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;


public class DiscoverDeckMapPresenterTest extends BaseTest {
    ProductDeckMapPresenter presenter;
    @Mock ProductDeckMapView view;
    @Mock GetProductDbUseCase getProductDbUseCase;

    private Product product;

    @Before
    public void setup() throws Exception {
        super.setUp();
        MockitoAnnotations.initMocks(this);

        product = new Product();
        ProductType productType = new ProductType();
        productType.setProductType("SPA");
        product.setProductType(productType);
        when(getProductDbUseCase.get(1L)).thenReturn(product);
        presenter = new ProductDeckMapPresenter(view, getProductDbUseCase);
    }

    @Test
    public void initTest() {
        presenter.init(1L);
        verify(view).initPopupLayout();
        verify(view).initDeckImage(R.drawable.map_05_fwd);
    }

    @Test
    public void onTouchDeckMapImage() throws Exception {
        when(view.isDeckMapImageReady()).thenReturn(true);
        //verify(view).showProductOnPopupLayout(product);
        verifyNoMoreInteractions(view);
    }

    @Test
    public void onDismissPopupWindow() throws Exception {
        presenter.onDismissPopupWindow();
        verify(view).dismissPopupWindow();

    }

    @Test
    public void onGlobalLayout() throws Exception {
        when(view.isDeckMapImageReady()).thenReturn(true);
        presenter.onGlobalLayout();
        verify(view).removeTreeObserver();
    }
}