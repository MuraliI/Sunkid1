package com.rcl.excalibur.deckmap.mvp.presenter;

import android.graphics.PointF;
import android.graphics.RectF;

import com.rcl.excalibur.deckmap.mvp.view.DiscoverDeckMapView;
import com.rcl.excalibur.domain.Product;
import com.rcl.excalibur.domain.interactor.GetProductDbUseCase;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

//TODO To be completed
public class DiscoverDeckMapPresenterTest {
    DiscoverDeckMapPresenter presenter;
    @Mock DiscoverDeckMapView view;
    @Mock GetProductDbUseCase getProductDbUseCase;
    @Mock Product product;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        when(getProductDbUseCase.get(1L)).thenReturn(product);
        when(product.getProductType().getProductType()).thenReturn("SPA");
        presenter = new DiscoverDeckMapPresenter(view, 1L);
    }

    @Test
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
        verify(view).moveToProductCoordinate(196, 526);
        verify(view).showProductOnPopupLayout(product);
    }
}