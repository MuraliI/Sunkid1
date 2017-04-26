package com.rcl.excalibur.mvp.view;

import android.graphics.PointF;

import com.davemorrissey.labs.subscaleview.SubsamplingScaleImageView;
import com.rcl.excalibur.R;
import com.rcl.excalibur.activity.VoyageMapActivity;
import com.rcl.excalibur.custom.view.VoyageMapImageView;
import com.rcl.excalibur.mvp.view.base.ActivityView;

import butterknife.Bind;
import butterknife.ButterKnife;

public class VoyageMapView extends ActivityView<VoyageMapActivity, Void, Void> {
    private static final int MINIMUM_DPI = 80;
    @Bind(R.id.image_voyage_map) VoyageMapImageView voyageMapImage;

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
}
