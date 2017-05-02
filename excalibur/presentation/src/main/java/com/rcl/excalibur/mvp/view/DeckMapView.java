package com.rcl.excalibur.mvp.view;


import android.util.Pair;
import android.view.View;
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

    @BindView(R.id.image_deck_map) ImageView deckMapImage;
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
        deckMapScrollView.post(new Runnable() {
            @Override
            public void run() {
                deckMapScrollView.smoothScrollTo(0, y);
            }
        });
    }

    public void enableDisableDeckSelector(Boolean enable) {
        deckSelectorButton.setEnabled(enable);
    }

    public void hideArrowDeckSelector(Boolean hide) {
        deckSelectorArrowImage.setVisibility(hide ? View.VISIBLE : View.INVISIBLE);
    }

    public void onDeckSelected(Pair<Integer, Integer> deck) {
        deckMapImage.setImageResource(deck.second);
        deckSelectorPicker.setSelectedItem(deck);
        if (getContext() != null) {
            deckSelectorButton.setText(getContext().getString(R.string.deck_number, String.valueOf(deck.first)));
        }
    }

    public void openDeckSelector(Boolean opened) {
        deckSelectorPicker.setVisibility(opened ? View.VISIBLE : View.GONE);
    }
}
