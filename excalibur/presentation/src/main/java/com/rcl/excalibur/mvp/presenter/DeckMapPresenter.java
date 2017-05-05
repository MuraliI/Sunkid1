package com.rcl.excalibur.mvp.presenter;


import android.content.Context;
import android.util.Pair;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.rcl.excalibur.R;
import com.rcl.excalibur.domain.LocationDeckInfo;
import com.rcl.excalibur.domain.Product;
import com.rcl.excalibur.domain.ProductLocation;
import com.rcl.excalibur.domain.interactor.GetProductDbUseCase;
import com.rcl.excalibur.domain.utils.ConstantsUtil;
import com.rcl.excalibur.mvp.presenter.rx.DefaultPresentObserver;
import com.rcl.excalibur.mvp.presenter.rx.DefaultPresenterConsumer;
import com.rcl.excalibur.mvp.view.DeckMapView;

import java.util.ArrayList;
import java.util.List;

import timber.log.Timber;

public class DeckMapPresenter {

    private static final int DEFAULT_DECK_NUMBER = 1;
    private static final String DEFAULT_LATITUDE = "0";
    private static final String DEFAULT_DECK = "12";
    private static final String ERROR = "conversion error";

    private Animation fadeOutBack;
    private Animation fadeOutFront;
    private Animation fadeInBack;
    private Animation fadeInFront;

    private List<Pair<Integer, Integer>> deckImages;
    private GetProductDbUseCase getProductDbUseCase;
    private DeckMapView view;

    private int yCoord = 0;
    private int deckNumber = 1;
    private boolean enableDisable = true;

    public DeckMapPresenter(DeckMapView view, GetProductDbUseCase getProductDbUseCase) {
        this.view = view;
        this.getProductDbUseCase = getProductDbUseCase;
    }

    public void init(String productId) {
        Product product = getProductDbUseCase.get(productId);
        if (product != null) {
            enableDisable = false;
            ProductLocation productLocation = product.getProductLocation();
            if (productLocation != null) {
                String productLatitude = productLocation.getLatitude();
                List<LocationDeckInfo> locationDeckInfo = productLocation.getLocationDeckInfo();
                if (locationDeckInfo != null && locationDeckInfo.size() > 0) {
                    LocationDeckInfo deckInfo = locationDeckInfo.get(0);
                    String productDeckNumber = deckInfo.getDeckNumber();

                    try {
                        yCoord = Integer.valueOf(productLatitude == null ? DEFAULT_LATITUDE : productLatitude);
                        deckNumber = Integer.valueOf(productDeckNumber == null ? DEFAULT_DECK : productDeckNumber);
                    } catch (Exception e) {
                        Timber.e(ERROR, e);
                    }
                }
            }
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
        deckImages.add(new Pair<>(13, R.drawable.deck13));
    }

    private void initView() {
        view.setAdapterObserver(new DeckSelectorObserver(this));
        view.init(deckImages, new DeckButtonConsumer(this));
        view.setInitialDeck(enableDisable ? deckImages.get(deckImages.size() - 1) : deckImages.get(deckNumber - 1));
        view.moveToYPosition(yCoord);
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

    private void initAnimations() {
        Context context = view.getContext();
        fadeOutBack = AnimationUtils.loadAnimation(context, R.anim.zoom_fade_out_back_venue);
        fadeOutFront = AnimationUtils.loadAnimation(context, R.anim.zoom_fade_out_front_venue);
        fadeInBack = AnimationUtils.loadAnimation(context, R.anim.zoom_fade_in_back_venue);
        fadeInFront = AnimationUtils.loadAnimation(context, R.anim.zoom_fade_in_front_venue);
    }

    public void setFadeInOutAnimation(Pair<Integer, Integer> deck) {
        initAnimations();
        String preNumber = view.getTextDeckSelectorButton();

        int preDeckNumber = preNumber == null ? DEFAULT_DECK_NUMBER
                : Integer.valueOf(preNumber.split(ConstantsUtil.WHITE_SPACE)[DEFAULT_DECK_NUMBER]);
        if (preDeckNumber == deck.first) {
            return;
        } else if (preDeckNumber < deck.first) {
            view.setDeckBackImageDrawable();
            view.setAnimation(deck.second, fadeOutBack, fadeOutFront, false);
        } else {
            view.setDeckFrontImageDrawable();
            view.setAnimation(deck.second, fadeInFront, fadeInBack, true);
        }
    }

    private class DeckSelectorObserver extends DefaultPresentObserver<Pair<Integer, Integer>, DeckMapPresenter> {

        DeckSelectorObserver(DeckMapPresenter presenter) {
            super(presenter);
        }

        @Override
        public void onNext(Pair<Integer, Integer> value) {
            getPresenter().setFadeInOutAnimation(value);
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
