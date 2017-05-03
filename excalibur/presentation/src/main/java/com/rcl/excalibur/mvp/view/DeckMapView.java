package com.rcl.excalibur.mvp.view;


import android.graphics.drawable.Drawable;
import android.util.Pair;
import android.view.View;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.ScrollView;

import com.rcl.excalibur.R;
import com.rcl.excalibur.activity.DeckMapActivity;
import com.rcl.excalibur.custom.view.DeckSelectorButton;
import com.rcl.excalibur.custom.view.HorizontalPickerView;
import com.rcl.excalibur.mvp.view.base.ActivityView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.functions.Consumer;

public class DeckMapView extends ActivityView<DeckMapActivity, Boolean, Pair<Integer, Integer>> {

    @BindView(R.id.image_deck_map_back) ImageView deckMapBackImage;
    @BindView(R.id.image_deck_map_front) ImageView deckMapFrontImage;
    @BindView(R.id.horizontal_deck_selector) HorizontalPickerView<Integer> deckSelectorPicker;
    @BindView(R.id.button_deck_selector) DeckSelectorButton deckSelectorButton;
    @BindView(R.id.scrollView_deck_map) ScrollView deckMapScrollView;
    @BindView(R.id.image_dropdown) ImageView deckSelectorArrowImage;

    public DeckMapView(DeckMapActivity activity) {
        super(activity);
        ButterKnife.bind(this, activity);
    }

    public void init(List<Pair<Integer, Integer>> deckImagesMap, Consumer<Boolean> deckSelectorButtonObserver) {
        deckSelectorPicker.setItems(deckImagesMap);
        deckSelectorPicker.subscribeToItemClick(getAdapterObserver());
        deckSelectorButton.setButtonObserver(deckSelectorButtonObserver);
    }

    public void moveToYPosition(int y) {
        deckMapScrollView.post(() -> deckMapScrollView.smoothScrollBy(0, y == 0 ? getDeckImageMeasuredHeight() : y));
    }

    public int getDeckImageMeasuredHeight() {
        return deckMapBackImage.getMeasuredHeight() / 2;
    }

    public void enableDisableDeckSelector(Boolean enable) {
        deckSelectorButton.setEnabled(enable);
    }

    public void hideArrowDeckSelector(Boolean hide) {
        deckSelectorArrowImage.setVisibility(hide ? View.VISIBLE : View.INVISIBLE);
    }

    public void setInitialDeck(Pair<Integer, Integer> deck) {
        deckMapBackImage.setImageResource(deck.second);
        deckSelectorPicker.setSelectedItem(deck);
        if (getContext() != null) {
            deckSelectorButton.setText(getContext().getString(R.string.deck_number, String.valueOf(deck.first)));
        }
    }

    public void onDeckSelected(Pair<Integer, Integer> deck) {
        deckSelectorPicker.setSelectedItem(deck);
        if (getContext() != null) {
            deckSelectorButton.setText(getContext().getString(R.string.deck_number, String.valueOf(deck.first)));
        }
    }

    public ImageView getDeckMapBackImage() {
        return deckMapBackImage;
    }

    public ImageView getDeckMapFrontImage() {
        return deckMapFrontImage;
    }

    public DeckSelectorButton getDeckSelectorButton() {
        return deckSelectorButton;
    }

    public void openDeckSelector(Boolean opened) {
        deckSelectorPicker.setVisibility(opened ? View.VISIBLE : View.GONE);
    }

    public void setDeckImageDrawable(Drawable preImage, ImageView backView, ImageView frontView) {
        backView.setImageDrawable(preImage);
        frontView.setImageDrawable(null);
    }

    public void setAnimation(Pair<Integer, Integer> deck, Animation fadeOutBack, Animation fadeOutFront,
                             ImageView frontView, ImageView backView) {
        frontView.setImageResource(deck.second);
        backView.startAnimation(fadeOutBack);
        frontView.startAnimation(fadeOutFront);
        fadeOutBack.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                backView.setImageDrawable(null);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }
}
