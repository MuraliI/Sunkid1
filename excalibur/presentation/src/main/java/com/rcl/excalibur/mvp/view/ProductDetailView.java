package com.rcl.excalibur.mvp.view;

import android.support.annotation.ColorRes;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mmin18.widget.RealtimeBlurView;
import com.rcl.excalibur.R;
import com.rcl.excalibur.activity.ProductDetailActivity;
import com.rcl.excalibur.adapters.base.RecyclerViewType;
import com.rcl.excalibur.adapters.delegate.DetailViewCoordinatorAdapter;
import com.rcl.excalibur.domain.utils.ConstantsUtil;
import com.rcl.excalibur.mvp.view.base.ActivityView;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;


public class ProductDetailView extends ActivityView<ProductDetailActivity, String> {

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
            Picasso.with(getActivity()).load(url).placeholder(R.drawable.placeholder_hero_image).into(heroImage);
        }
    }

    private int scrolledAmount = 0;

    @SuppressWarnings("unchecked")
    public void render(List<RecyclerViewType> viewTypes) {
        adapter = new DetailViewCoordinatorAdapter(adapterObserver, viewTypes);
        planDetailRecycler.setAdapter(adapter);

        ProductDetailActivity activity = getActivity();
        if (activity == null) {
            return;
        }

        LinearLayoutManager layoutManager = new LinearLayoutManager(activity);
        planDetailRecycler.setLayoutManager(layoutManager);
        planDetailRecycler.addItemDecoration(new DividerItemDecoration(activity, layoutManager.getOrientation()));
        // Cannot be passed to Activity because RecyclerView.OnScrollListener is a class and not an interface
        planDetailRecycler.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                scrolledAmount += dy;

                if (layoutManager.findFirstVisibleItemPosition() == 0) {
                    View view = recyclerView.getChildAt(0);
                    if (view == null) {
                        return;
                    }

                    TextView textView = (TextView) view.findViewById(R.id.text_module_title);
                    if (textView == null) {
                        return;
                    }

                    if (scrolledAmount >= view.getPaddingTop() + (textView.getHeight() / 2)) {
                        collapsingToolbar.setTitle(textView.getText().toString());
                    } else {
                        collapsingToolbar.setTitle(ConstantsUtil.EMPTY);
                    }
                }
            }
        });
    }

    public void showToastAndFinishActivity(String message) {
        if (getActivity() != null) {
            Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
            getActivity().finish();
        }
    }

    public void setBlurRadiusOnImage(float blurRadius) {
        ProductDetailActivity activity = getActivity();
        if (activity == null) {
            return;
        }
        realtimeBlurView.setBlurRadius(TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                blurRadius,
                activity.getResources().getDisplayMetrics())
        );
    }

    public void setContentScrimResource(@ColorRes int scrimRes) {
        collapsingToolbar.setContentScrimResource(scrimRes);
    }
}
