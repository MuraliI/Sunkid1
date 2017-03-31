package com.rcl.excalibur.mvp.view;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.rcl.excalibur.R;
import com.rcl.excalibur.activity.ProductDetailActivity;
import com.rcl.excalibur.adapters.base.RecyclerViewType;
import com.rcl.excalibur.adapters.delegate.DetailViewCoordinatorAdapter;
import com.rcl.excalibur.custom.view.ReservationDetailLayout;
import com.rcl.excalibur.mvp.view.base.ActivityView;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;


public class ProductDetailView extends ActivityView<ProductDetailActivity> {

    @Bind(R.id.recycler_discover_item_details) RecyclerView planDetailRecycler;
    @Bind(R.id.toolbar_detail) Toolbar detailToolbar;
    @Bind(R.id.text_discover_item_name) TextView discoverItemName;
    @Bind(R.id.frame_layout_reservation_container) ReservationDetailLayout reservationDetailLayout;
    @Bind(R.id.fab_reserve_discover_item) FloatingActionButton reserveButton;
    @Bind(R.id.image_hero) ImageView heroImage;
    @Bind(R.id.btn_deck_map) ImageButton deckButton;

    private DetailViewCoordinatorAdapter adapter;

    public ProductDetailView(ProductDetailActivity activity) {
        super(activity);
        ButterKnife.bind(this, activity);
        activity.setSupportActionBar(detailToolbar);
    }

    public void hideDeckMapButton() {
        deckButton.setVisibility(View.GONE);
    }

    public void setHeroImage(String url) {
        if (getActivity() != null) {
            Picasso.with(getActivity()).load(url).placeholder(R.drawable.thumb).into(heroImage);
        }
    }

    public void showOnlyReservationIcon() {
        reservationDetailLayout.showOnlyReservationIcon();
        reserveButton.setVisibility(View.GONE);
    }

    public void setDetailTitle(String title) {
        discoverItemName.setText(title);
    }

    public void render(List<RecyclerViewType> viewTypes) {
        adapter = new DetailViewCoordinatorAdapter(viewObserver, viewTypes);
        planDetailRecycler.setAdapter(adapter);

        if (getActivity() != null) {
            LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
            planDetailRecycler.setLayoutManager(layoutManager);
            planDetailRecycler.addItemDecoration(new DividerItemDecoration(getActivity(), layoutManager.getOrientation()));
        }
    }

    public void showToastAndFinishActivity(String message) {
        if (getActivity() != null) {
            Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
            getActivity().finish();
        }
    }
}