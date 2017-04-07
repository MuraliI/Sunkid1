package com.rcl.excalibur.mvp.view;

import android.support.design.widget.AppBarLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
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
import io.reactivex.Observable;


public class ProductDetailView extends ActivityView<ProductDetailActivity, Long> {

    @Bind(R.id.recycler_discover_item_details) RecyclerView planDetailRecycler;
    @Bind(R.id.app_bar_layout_detail) AppBarLayout appBarLayout;
    @Bind(R.id.realtime_blur_view) RealtimeBlurView realtimeBlurView;
    @Bind(R.id.toolbar_detail) Toolbar detailToolbar;
    @Bind(R.id.image_hero) ImageView heroImage;
    @Bind(R.id.tv_detail_toolbar_title) TextView titleToolbarTextView;

    private Animation upAnimation;
    private Animation downAnimation;
    private View detailInfoView;
    private TextView productDetailName;

    private String productTitle;

    public ProductDetailView(ProductDetailActivity activity) {
        super(activity);
        ButterKnife.bind(this, activity);
    }

    public void initAnimation() {
        ProductDetailActivity activity = getActivity();
        if (activity == null) {
            return;
        }
        upAnimation = AnimationUtils.loadAnimation(activity, R.anim.toolbar_title_up);
        downAnimation = AnimationUtils.loadAnimation(activity, R.anim.toolbar_title_down);
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

    private void upAnimationTitle() {
        upAnimation.reset();
        titleToolbarTextView.startAnimation(upAnimation);
    }

    private void downAnimationTitle() {
        downAnimation.reset();
        titleToolbarTextView.startAnimation(downAnimation);
    }

    @SuppressWarnings("unchecked")
    public void render(List<RecyclerViewType> viewTypes) {
        ProductDetailActivity activity = getActivity();
        if (activity == null) {
            return;
        }

        planDetailRecycler.setAdapter(new DetailViewCoordinatorAdapter(adapterObserver, viewTypes));
        LinearLayoutManager layoutManager = new LinearLayoutManager(activity);
        planDetailRecycler.setLayoutManager(layoutManager);
        // Cannot be passed to Activity because RecyclerView.OnScrollListener is a class and not an interface
        planDetailRecycler.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                int firstVisibleItem = layoutManager.findFirstVisibleItemPosition();
                if (firstVisibleItem != 0) {
                    return;
                }

                if (detailInfoView == null) {
                    detailInfoView = recyclerView.getChildAt(firstVisibleItem);
                }

                if (productDetailName == null && detailInfoView != null) {
                    productDetailName = ButterKnife.findById(detailInfoView, R.id.text_product_detail_name);
                }

                if (productDetailName == null) {
                    return;
                }

                productTitle = productDetailName.getText().toString();

                int[] outLocation = new int[2];
                productDetailName.getLocationOnScreen(outLocation);

                if (viewObserver != null) {
                    Observable.just(outLocation[1]).subscribe(viewObserver);
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
        titleToolbarTextView.setText(productTitle);
        upAnimationTitle();
    }

    public void hideCollapsingToolbarTitle() {
        downAnimationTitle();
    }
}
