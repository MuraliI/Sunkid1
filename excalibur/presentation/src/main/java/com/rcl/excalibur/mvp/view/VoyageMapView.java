package com.rcl.excalibur.mvp.view;

import android.graphics.PointF;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

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

    @BindView(R.id.recycler_view) RecyclerView recyclerView;
    @BindView(R.id.image_voyage_map) VoyageMapImageView voyageMapImage;

    private ShipStatsAdapter adapter;


    public VoyageMapView(VoyageMapActivity activity) {
        super(activity);
        ButterKnife.bind(this, activity);
    }

    public void initVoyageMapImage(int resource) {
        voyageMapImage.setImage(resource);
        voyageMapImage.setPanLimit(SubsamplingScaleImageView.PAN_LIMIT_OUTSIDE);
        voyageMapImage.setMinimumDpi(MINIMUM_DPI);

        adapter = new ShipStatsAdapter(adapterObserver);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(),
                LinearLayoutManager.HORIZONTAL, false));
        recyclerView.setAdapter(adapter);
    }

    public void setCruiseCoordinate(float xCoord, float yCoord) {
        if (xCoord == 0 && yCoord == 0) {
            return;
        }
        voyageMapImage.setCruiseCoord(new PointF(xCoord, yCoord));
    }

    public void addAll(List<ShipStatsModel> list) {
        adapter.addAll(list);
    }
}
