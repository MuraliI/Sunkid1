package com.rcl.excalibur.mvp.view;


import android.graphics.Color;
import android.graphics.PointF;
import android.graphics.RectF;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.util.Pair;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;

import com.davemorrissey.labs.subscaleview.SubsamplingScaleImageView;
import com.rcl.excalibur.R;
import com.rcl.excalibur.activity.DeckMapActivity;
import com.rcl.excalibur.custom.view.DeckMapImageView;
import com.rcl.excalibur.custom.view.DeckMapPopupLayout;
import com.rcl.excalibur.custom.view.DeckSelectorButton;
import com.rcl.excalibur.custom.view.HorizontalPickerView;
import com.rcl.excalibur.domain.Product;
import com.rcl.excalibur.mvp.view.base.ActivityView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.functions.Consumer;

public class DeckMapView extends ActivityView<DeckMapActivity, Boolean, Pair<Integer, Integer>> {
    private static final int MINIMUM_DPI = 80;
    private static final float HALF_FACTOR = 2.0f;

    @BindView(R.id.image_deck_map) DeckMapImageView deckMapImage;
    @BindView(R.id.horizontal_deck_selector) HorizontalPickerView<Integer> deckSelectorPicker;
    @BindView(R.id.button_deck_selector) DeckSelectorButton deckSelectorButton;

    private DeckMapPopupLayout popupView;
    private PopupWindow popupWindow;

    public DeckMapView(DeckMapActivity activity) {
        super(activity);
        ButterKnife.bind(this, activity);
    }

    public void init(List<Pair<Integer, Integer>> deckImagesMap, Consumer<Boolean> deckSelectorButtonObserver) {
        deckSelectorPicker.setItems(deckImagesMap);
        deckSelectorPicker.subscribeToItemClick(getAdapterObserver());
        deckSelectorButton.setButtonObserver(deckSelectorButtonObserver);
    }

    public void initDeckImage(int resource) {
        deckMapImage.setImage(resource);
        deckMapImage.setPanLimit(SubsamplingScaleImageView.PAN_LIMIT_OUTSIDE);
        deckMapImage.setMinimumDpi(MINIMUM_DPI);

        DeckMapActivity activity = getActivity();
        if (activity != null) {
            deckMapImage.getViewTreeObserver().addOnGlobalLayoutListener(activity);
            deckMapImage.setOnTouchListener(activity);
        }
    }

    public void initPopupLayout() {
        popupView = new DeckMapPopupLayout(getContext());
        popupWindow = new PopupWindow(
                popupView,
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );
        popupWindow.setOutsideTouchable(true);
        popupWindow.setTouchable(true);
        popupWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        DeckMapActivity activity = getActivity();
        if (activity != null) {
            popupWindow.setTouchInterceptor(activity);
        }
    }

    public void setProductCoordinate(float xCoord, float yCoord) {
        if (xCoord == 0 && yCoord == 0) {
            return;
        }
        deckMapImage.setProductCoord(new PointF(xCoord, yCoord));
    }

    public void moveToProductCoordinate(float xCoord, float yCoord) {
        deckMapImage.animatePointToCenter(new PointF(xCoord, yCoord));
    }

    public boolean isDeckMapImageReady() {
        return deckMapImage.isReady();
    }

    public void removeTreeObserver() {
        DeckMapActivity activity = getActivity();
        if (activity != null) {
            deckMapImage.getViewTreeObserver()
                    .removeOnGlobalLayoutListener(activity);
        }
    }

    public void showProductOnPopupLayout(@NonNull Product product) {
        DeckMapActivity activity = getActivity();
        if (activity == null) {
            return;
        }

        float elevationError = activity.getResources().getDimension(R.dimen.item_deck_elevation_error);
        float popUpHeight = activity.getResources().getDimensionPixelOffset(R.dimen.item_deck_height);
        int elevation = (int) (popUpHeight / HALF_FACTOR + deckMapImage.getMarketHeight() - elevationError);

        popupView.setProduct(product);
        popupWindow.showAtLocation(deckMapImage, Gravity.CENTER, 0, -elevation);
    }

    public void dismissPopupWindow() {
        if (popupWindow == null) {
            return;
        }
        popupWindow.dismiss();
    }

    public RectF getMarkerArea() {
        return deckMapImage.getMarkerArea();
    }

    public void onDeckSelected(Pair<Integer, Integer> deck) {
        deckMapImage.setImage(deck.first);
        deckSelectorPicker.setSelectedItem(deck);
        if (getContext() != null) {
            deckSelectorButton.setText(getContext().getString(R.string.deck_number, String.valueOf(deck.first)));
        }
    }

    public void setInitialDeck(Pair<Integer, Integer> deck) {
        initDeckImage(deck.second);
        deckSelectorPicker.setSelectedItem(deck);
        //FIXME refactor this after modifying the deck image
        if (getContext() != null) {
            deckSelectorButton.setText(getContext().getString(R.string.deck_number, String.valueOf(deck.first)));
        }
    }

    public void openDeckSelector(Boolean opened) {
        deckSelectorPicker.setVisibility(opened ? View.VISIBLE : View.GONE);
    }
}
