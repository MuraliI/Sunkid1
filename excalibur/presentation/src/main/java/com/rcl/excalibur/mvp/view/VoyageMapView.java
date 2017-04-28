package com.rcl.excalibur.mvp.view;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.graphics.PointF;
import android.view.View;
import android.widget.ImageView;

import com.davemorrissey.labs.subscaleview.SubsamplingScaleImageView;
import com.rcl.excalibur.R;
import com.rcl.excalibur.activity.VoyageMapActivity;
import com.rcl.excalibur.custom.view.VoyageMapImageView;
import com.rcl.excalibur.mvp.view.base.ActivityView;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.view.View.GONE;

public class VoyageMapView extends ActivityView<VoyageMapActivity, Void, Void> {
    private static final int MINIMUM_DPI = 80;
    private static final String ANIMATOR_PROPERTY = "alpha";
    private static final float ALPHA_OFF = 0.0f;
    private static final float ALPHA_VISIBLE = 1.0f;
    private static final int ANIMATOR_DURATION = 250;

    @BindView(R.id.image_ship_voyage) ImageView ship;
    @BindView(R.id.image_voyage_map) VoyageMapImageView voyageMapImage;

    public VoyageMapView(VoyageMapActivity activity) {
        super(activity);
        ButterKnife.bind(this, activity);
    }

    public void initVoyageMapImage(int resource) {
        voyageMapImage.setImage(resource);
        voyageMapImage.setPanLimit(SubsamplingScaleImageView.PAN_LIMIT_OUTSIDE);
        voyageMapImage.setMinimumDpi(MINIMUM_DPI);
    }

    public void setCruiseCoordinate(float xCoord, float yCoord) {
        if (xCoord == 0 && yCoord == 0) {
            return;
        }
        voyageMapImage.setCruiseCoord(new PointF(xCoord, yCoord));
    }

    public void hideShip() {
        Animator animator = ObjectAnimator.ofFloat(ship, ANIMATOR_PROPERTY, ALPHA_OFF);
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                ship.setVisibility(GONE);
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
}
