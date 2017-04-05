package com.rcl.excalibur.mvp.view;

import android.support.annotation.ColorRes;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
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
import io.reactivex.Observable;


public class ProductDetailView extends ActivityView<ProductDetailActivity, String> {

    @Bind(R.id.recycler_discover_item_details) RecyclerView planDetailRecycler;
    @Bind(R.id.app_bar_layout_detail) AppBarLayout appBarLayout;
    @Bind(R.id.collapsing_toolbar_detail) CollapsingToolbarLayout collapsingToolbar;
    @Bind(R.id.realtime_blur_view) RealtimeBlurView realtimeBlurView;
    @Bind(R.id.toolbar_detail) Toolbar detailToolbar;
    @Bind(R.id.image_hero) ImageView heroImage;

    private View detailInfoView;
    private View detailInfoContainer;
    private TextView productDetailName;

    private DetailViewCoordinatorAdapter adapter;
    private String productTitle;
    private int scrolledAmount = 0;

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
    }

    public void setHeroImage(String url) {
        ProductDetailActivity activity = getActivity();
        if (activity == null) {
            return;
        }
        Picasso.with(activity)
                .load(url)
                .placeholder(R.drawable.placeholder_hero_image)
                .into(heroImage);
    }

    @SuppressWarnings("unchecked")
    public void render(List<RecyclerViewType> viewTypes) {
        ProductDetailActivity activity = getActivity();
        if (activity == null) {
            return;
        }

        adapter = new DetailViewCoordinatorAdapter(adapterObserver, viewTypes);
        planDetailRecycler.setAdapter(adapter);
        planDetailRecycler.setLayoutManager(new LinearLayoutManager(activity));
        // Cannot be passed to Activity because RecyclerView.OnScrollListener is a class and not an interface
        planDetailRecycler.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                scrolledAmount += dy;

                int firstVisibleItem = layoutManager.findFirstVisibleItemPosition();
                if (firstVisibleItem != 0) {
                    return;
                }

                if (detailInfoView == null) {
                    detailInfoView = recyclerView.getChildAt(firstVisibleItem);
                }

                if (detailInfoContainer == null && detailInfoView != null) {
                    detailInfoContainer = ButterKnife.findById(detailInfoView, R.id.layout_information_container);
                }

                if (productDetailName == null && detailInfoView != null) {
                    productDetailName = ButterKnife.findById(detailInfoView, R.id.text_product_detail_name);
                }

                if (productDetailName == null) {
                    return;
                }

                productTitle = productDetailName.getText().toString();

                if (viewObserver != null) {
                    Observable.just(new int[]{scrolledAmount, detailInfoContainer.getPaddingTop(), productDetailName.getHeight()})
                            .subscribe(viewObserver);
                }
            }
        });
    }

    public void showToastAndFinishActivity(String message) {
        ProductDetailActivity activity = getActivity();
        if (activity == null) {
            return;
        }
        Toast.makeText(activity, message, Toast.LENGTH_SHORT).show();
        activity.finish();
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

    public void showCollapsingToolbarTitle() {
        collapsingToolbar.setTitle(productTitle);
    }

    public void hideCollapsingToolbarTitle() {
        collapsingToolbar.setTitle(ConstantsUtil.EMPTY);
    }

    public void setContentScrimResource(@ColorRes int scrimRes) {
        collapsingToolbar.setContentScrimResource(scrimRes);
    }
}
