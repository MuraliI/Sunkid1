package com.rcl.excalibur.deckmap.mvp.view;


import android.graphics.Color;
import android.graphics.PointF;
import android.graphics.RectF;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.PopupWindow;

import com.davemorrissey.labs.subscaleview.SubsamplingScaleImageView;
import com.rcl.excalibur.R;
import com.rcl.excalibur.deckmap.activity.DiscoverDeckMapActivity;
import com.rcl.excalibur.deckmap.custom.view.MarkerImageView;
import com.rcl.excalibur.deckmap.custom.view.PopupLayout;
import com.rcl.excalibur.domain.Product;
import com.rcl.excalibur.mvp.view.base.ActivityView;

import butterknife.Bind;
import butterknife.ButterKnife;

public class DiscoverDeckMapView extends ActivityView<DiscoverDeckMapActivity> {
    private static final int MINIMUM_DPI = 80;

    @Bind(R.id.image_ship) MarkerImageView shipImage;

    private PopupLayout popupView;
    private PopupWindow popupWindow;

    public DiscoverDeckMapView(DiscoverDeckMapActivity activity) {
        super(activity);
        ButterKnife.bind(this, activity);
    }

    public void initDeckImage(int resource) {
        shipImage.setImage(resource);
        shipImage.setPanLimit(SubsamplingScaleImageView.PAN_LIMIT_OUTSIDE);
        shipImage.setMinimumDpi(MINIMUM_DPI);

        if (getActivity() != null) {
            shipImage.getViewTreeObserver().addOnGlobalLayoutListener(getActivity());
            shipImage.setOnTouchListener(getActivity());
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

    public void setProductCoordinate(PointF productCoordinate) {
        shipImage.setProductCoord(productCoordinate);
    }

    public void moveToProductCoordinate(PointF coordinate) {
        shipImage.animatePointToCenter(coordinate);
    }

    public boolean isShipImageReady() {
        return shipImage.isReady();
    }

    public void removeTreeObserver() {
        if (getActivity() != null) {
            shipImage.getViewTreeObserver()
                    .removeOnGlobalLayoutListener(getActivity());
        }
    }

    public void showProductOnPopupLayout(Product product) {
        if (getActivity() != null) {
            popupView.setProduct(product);

            int pxHeight = getActivity().getResources().getDimensionPixelOffset(R.dimen.item_deck_height);
            popupWindow.showAtLocation(shipImage, Gravity.CENTER, 0, (int) (-pxHeight / 1.3));
        }
    }

    public void dismissPopupWindow() {
        if (popupWindow != null) {
            popupWindow.dismiss();
        }
    }

    public RectF getMarkerArea() {
        return shipImage.getMarkerArea();
    }
}
