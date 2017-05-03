package com.rcl.excalibur.mvp.presenter;


import android.graphics.PointF;
import android.util.Pair;

import com.rcl.excalibur.R;
import com.rcl.excalibur.domain.Product;
import com.rcl.excalibur.domain.interactor.GetProductDbUseCase;
import com.rcl.excalibur.mvp.presenter.rx.DefaultPresentObserver;
import com.rcl.excalibur.mvp.presenter.rx.DefaultPresenterConsumer;
import com.rcl.excalibur.mvp.view.DeckMapView;

import java.util.ArrayList;
import java.util.List;

import static com.rcl.excalibur.utils.CategoryUtils.ACTIVITIES;
import static com.rcl.excalibur.utils.CategoryUtils.DINING;
import static com.rcl.excalibur.utils.CategoryUtils.ENTERTAINMENT;
import static com.rcl.excalibur.utils.CategoryUtils.SHOREX;
import static com.rcl.excalibur.utils.CategoryUtils.SPA;

public class DeckMapPresenter {

    private static final int X_1 = 196;
    private static final int Y_1 = 526;
    private static final int X_2 = 116;
    private static final int Y_2 = 841;
    private static final int X_3 = 192;
    private static final int Y_3 = 421;
    private static final int X_4 = 120;
    private static final int Y_4 = 539;
    private static final int X_5 = 243;
    private static final int Y_5 = 558;

    private List<Pair<Integer, Integer>> deckImages;
    private GetProductDbUseCase getProductDbUseCase;
    private DeckMapView view;

    private Product product;
    private float xCoord;
    private float yCoord;

    public DeckMapPresenter(DeckMapView view, GetProductDbUseCase getProductDbUseCase) {
        this.view = view;
        this.getProductDbUseCase = getProductDbUseCase;
    }

    public void init(String productId) {
        product = getProductDbUseCase.get(productId);
        if (product != null) {
            setCoordinate(product.getProductType().getProductType());
        }
        createDeckImagesMap();
        initView();
    }

    private void createDeckImagesMap() {
        deckImages = new ArrayList<>(12);
        deckImages.add(new Pair<>(1, R.drawable.deck1));
        deckImages.add(new Pair<>(2, R.drawable.deck2));
        deckImages.add(new Pair<>(3, R.drawable.deck3));
        deckImages.add(new Pair<>(4, R.drawable.deck4));
        deckImages.add(new Pair<>(5, R.drawable.deck5));
        deckImages.add(new Pair<>(6, R.drawable.deck6));
        deckImages.add(new Pair<>(7, R.drawable.deck7));
        deckImages.add(new Pair<>(8, R.drawable.deck8));
        deckImages.add(new Pair<>(9, R.drawable.deck9));
        deckImages.add(new Pair<>(10, R.drawable.deck10));
        deckImages.add(new Pair<>(11, R.drawable.deck11));
        deckImages.add(new Pair<>(12, R.drawable.deck12));
    }

    private void initView() {
        view.setAdapterObserver(new DeckSelectorObserver(this));
        view.init(deckImages, new DeckButtonConsumer(this));
        view.setInitialDeck(deckImages.get(deckImages.size() - 1));
        view.setProductCoordinate(xCoord, yCoord);
        view.initPopupLayout();
    }

    // TODO: In a future we'll get this coordinate from the service
    private void setCoordinate(String productType) {
        switch (productType) {
            case SPA:
                xCoord = X_1;
                yCoord = Y_1;
                break;
            case ENTERTAINMENT:
                xCoord = X_2;
                yCoord = Y_2;
                break;
            case ACTIVITIES:
                xCoord = X_3;
                yCoord = Y_3;
                break;
            case DINING:
                xCoord = X_4;
                yCoord = Y_4;
                break;
            case SHOREX:
                xCoord = X_5;
                yCoord = Y_5;
                break;
            default:
                xCoord = X_1;
                yCoord = Y_1;
                break;
        }
    }

    public void onTouchDeckMapImage(PointF touchedLocation) {
        if (view.isDeckMapImageReady() && view.getMarkerArea().contains(touchedLocation.x, touchedLocation.y)) {
            moveToCoordinateAndShowPopup();
        }
    }

    public void onCloseClicked() {
        if (view.getActivity() != null) {
            view.getActivity().finish();
        }
    }

    public void onDismissPopupWindow() {
        view.dismissPopupWindow();
    }

    public void onGlobalLayout() {
        if (view.isDeckMapImageReady()) {
            view.removeTreeObserver();
            moveToCoordinateAndShowPopup();
        }
    }

    private void moveToCoordinateAndShowPopup() {
        if (product == null) {
            return;
        }
        view.moveToProductCoordinate(xCoord, yCoord);
        view.showProductOnPopupLayout(product);
    }

    private void onDeckSelected(Pair<Integer, Integer> deck) {
        view.onDeckSelected(deck);
    }

    private void onDeckSelectorButtonClicked(Boolean opened) {
        view.openDeckSelector(opened);
    }

    private class DeckSelectorObserver extends DefaultPresentObserver<Pair<Integer, Integer>, DeckMapPresenter> {

        DeckSelectorObserver(DeckMapPresenter presenter) {
            super(presenter);
        }

        @Override
        public void onNext(Pair<Integer, Integer> value) {
            getPresenter().onDeckSelected(value);
        }
    }

    private class DeckButtonConsumer extends DefaultPresenterConsumer<Boolean, DeckMapPresenter> {

        public DeckButtonConsumer(DeckMapPresenter presenter) {
            super(presenter);
        }

        @Override
        public void accept(Boolean value) throws Exception {
            getPresenter().onDeckSelectorButtonClicked(value);
        }
    }
}
