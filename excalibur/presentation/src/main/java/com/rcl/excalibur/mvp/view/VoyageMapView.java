package com.rcl.excalibur.mvp.view;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.graphics.PointF;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.davemorrissey.labs.subscaleview.SubsamplingScaleImageView;
import com.rcl.excalibur.R;
import com.rcl.excalibur.activity.DayPickerActivity;
import com.rcl.excalibur.activity.VoyageMapActivity;
import com.rcl.excalibur.custom.view.VoyageMapImageView;
import com.rcl.excalibur.mvp.view.base.ActivityView;
import com.rcl.excalibur.utils.ActivityUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class VoyageMapView extends ActivityView<VoyageMapActivity, Void, Void> {
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

    public VoyageMapView(VoyageMapActivity activity) {
        super(activity);
        ButterKnife.bind(this, activity);
    }

    public void init(int width) {
        whiteBarView.getLayoutParams().width = width / SCREEN_DIVISOR;
    }

    public void initVoyageMapImage(int resource) {
        voyageMapImage.setImage(resource);
        voyageMapImage.setPanLimit(SubsamplingScaleImageView.PAN_LIMIT_INSIDE);
        voyageMapImage.setMinimumDpi(MINIMUM_DPI);
        voyageMapImage.setScaleAndCenter(voyageMapImage.getMaxScale() / 2, new PointF(796, 826));
        voyageMapImage.setZoomEnabled(false);
    }

    public void setCruiseCoordinate(float xCoord, float yCoord) {
        if (xCoord == 0 && yCoord == 0) {
            return;
        }
        voyageMapImage.setCruiseCoord(new PointF(xCoord, yCoord));
    }

    public void setCruiseAngle(long cruiseAngle) {
        voyageMapImage.setAngle(cruiseAngle);
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
}
