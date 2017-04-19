package com.rcl.excalibur.mvp.view;


import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;

import com.rcl.excalibur.R;
import com.rcl.excalibur.activity.BaseActivity;
import com.rcl.excalibur.activity.ProductDetailActivity;
import com.rcl.excalibur.adapters.planner.abstractitem.PlannerProductItem;
import com.rcl.excalibur.custom.itinerary.RoyalLinearLayoutManager;
import com.rcl.excalibur.fragments.PlannerFragment;
import com.rcl.excalibur.mvp.view.base.FragmentView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import eu.davidea.flexibleadapter.FlexibleAdapter;
import eu.davidea.flexibleadapter.items.AbstractFlexibleItem;

public class PlannerView extends FragmentView<PlannerFragment, Void, Void> {
    private static final int TOP_OF_LIST = 0;

    @Bind(R.id.recycler_view) RecyclerView recyclerView;
    @Bind(R.id.layout_planner_all_day) View allDayView;
    @Bind(R.id.layout_planner_container) LinearLayout containerLayout;

    private FlexibleAdapter<AbstractFlexibleItem> adapter;

    private LinearLayout firstHeader;
    private BottomSheetBehavior bottomSheetBehavior;
    private Animation slideUpAnim;

    private Animation animationGoIn;

    private boolean isExpanded = false;
    private boolean initialized = false;
    private boolean isAllDayNecessary = false;

    private int initHorizontalMargin = -1;
    private int initVerticalMargin = -1;
    private int itemCount = 0;

    public PlannerView(PlannerFragment fragment) {
        super(fragment);
        ButterKnife.bind(this, fragment.getView());
    }

    public void init() {
        final PlannerFragment fragment = getFragment();
        if (fragment == null) {
            return;
        }
        adapter = new FlexibleAdapter<>(null, fragment, true);
        adapter.setDisplayHeadersAtStartUp(true).setStickyHeaders(true);
        recyclerView.setLayoutManager(new RoyalLinearLayoutManager(fragment.getContext(), fragment));
        recyclerView.setAdapter(adapter);

        Resources resources = fragment.getResources();
        initHorizontalMargin = resources.getDimensionPixelSize(R.dimen.planner_item_init_horizontal_margin);
        initVerticalMargin = resources.getDimensionPixelSize(R.dimen.planner_item_init_vertical_margin);
    }

    public void initAnimation() {
        animationGoIn = AnimationUtils.loadAnimation(getContext(), R.anim.planner_preview_slide_in);
        slideUpAnim = AnimationUtils.loadAnimation(getContext(), R.anim.view_slide_up);
    }

    public void initBottomSheetBehavior() {
        bottomSheetBehavior = BottomSheetBehavior.from(containerLayout);
        bottomSheetBehavior.setBottomSheetCallback(
                new BottomSheetBehavior.BottomSheetCallback() {
                    @Override
                    public void onStateChanged(@NonNull View bottomSheet, int newState) {
                        if (newState == BottomSheetBehavior.STATE_EXPANDED) {
                            bottomSheetBehavior.setPeekHeight(0);
                            isExpanded = true;
                        }

                        if (newState == BottomSheetBehavior.STATE_COLLAPSED) {
                            isExpanded = false;
                            initItemsWithMargin();
                            bottomSheetBehavior.setPeekHeight(BottomSheetBehavior.PEEK_HEIGHT_AUTO);
                            containerLayout.startAnimation(animationGoIn);
                        }
                    }

                    @Override
                    public void onSlide(@NonNull View bottomSheet, float slideOffset) {
                        if (slideOffset <= 0.1 && isExpanded) {
                            hideHeadersView();
                        }
                        if (isExpanded) {
                            return;
                        }
                        for (int i = 1; i < itemCount; i++) {
                            View view = recyclerView.getLayoutManager().findViewByPosition(i);
                            int verticalMargin = getMargin(slideOffset, initVerticalMargin);
                            int horizontalMargin = getMargin(slideOffset, initHorizontalMargin);
                            if (verticalMargin <= 0 && horizontalMargin <= 0) {
                                showHeadersView();
                            }
                            resizeItemView(view, verticalMargin, horizontalMargin);
                        }
                    }
                }
        );
    }

    private int getMargin(float slideOffset, int marginValue) {
        return Math.round((1 - slideOffset) * marginValue);
    }

    private void initItemsWithMargin() {
        for (int i = 0; i < itemCount; i++) {
            View view = recyclerView.getLayoutManager().findViewByPosition(i);
            if (view == null) {
                return;
            }
            if (adapter.isHeader(adapter.getItem(i))) {
                firstHeader = (LinearLayout) view.findViewById(R.id.layout_planner_header_container);
                firstHeader.setVisibility(View.INVISIBLE);
            } else {
                view.setBackgroundResource(R.drawable.background_gradient_item_cue_card_round);
                resizeItemView(view, initVerticalMargin, initHorizontalMargin);
            }
        }
        hideHeadersView();
    }

    private void resizeItemView(View view, int verticalMargin, int horizontalMargin) {
        if (view == null) {
            return;
        }

        ViewGroup.MarginLayoutParams marginLayout = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        marginLayout.setMargins(horizontalMargin, verticalMargin, horizontalMargin, verticalMargin);

        if (initialized) {
            view.requestLayout();
        }
    }

    private void showHeadersView() {
        if (isAllDayNecessary) {
            if (firstHeader != null) {
                firstHeader.setBackgroundResource(R.drawable.background_gradient_item_cue_card);
                firstHeader.startAnimation(slideUpAnim);
                firstHeader.setVisibility(View.VISIBLE);
            }
            allDayView.setBackgroundResource(R.drawable.background_planner_header);
            allDayView.startAnimation(slideUpAnim);
            allDayView.setVisibility(View.VISIBLE);
        } else if (firstHeader != null) {
            firstHeader.setBackgroundResource(R.drawable.background_planner_header);
            firstHeader.startAnimation(slideUpAnim);
            firstHeader.setVisibility(View.VISIBLE);
            allDayView.setVisibility(View.GONE);
        }
    }

    private void hideHeadersView() {
        if (firstHeader != null && firstHeader.getVisibility() == View.VISIBLE) {
            firstHeader.setVisibility(View.INVISIBLE);
        }
        if (isAllDayNecessary && allDayView.getVisibility() == View.VISIBLE) {
            allDayView.setVisibility(View.INVISIBLE);
        }
    }

    public void addPlannerItems(List<AbstractFlexibleItem> items) {
        adapter.addItems(TOP_OF_LIST, items);
    }

    public void onItemClick(int position) {
        AbstractFlexibleItem item = adapter.getItem(position);
        if (item instanceof PlannerProductItem) {
            PlannerProductItem productItem = (PlannerProductItem) item;
            BaseActivity activity = getActivity();
            if (activity == null) {
                return;
            }
            activity.startActivity(ProductDetailActivity.getIntent(activity,
                    productItem.getPlannerProductModel().getProductId()));
        }
    }

    public void showAllDayLayout() {
        isAllDayNecessary = true;
    }

    public void isShowingItems(int visibleItemCount) {
        if (initialized) {
            return;
        }
        itemCount = visibleItemCount;
        initItemsWithMargin();
        initialized = true;
    }
}
