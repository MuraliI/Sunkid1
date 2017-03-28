package com.rcl.excalibur.deckmap.mvp.view;


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
import com.rcl.excalibur.deckmap.activity.DiscoverDeckMapActivity;
import com.rcl.excalibur.deckmap.custom.view.DeckMapImageView;
import com.rcl.excalibur.deckmap.custom.view.PopupLayout;
import com.rcl.excalibur.domain.Product;
import com.rcl.excalibur.mvp.view.base.ActivityView;

import butterknife.Bind;
import butterknife.ButterKnife;

public class DiscoverDeckMapView extends ActivityView<DiscoverDeckMapActivity> {
    private static final int MINIMUM_DPI = 80;

    @Bind(R.id.image_deck_map) DeckMapImageView deckMapImage;

    private PopupLayout popupView;
    private PopupWindow popupWindow;

    public DiscoverDeckMapView(DiscoverDeckMapActivity activity) {
        super(activity);
        ButterKnife.bind(this, activity);
    }

    public void initDeckImage(int resource) {
        deckMapImage.setImage(resource);
        deckMapImage.setPanLimit(SubsamplingScaleImageView.PAN_LIMIT_OUTSIDE);
        deckMapImage.setMinimumDpi(MINIMUM_DPI);

        if (getActivity() != null) {
            deckMapImage.getViewTreeObserver().addOnGlobalLayoutListener(getActivity());
            deckMapImage.setOnTouchListener(getActivity());
        }
    }

    public void initPopupLayout() {
        popupView = new PopupLayout(getContext());
        popupWindow = new PopupWindow(
                popupView,
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );
        popupWindow.setOutsideTouchable(true);
        popupWindow.setTouchable(true);
        popupWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        if (getActivity() != null) {
            popupWindow.setTouchInterceptor(getActivity());
        }
    }

    public void setProductCoordinate(float xCoord, float yCoord) {
        deckMapImage.setProductCoord(new PointF(xCoord, yCoord));
    }

    public void moveToProductCoordinate(float xCoord, float yCoord) {
        deckMapImage.animatePointToCenter(new PointF(xCoord, yCoord));
    }

    public boolean isShipImageReady() {
        return deckMapImage.isReady();
    }

    public void removeTreeObserver() {
        if (getActivity() != null) {
            deckMapImage.getViewTreeObserver()
                    .removeOnGlobalLayoutListener(getActivity());
        }
    }

    public void showProductOnPopupLayout(@NonNull Product product) {
        popupView.setProduct(product);
        int elevation = 0;
        if (getActivity() != null) {
            float elevationError = getActivity().getResources().getDimension(R.dimen.item_deck_elevation_error);
            float popUpHeight = getActivity().getResources().getDimensionPixelOffset(R.dimen.item_deck_height);
            elevation = (int) (popUpHeight / 2.0f + deckMapImage.getMarketHeight() - elevationError);
        }
        popupWindow.showAtLocation(deckMapImage, Gravity.CENTER, 0, -elevation);
    }

    public void dismissPopupWindow() {
        if (popupWindow != null) {
            popupWindow.dismiss();
        }
    }

    public RectF getMarkerArea() {
        return deckMapImage.getMarkerArea();
    }
}
