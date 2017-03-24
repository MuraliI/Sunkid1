package com.rcl.excalibur.deckmap.mvp.view;


import android.graphics.Color;
import android.graphics.PointF;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.PopupWindow;

import com.davemorrissey.labs.subscaleview.SubsamplingScaleImageView;
import com.rcl.excalibur.R;
import com.rcl.excalibur.deckmap.activity.DiscoverDeckMapActivity;
import com.rcl.excalibur.deckmap.custom.view.MarkerImageView;
import com.rcl.excalibur.deckmap.custom.view.PopupLayout;
import com.rcl.excalibur.deckmap.model.ProductDeckMapModel;
import com.rcl.excalibur.domain.Product;
import com.rcl.excalibur.mvp.view.base.ActivityView;

import butterknife.Bind;
import butterknife.ButterKnife;

public class DiscoverDeckMapView extends ActivityView<DiscoverDeckMapActivity> {
    private static final int MINIMUM_DPI = 80;
    private static final double FACTOR = 1.3;

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
        //shipImage.setScaleAndCenter(shipImage.getMaxScale(), new PointF(0, 0));
        shipImage.setMinimumDpi(MINIMUM_DPI);
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
        // Can be used to get the touch event from the popup window
        popupWindow.setTouchInterceptor((v, event) -> {
            popupWindow.dismiss();
            return true;
        });
    }

    public void setProductDeckMapModel(ProductDeckMapModel productDeckMapModel) {
        shipImage.setProductDeckMap(productDeckMapModel);
    }

    public void setOnMarkerClickListener(MarkerImageView.OnMarkerClickListener listener) {
        shipImage.setOnMarkerClickListener(listener);
    }

    public void moveToProductCoordinate(PointF coordinate) {
        shipImage.animatePointToCenter(coordinate);
    }

    public void showProductOnPopupLayout(Product product) {
        popupView.setProduct(product);

        int pxHeight = getContext().getResources().getDimensionPixelOffset(R.dimen.item_deck_height);
        popupWindow.showAtLocation(shipImage, Gravity.CENTER, 0, (int) (-pxHeight / FACTOR));
    }

    public void onDestroy() {
        if (popupWindow != null) {
            popupWindow.dismiss();
        }
    }
}
