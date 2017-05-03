package com.rcl.excalibur.mvp.presenter;

import android.graphics.PointF;
import android.graphics.RectF;
import android.util.Pair;

import com.rcl.excalibur.R;
import com.rcl.excalibur.domain.Product;
import com.rcl.excalibur.domain.ProductType;
import com.rcl.excalibur.domain.interactor.GetProductDbUseCase;
import com.rcl.excalibur.mvp.view.DeckMapView;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyFloat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


public class DeckMapPresenterTest {

    private static final boolean DECK_OPENED = true;

    @Mock DeckMapView view;
    @Mock GetProductDbUseCase getProductDbUseCase;
    @Mock RectF rectF;
    @Mock Pair<Integer, Integer> deckSelected;

    private final String productId = "1";
    private Product product;
    private DeckMapPresenter presenter;

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

        deckSelected = new Pair<>(2, 2);

        when(getProductDbUseCase.get(productId)).thenReturn(product);
    }

    @Test
    public void initTest() throws Exception {
        presenter.init(productId);
        verify(view).setProductCoordinate(196, 526);
        verify(view).initPopupLayout();
    }


    @Test
    public void onTouchDeckMapImageTest() throws Exception {
        when(view.isDeckMapImageReady()).thenReturn(true);
        when(view.getMarkerArea()).thenReturn(rectF);
        PointF touchedLocation = new PointF(1f, 2f);

        when(rectF.contains(touchedLocation.x, touchedLocation.y)).thenReturn(true);
        presenter.init(productId);
        presenter.onTouchDeckMapImage(touchedLocation);
        verify(view).isDeckMapImageReady();
        verify(view).moveToProductCoordinate(196, 526);
        verify(view).showProductOnPopupLayout(product);

    }

    @Test
    public void onDismissPopupWindowTest() throws Exception {
        presenter.onDismissPopupWindow();
        verify(view).dismissPopupWindow();
    }

    @Test
    public void onGlobalLayoutTest() throws Exception {
        when(view.isDeckMapImageReady()).thenReturn(true);
        presenter.init(productId);
        presenter.onGlobalLayout();
        verify(view).removeTreeObserver();
        verify(view).moveToProductCoordinate(anyFloat(), anyFloat());
        verify(view).showProductOnPopupLayout(product);
    }

    @Test
    public void onDeckSelectedTest() {
        presenter.onDeckSelected(deckSelected);
        verify(view).onDeckSelected(any(Pair.class));
    }

    @Test
    public void onDeckSelectorButtonClickedTest() {
        presenter.onDeckSelectorButtonClicked(DECK_OPENED);
        verify(view).openDeckSelector(DECK_OPENED);
    }
}
