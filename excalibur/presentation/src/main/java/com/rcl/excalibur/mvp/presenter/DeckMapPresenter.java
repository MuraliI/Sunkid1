package com.rcl.excalibur.mvp.presenter;


import android.util.Pair;

import com.rcl.excalibur.R;
import com.rcl.excalibur.domain.Product;
import com.rcl.excalibur.domain.interactor.GetProductDbUseCase;
import com.rcl.excalibur.mvp.presenter.rx.DefaultPresentObserver;
import com.rcl.excalibur.mvp.presenter.rx.DefaultPresenterConsumer;
import com.rcl.excalibur.mvp.view.DeckMapView;

import java.util.ArrayList;
import java.util.List;

public class DeckMapPresenter {

    private static final String DEFAULT_LATITUDE = "0";

    private List<Pair<Integer, Integer>> deckImages;
    private GetProductDbUseCase getProductDbUseCase;
    private DeckMapView view;

    private Product product;
    private int yCoord;
    private boolean enableDisable = true;

    public DeckMapPresenter(DeckMapView view, GetProductDbUseCase getProductDbUseCase) {
        this.view = view;
        this.getProductDbUseCase = getProductDbUseCase;
    }

    public void init(String productId) {
        product = getProductDbUseCase.get(productId);
        if (product != null) {
            enableDisable = false;
            String productLatitude = product.getProductLocation().getLatitude();
            yCoord = Integer.valueOf(productLatitude == null ? DEFAULT_LATITUDE : productLatitude);
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
        view.moveToYPosition(yCoord);
        view.setInitialDeck(deckImages.get(deckImages.size() - 1));
        view.enableDisableDeckSelector(enableDisable);
        view.hideArrowDeckSelector(enableDisable);
    }

    public void onCloseClicked() {
        if (view.getActivity() != null) {
            view.getActivity().finish();
        }
    }

    public void onDeckSelected(Pair<Integer, Integer> deck) {
        view.onDeckSelected(deck);
    }

    public void onDeckSelectorButtonClicked(Boolean open) {
        view.openDeckSelector(open);
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

        DeckButtonConsumer(DeckMapPresenter presenter) {
            super(presenter);
        }

        @Override
        public void accept(Boolean value) throws Exception {
            getPresenter().onDeckSelectorButtonClicked(value);
        }
    }
}
