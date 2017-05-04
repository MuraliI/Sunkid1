package com.rcl.excalibur.mvp.view;


import android.content.Context;
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

    public static final int SCROLL_CENTER = 0;
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
        deckMapScrollView.post(() -> deckMapScrollView.scrollTo(0, y == 0 ? getDeckImageMeasuredHeight() : y));
    }

    public void centerScroll(final ImageView view) {
        view.post(() -> deckMapScrollView.scrollTo(0, view.getMeasuredHeight() / 2));
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
        Context context = getContext();
        if (context == null) {
            return;
        }
        deckSelectorButton.setText(context.getString(R.string.deck_number, String.valueOf(deck.first)));
    }

    public void onDeckSelected(Pair<Integer, Integer> deck) {
        deckSelectorPicker.setSelectedItem(deck);
        Context context = getContext();
        if (context == null) {
            return;
        }
        deckSelectorButton.setText(context.getString(R.string.deck_number, String.valueOf(deck.first)));
    }

    public String getTextDeckSelectorButton() {
        return deckSelectorButton.getText();
    }

    public void openDeckSelector(Boolean opened) {
        deckSelectorPicker.setVisibility(opened ? View.VISIBLE : View.GONE);
    }

    public void setDeckBackImageDrawable() {
        Drawable deckMapFrontDrawable = deckMapFrontImage.getDrawable();
        if (deckMapFrontDrawable != null) {
            deckMapBackImage.setImageDrawable(deckMapFrontDrawable);
            deckMapFrontImage.setImageDrawable(null);
        }
    }

    public void setDeckFrontImageDrawable() {
        if (deckMapFrontImage.getDrawable() == null) {
            deckMapFrontImage.setImageDrawable(deckMapBackImage.getDrawable());
            deckMapBackImage.setImageDrawable(null);
        }
    }

    public void setAnimation(int resource, Animation fadeOutBack, Animation fadeOutFront, boolean front) {
        ImageView iFront = front ? deckMapBackImage : deckMapFrontImage;
        ImageView iBack = front ? deckMapFrontImage : deckMapBackImage;

        iFront.setImageResource(resource);
        centerScroll(iFront);
        iBack.startAnimation(fadeOutBack);
        iFront.startAnimation(fadeOutFront);
        fadeOutBack.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                iBack.setImageDrawable(null);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }
}
