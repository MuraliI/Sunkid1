package com.rcl.excalibur.mvp.view;

import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.widget.ImageView;
import android.widget.Toast;

import com.github.mmin18.widget.RealtimeBlurView;
import com.rcl.excalibur.R;
import com.rcl.excalibur.activity.ProductDetailActivity;
import com.rcl.excalibur.adapters.base.RecyclerViewType;
import com.rcl.excalibur.adapters.delegate.DetailViewCoordinatorAdapter;
import com.rcl.excalibur.mvp.view.base.ActivityView;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;


public class ProductDetailView extends ActivityView<ProductDetailActivity> {
    private static final int MAX_BLUR_VALUE = 25;

    @Bind(R.id.recycler_discover_item_details) RecyclerView planDetailRecycler;
    @Bind(R.id.app_bar_layout_detail) AppBarLayout appBarLayout;
    @Bind(R.id.collapsing_toolbar_detail) CollapsingToolbarLayout collapsingToolbar;
    @Bind(R.id.realtime_blur_view) RealtimeBlurView realtimeBlurView;
    @Bind(R.id.toolbar_detail) Toolbar detailToolbar;
    @Bind(R.id.image_hero) ImageView heroImage;

    private DetailViewCoordinatorAdapter adapter;

    public ProductDetailView(ProductDetailActivity activity) {
        super(activity);
        ButterKnife.bind(this, activity);
    }

    public void setupToolbar() {
        ProductDetailActivity activity = getActivity();
        if (activity == null) {
            return;
        }
        activity.setSupportActionBar(detailToolbar);
        appBarLayout.addOnOffsetChangedListener(activity);
        collapsingToolbar.setExpandedTitleColor(ContextCompat.getColor(activity,
                android.R.color.transparent));
    }

    public void setHeroImage(String url) {
        if (getActivity() != null) {
            Picasso.with(getActivity()).load(url).placeholder(R.drawable.thumb).into(heroImage);
        }
    }

    public void showOnlyReservationIcon() {
        // TODO: Check if there is going to be use for this method once the change on the details has been made
    }

    public void setDetailTitle(String title) {
        collapsingToolbar.setTitle(title);
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

    public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
        ProductDetailActivity activity = getActivity();
        if (activity == null) {
            return;
        }
        realtimeBlurView.setBlurRadius(TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                getBlurRadius(appBarLayout, verticalOffset),
                activity.getResources().getDisplayMetrics())
        );
    }

    private float getBlurRadius(AppBarLayout appBarLayout, int verticalOffset) {
        return (Math.abs(verticalOffset) * MAX_BLUR_VALUE) / appBarLayout.getTotalScrollRange();
    }
}
