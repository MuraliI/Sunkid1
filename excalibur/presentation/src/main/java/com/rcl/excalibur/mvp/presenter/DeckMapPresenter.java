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

    private static final int NUMBER_OF_DECKS = 13;
    private static final int DECK_1 = 1;
    private static final int DECK_2 = 2;
    private static final int DECK_3 = 3;
    private static final int DECK_4 = 4;
    private static final int DECK_5 = 5;
    private static final int DECK_6 = 6;
    private static final int DECK_7 = 7;
    private static final int DECK_8 = 8;
    private static final int DECK_9 = 9;
    private static final int DECK_10 = 10;
    private static final int DECK_11 = 11;
    private static final int DECK_12 = 12;
    private static final int DECK_13 = 13;

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
    private boolean isEnabled = true;

    public DeckMapPresenter(DeckMapView view, GetProductDbUseCase getProductDbUseCase) {
        this.view = view;
        this.getProductDbUseCase = getProductDbUseCase;
    }

    public void init(String productId) {
        Product product = getProductDbUseCase.get(productId);
        if (product != null) {
            isEnabled = false;
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
        deckImages = new ArrayList<>(NUMBER_OF_DECKS);
        deckImages.add(new Pair<>(DECK_1, R.drawable.deck1));
        deckImages.add(new Pair<>(DECK_2, R.drawable.deck2));
        deckImages.add(new Pair<>(DECK_3, R.drawable.deck3));
        deckImages.add(new Pair<>(DECK_4, R.drawable.deck4));
        deckImages.add(new Pair<>(DECK_5, R.drawable.deck5));
        deckImages.add(new Pair<>(DECK_6, R.drawable.deck6));
        deckImages.add(new Pair<>(DECK_7, R.drawable.deck7));
        deckImages.add(new Pair<>(DECK_8, R.drawable.deck8));
        deckImages.add(new Pair<>(DECK_9, R.drawable.deck9));
        deckImages.add(new Pair<>(DECK_10, R.drawable.deck10));
        deckImages.add(new Pair<>(DECK_11, R.drawable.deck11));
        deckImages.add(new Pair<>(DECK_12, R.drawable.deck12));
        deckImages.add(new Pair<>(DECK_13, R.drawable.deck13));
    }

    private void initView() {
        view.setAdapterObserver(new DeckSelectorObserver(this));
        view.init(deckImages, new DeckButtonConsumer(this));
        view.setInitialDeck(isEnabled ? deckImages.get(deckImages.size() - 1) : deckImages.get(deckNumber - 1));
        view.moveToYPosition(yCoord);
        view.enableDisableDeckSelector(isEnabled);
        view.hideArrowDeckSelector(isEnabled);
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
