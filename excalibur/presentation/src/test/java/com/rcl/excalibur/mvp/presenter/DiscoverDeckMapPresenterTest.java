package com.rcl.excalibur.mvp.presenter;

import android.graphics.PointF;
import android.graphics.RectF;

import com.rcl.excalibur.BaseTest;
import com.rcl.excalibur.R;
import com.rcl.excalibur.domain.Product;
import com.rcl.excalibur.domain.ProductType;
import com.rcl.excalibur.domain.interactor.GetProductDbUseCase;
import com.rcl.excalibur.mvp.view.ProductDeckMapView;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

// TODO: Waiting for dagger injections
public class DiscoverDeckMapPresenterTest extends BaseTest {
    ProductDeckMapPresenter presenter;
    @Mock
    ProductDeckMapView view;
    @Mock
    GetProductDbUseCase getProductDbUseCase;

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

    @Ignore
    public void onTouchDeckMapImage() throws Exception {
        PointF productCoord = new PointF(0, 0);
        when(view.isDeckMapImageReady()).thenReturn(true);
        when(view.getMarkerArea()).thenReturn(new RectF());
        when(view.getMarkerArea().contains(productCoord.x, productCoord.y)).thenReturn(true);
        presenter.onTouchDeckMapImage(productCoord);
        verify(view).isDeckMapImageReady();
        verify(view).getMarkerArea();
        verify(view).moveToProductCoordinate(productCoord.x, productCoord.y);
        verify(view).showProductOnPopupLayout(product);
    }

    @Ignore
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