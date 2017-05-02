package com.rcl.excalibur.mvp.view;

import android.graphics.PointF;
import android.view.View;
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
        voyageMapImage.setPanLimit(SubsamplingScaleImageView.PAN_LIMIT_OUTSIDE);
        voyageMapImage.setMinimumDpi(MINIMUM_DPI);
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

    public void setHeader(String day) {

    }

    @OnClick(R.id.date_picker_plans_tab)
    public void onDayPickerClick() {
        ActivityUtils.startActivity(getActivity(), DayPickerActivity.getStartIntent(getActivity()));
    }

    public void setTextShipLocation(String textShip, String day) {
        dayPickerText.setText(day);
        textShipText.setText(textShip);
    }
}
