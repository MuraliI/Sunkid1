package com.rcl.excalibur.mvp.view;


import android.graphics.Color;
import android.graphics.PointF;
import android.graphics.RectF;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.PopupWindow;

import com.davemorrissey.labs.subscaleview.SubsamplingScaleImageView;
import com.rcl.excalibur.R;
import com.rcl.excalibur.activity.DiscoverDeckMapActivity;
import com.rcl.excalibur.custom.view.DeckMapImageView;
import com.rcl.excalibur.custom.view.DeckMapPopupLayout;
import com.rcl.excalibur.domain.Product;
import com.rcl.excalibur.mvp.view.base.ActivityView;

import butterknife.Bind;
import butterknife.ButterKnife;

public class DiscoverDeckMapView extends ActivityView<DiscoverDeckMapActivity> {
    private static final int MINIMUM_DPI = 80;
    private static final float HALF_FACTOR = 2.0f;

    @Bind(R.id.image_deck_map) DeckMapImageView deckMapImage;

    private DeckMapPopupLayout popupView;
    private PopupWindow popupWindow;

    public DiscoverDeckMapView(DiscoverDeckMapActivity activity) {
        super(activity);
        ButterKnife.bind(this, activity);
    }

    public void initDeckImage(int resource) {
        deckMapImage.setImage(resource);
        deckMapImage.setPanLimit(SubsamplingScaleImageView.PAN_LIMIT_OUTSIDE);
        deckMapImage.setMinimumDpi(MINIMUM_DPI);

        DiscoverDeckMapActivity activity = getActivity();
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

        DiscoverDeckMapActivity activity = getActivity();
        if (activity != null) {
            popupWindow.setTouchInterceptor(activity);
        }
    }

    public void setProductCoordinate(float xCoord, float yCoord) {
        deckMapImage.setProductCoord(new PointF(xCoord, yCoord));
    }

    public void moveToProductCoordinate(float xCoord, float yCoord) {
        deckMapImage.animatePointToCenter(new PointF(xCoord, yCoord));
    }

    public boolean isDeckMapImageReady() {
        return deckMapImage.isReady();
    }

    public void removeTreeObserver() {
        DiscoverDeckMapActivity activity = getActivity();
        if (activity != null) {
            deckMapImage.getViewTreeObserver()
                    .removeOnGlobalLayoutListener(activity);
        }
    }

    public void showProductOnPopupLayout(@NonNull Product product) {
        DiscoverDeckMapActivity activity = getActivity();
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
}
