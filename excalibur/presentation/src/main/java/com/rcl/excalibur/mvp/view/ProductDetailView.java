package com.rcl.excalibur.mvp.view;

import android.support.annotation.ColorRes;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.DividerItemDecoration;
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


public class ProductDetailView extends ActivityView<ProductDetailActivity, String> {

    @Bind(R.id.recycler_discover_item_details) RecyclerView planDetailRecycler;
    @Bind(R.id.app_bar_layout_detail) AppBarLayout appBarLayout;
    @Bind(R.id.collapsing_toolbar_detail) CollapsingToolbarLayout collapsingToolbar;
    @Bind(R.id.realtime_blur_view) RealtimeBlurView realtimeBlurView;
    @Bind(R.id.toolbar_detail) Toolbar detailToolbar;
    @Bind(R.id.image_hero) ImageView heroImage;
    @Bind(R.id.tv_detail_toolbar_title) TextView titleToolbarTextView;

    private DetailViewCoordinatorAdapter adapter;
    private Animation upAnimation;
    private Animation downAnimation;

    private boolean isTitleVisible = false;
    private int scrolledAmount = 0;

    public ProductDetailView(ProductDetailActivity activity) {
        super(activity);
        ButterKnife.bind(this, activity);
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
        if (getActivity() != null) {
            Picasso.with(getActivity()).load(url).placeholder(R.drawable.placeholder_hero_image).into(heroImage);
        }
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

                    TextView textView = (TextView) view.findViewById(R.id.text_product_detail_name);
                    if (textView == null) {
                        return;
                    }

                    if (scrolledAmount >= view.getPaddingTop() + (textView.getHeight() / 2) && !isTitleVisible) {
                        isTitleVisible = true;
                        titleToolbarTextView.setText(textView.getText().toString());
                        upAnimationTitle();
                    } else if (scrolledAmount < view.getPaddingTop() + (textView.getHeight() / 2) && isTitleVisible) {
                        isTitleVisible = false;
                        downAnimationTitle();
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
