package com.rcl.excalibur.mvp.presenter;

public class DeckMapPresenterTest {
    /*final String productId = "1";
    DeckMapPresenter presenter;
    @Mock DeckMapView view;
    @Mock GetProductDbUseCase getProductDbUseCase;
    @Mock RectF rectF;
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

        when(getProductDbUseCase.get(productId)).thenReturn(product);
    }

    @Test
    public void initTest() throws Exception {
        presenter.init(productId);
        verify(view).initDeckImage(R.drawable.map_05_fwd);
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

    }*/


}
