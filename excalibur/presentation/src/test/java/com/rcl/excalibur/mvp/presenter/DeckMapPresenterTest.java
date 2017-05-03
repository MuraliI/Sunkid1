package com.rcl.excalibur.mvp.presenter;

import com.rcl.excalibur.domain.Product;
import com.rcl.excalibur.domain.ProductLocation;
import com.rcl.excalibur.domain.ProductType;
import com.rcl.excalibur.domain.interactor.GetProductDbUseCase;
import com.rcl.excalibur.mvp.view.DeckMapView;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class DeckMapPresenterTest {
    final String productId = "1";
    DeckMapPresenter presenter;
    @Mock DeckMapView view;
    @Mock GetProductDbUseCase getProductDbUseCase;
    Product product;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        presenter = new DeckMapPresenter(view, getProductDbUseCase);

        product = new Product();
        product.setProductId(productId);
        ProductType productType = new ProductType();
        productType.setProductType("SPA");
        product.setProductType(productType);
        product.setProductTitle("Mock Tittle");
        ProductLocation productLocation = new ProductLocation();
        productLocation.setLatitude("100");
        product.setProductLocation(productLocation);

        when(getProductDbUseCase.get(productId)).thenReturn(product);
    }

    @Test
    public void initTest() throws Exception {
        presenter.init(productId);

        verify(view).moveToYPosition(100);
        verify(view).enableDisableDeckSelector(false);
        verify(view).hideArrowDeckSelector(false);
    }

}
