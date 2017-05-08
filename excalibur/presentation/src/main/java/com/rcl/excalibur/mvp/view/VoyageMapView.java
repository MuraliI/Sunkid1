package com.rcl.excalibur.mvp.view;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.graphics.PointF;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.davemorrissey.labs.subscaleview.SubsamplingScaleImageView;
import com.rcl.excalibur.R;
import com.rcl.excalibur.activity.VoyageMapActivity;
import com.rcl.excalibur.adapters.ShipStatsAdapter;
import com.rcl.excalibur.custom.view.VoyageMapImageView;
import com.rcl.excalibur.model.ShipStatsModel;
import com.rcl.excalibur.mvp.view.base.ActivityView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class VoyageMapView extends ActivityView<VoyageMapActivity, Void, ShipStatsModel> {
    private static final int MINIMUM_DPI = 80;
    private static final int SCREEN_DIVISOR = 2;
    private static final String ALPHA_PROPERTY = "alpha";
    private static final float ALPHA_OFF = 0.0f;
    private static final float ALPHA_VISIBLE = 1.0f;
    private static final int ANIMATOR_DURATION = 300;

    private VoyageMapImageView voyageMapImage;
    @BindView(R.id.image_ship_voyage) ImageView ship;
    @BindView(R.id.image_voyage_map) FrameLayout mapContainer;
    @BindView(R.id.date_picker_plans_tab) TextView dayPickerText;
    @BindView(R.id.text_ship_status) TextView textShipText;
    @BindView(R.id.view_white_voyage_map) View whiteBarView;
    @BindView(R.id.recycler_view) RecyclerView recyclerView;
    @BindView(R.id.bottom_back_view) View backView;

    private ShipStatsAdapter adapter;

    public VoyageMapView(VoyageMapActivity activity) {
        super(activity);
        ButterKnife.bind(this, activity);
        voyageMapImage = VoyageMapImageView.getInstance();
        voyageMapImage.initialize();
        mapContainer.addView(voyageMapImage);
    }

    public void init(int width) {
        whiteBarView.getLayoutParams().width = width / SCREEN_DIVISOR;
    }

    public void initVoyageMapImage() {
        voyageMapImage.setMinimumDpi(MINIMUM_DPI);
        voyageMapImage.setZoomEnabled(false);
        adapter = new ShipStatsAdapter(adapterObserver);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        recyclerView.setAdapter(adapter);
    }

    public void setCruiseCoordinate(PointF point) {
        voyageMapImage.setCruiseCoord(point);
    }

    public void setScaleAndCenter(PointF point) {
        voyageMapImage.setScaleAndCenter(voyageMapImage.getMaxScale() / VoyageMapImageView.SCALE_FACTOR, point);
    }

    public void hideShip() {
        Animator shipAnimator = ObjectAnimator.ofFloat(ship, ALPHA_PROPERTY, ALPHA_OFF);
        Animator mapAnimator = ObjectAnimator.ofFloat(mapContainer, ALPHA_PROPERTY, ALPHA_VISIBLE);
        Animator recyclerAnimator = ObjectAnimator.ofFloat(recyclerView, ALPHA_PROPERTY, ALPHA_VISIBLE);
        shipAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                ship.setVisibility(View.GONE);
            }
        });
        AnimatorSet animatorSet = new AnimatorSet().setDuration(ANIMATOR_DURATION);
        animatorSet.setInterpolator(new AccelerateInterpolator());
        animatorSet.playTogether(shipAnimator, mapAnimator, recyclerAnimator);
        animatorSet.start();
        backView.setVisibility(View.VISIBLE);
    }

    public void showShipAndFinishWithTransition() {
        ship.setVisibility(View.VISIBLE);
        ship.setAlpha(0.0f);
        Animator shipAnimator = ObjectAnimator.ofFloat(ship, ALPHA_PROPERTY, ALPHA_VISIBLE);
        Animator mapAnimator = ObjectAnimator.ofFloat(mapContainer, ALPHA_PROPERTY, ALPHA_OFF);
        shipAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                if (getActivity() != null) {
                    getActivity().finishAfterTransition();
                }
            }
        });
        AnimatorSet animatorSet = new AnimatorSet().setDuration(ANIMATOR_DURATION);
        animatorSet.setInterpolator(new DecelerateInterpolator());
        animatorSet.playTogether(shipAnimator, mapAnimator);
        animatorSet.start();
    }

    public void setTextShipLocation(String textShip, String day) {
        dayPickerText.setText(day);
        textShipText.setText(textShip);
    }

    public void animatePointToCenter(PointF point, SubsamplingScaleImageView.OnAnimationEventListener event) {
        voyageMapImage.animatePointToCenter(point, event);
    }

    public void addAll(List<ShipStatsModel> list) {
        adapter.addAll(list);
    }

    public void onDestroy() {
        mapContainer.removeView(voyageMapImage);
    }
}
