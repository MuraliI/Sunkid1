package com.rcl.excalibur.mvp.view;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.graphics.PointF;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.davemorrissey.labs.subscaleview.SubsamplingScaleImageView;
import com.rcl.excalibur.R;
import com.rcl.excalibur.activity.DayPickerActivity;
import com.rcl.excalibur.activity.VoyageMapActivity;
import com.rcl.excalibur.adapters.ShipStatsAdapter;
import com.rcl.excalibur.custom.view.VoyageMapImageView;
import com.rcl.excalibur.model.ShipStatsModel;
import com.rcl.excalibur.mvp.view.base.ActivityView;
import com.rcl.excalibur.utils.ActivityUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class VoyageMapView extends ActivityView<VoyageMapActivity, Void, ShipStatsModel> {
    private static final int MINIMUM_DPI = 80;
    private static final int SCREEN_DIVISOR = 2;
    private static final String ANIMATOR_PROPERTY = "alpha";
    private static final float ALPHA_OFF = 0.0f;
    private static final float ALPHA_VISIBLE = 1.0f;
    private static final int ANIMATOR_DURATION = 250;

    @BindView(R.id.image_ship_voyage) ImageView ship;
    @BindView(R.id.image_voyage_map) VoyageMapImageView voyageMapImage;
    @BindView(R.id.date_picker_plans_tab) TextView dayPickerText;
    @BindView(R.id.text_ship_status) TextView textShipText;
    @BindView(R.id.view_white_voyage_map) View whiteBarView;
    @BindView(R.id.recycler_view) RecyclerView recyclerView;

    private ShipStatsAdapter adapter;

    public VoyageMapView(VoyageMapActivity activity) {
        super(activity);
        ButterKnife.bind(this, activity);
    }

    public void init(int width) {
        whiteBarView.getLayoutParams().width = width / SCREEN_DIVISOR;
    }

    public void initVoyageMapImage(int resource, SubsamplingScaleImageView.OnImageEventListener event) {
        voyageMapImage.setImage(resource);
        voyageMapImage.setMinimumDpi(MINIMUM_DPI);
        voyageMapImage.setZoomEnabled(false);
        voyageMapImage.setOnImageEventListener(event);

        adapter = new ShipStatsAdapter(adapterObserver);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        recyclerView.setAdapter(adapter);
    }

    public void setCruiseCoordinate(PointF point) {
        voyageMapImage.setCruiseCoord(point);
    }

    public void setScaleAndCenter(PointF point) {
        voyageMapImage.setScaleAndCenter(voyageMapImage.getMaxScale() / voyageMapImage.SCALE_FACTOR, point);
    }

    @OnClick(R.id.date_picker_plans_tab)
    void onDayPickerClick() {
        ActivityUtils.startActivity(getActivity(), DayPickerActivity.getStartIntent(getActivity()));
    }

    public void hideShip() {
        Animator animator = ObjectAnimator.ofFloat(ship, ANIMATOR_PROPERTY, ALPHA_OFF);
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                ship.setVisibility(View.GONE);
            }
        });
        animator.setDuration(ANIMATOR_DURATION)
                .start();
    }

    public void showShipAndFinishWithTransition() {
        ship.setVisibility(View.VISIBLE);
        Animator animator = ObjectAnimator.ofFloat(ship, ANIMATOR_PROPERTY, ALPHA_VISIBLE);
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                if (getActivity() != null) {
                    getActivity().finishAfterTransition();
                }
            }
        });
        animator.setDuration(ANIMATOR_DURATION)
                .start();
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
}
