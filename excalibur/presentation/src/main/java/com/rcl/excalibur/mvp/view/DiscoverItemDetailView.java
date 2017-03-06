package com.rcl.excalibur.mvp.view;

import android.support.design.widget.FloatingActionButton;
import android.support.v4.util.SparseArrayCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.rcl.excalibur.R;
import com.rcl.excalibur.ReservationDetailLayout;
import com.rcl.excalibur.adapters.base.DelegateAdapter;
import com.rcl.excalibur.adapters.base.RecyclerViewType;
import com.rcl.excalibur.adapters.delegate.DetailViewCoordinatorAdapter;
import com.rcl.excalibur.mvp.view.base.ActivityView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;


public class DiscoverItemDetailView extends ActivityView {

    @Bind(R.id.recycler_discover_item_details) RecyclerView planDetailRecycler;
    @Bind(R.id.toolbar_detail) Toolbar detailToolbar;
    @Bind(R.id.text_discover_item_name) TextView discoverItemName;
    @Bind(R.id.frame_layout_reservation_container) ReservationDetailLayout reservationDetailLayout;
    @Bind(R.id.fab_reserve_discover_item) FloatingActionButton reserveButton;
    @Bind(R.id.image_hero) ImageView heroImage;

    private DetailViewCoordinatorAdapter adapter;

    public DiscoverItemDetailView(AppCompatActivity activity) {
        super(activity);
        ButterKnife.bind(this, activity);
        activity.setSupportActionBar(detailToolbar);
    }

    public void setHeroImage(String url) {
        //TODO initialize image
    }

    public void showOnlyReservationIcon() {
        reservationDetailLayout.showOnlyReservationIcon();
        reserveButton.setVisibility(View.GONE);
    }

    public void setDetailTitle(String title) {
        discoverItemName.setText(title);
    }

    public void render(SparseArrayCompat<DelegateAdapter> adapterList, List<RecyclerViewType> viewTypes) {
        adapter = new DetailViewCoordinatorAdapter(viewObserver, adapterList, viewTypes);
        planDetailRecycler.setAdapter(adapter);
        planDetailRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
    }
}
