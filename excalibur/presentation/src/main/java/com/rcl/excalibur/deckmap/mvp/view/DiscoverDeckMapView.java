package com.rcl.excalibur.mvp.view;


import android.graphics.PointF;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.davemorrissey.labs.subscaleview.SubsamplingScaleImageView;
import com.rcl.excalibur.R;
import com.rcl.excalibur.custom.view.MarkerImageView;
import com.rcl.excalibur.mvp.view.base.ActivityView;

import butterknife.Bind;
import butterknife.ButterKnife;

public class DiscoverDeckMapView extends ActivityView<AppCompatActivity> {
    private static final int MINIMUM_DPI = 80;

    @Bind(R.id.toolbar_deck_map) Toolbar deckMapToolbar;
    @Bind(R.id.image_ship) MarkerImageView shipImage;

    public DiscoverDeckMapView(AppCompatActivity activity) {
        super(activity);
        ButterKnife.bind(this, activity);
        activity.setSupportActionBar(deckMapToolbar);
    }

    public void initDeckImage(int resource) {
        shipImage.setImage(resource);
        shipImage.setPanLimit(SubsamplingScaleImageView.PAN_LIMIT_OUTSIDE);
        shipImage.setScaleAndCenter(shipImage.getMaxScale(), new PointF(0, 0));
        shipImage.setMinimumDpi(MINIMUM_DPI);
    }
}
