package com.rcl.excalibur.mvp.view;


import android.app.Activity;
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
import com.rcl.excalibur.adapters.planner.stickylib.PlannerHeader;
import com.rcl.excalibur.custom.itinerary.RoyalLinearLayoutManager;
import com.rcl.excalibur.fragments.PlannerFragment;
import com.rcl.excalibur.mvp.view.base.FragmentView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import eu.davidea.flexibleadapter.FlexibleAdapter;
import eu.davidea.flexibleadapter.items.AbstractFlexibleItem;
import timber.log.Timber;

public class PlannerView extends FragmentView<PlannerFragment, Void, Void> implements RoyalLinearLayoutManager.OnFirstTimeListener {

    @Bind(R.id.recycler_view) RecyclerView recyclerView;
    @Bind(R.id.layout_planner_container) LinearLayout containerLayout;

    private FlexibleAdapter<AbstractFlexibleItem> adapter;

    private BottomSheetBehavior bottomSheetBehavior;

    private Animation animationGoOut, animationGoIn;

    private int initHorizontalMargin = -1;
    private int initVerticalMargin = -1;

    public PlannerView(PlannerFragment fragment) {
        super(fragment);
        ButterKnife.bind(this, fragment.getView());
    }

    public void init() {
        final Activity activity = getActivity();
        if (activity == null) {
            return;
        }
        adapter = new FlexibleAdapter<>(null, null, true);
        adapter.setDisplayHeadersAtStartUp(false).setStickyHeaders(true);
        recyclerView.setLayoutManager(new RoyalLinearLayoutManager(activity, this));
        recyclerView.setAdapter(adapter);

        Resources resources = activity.getResources();
        initHorizontalMargin = resources.getDimensionPixelSize(R.dimen.planner_item_init_horizontal_margin);
        initVerticalMargin = resources.getDimensionPixelSize(R.dimen.planner_item_init_vertical_margin);

        initBS();

        animationGoOut = AnimationUtils.loadAnimation(getContext(), R.anim.go_out);
        animationGoOut.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                initItems();
                containerLayout.startAnimation(animationGoIn);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        animationGoIn = AnimationUtils.loadAnimation(getContext(), R.anim.go_in);
        animationGoIn.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    private boolean isExpanded;

    public void initBS() {
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
                            /*bottomSheetBehavior.setPeekHeight(BottomSheetBehavior.PEEK_HEIGHT_AUTO);

                            initItems();
                            isExpanded = false;

                            bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);*/

                            //initItems();
                            initItems();
                            bottomSheetBehavior.setPeekHeight(BottomSheetBehavior.PEEK_HEIGHT_AUTO);
                            isExpanded = false;
                            containerLayout.startAnimation(animationGoIn);
                        }

                        switch (newState) {
                            case BottomSheetBehavior.STATE_DRAGGING:
                                Timber.i("STATE_DRAGGING");
                                break;
                            case BottomSheetBehavior.STATE_COLLAPSED:
                                Timber.i("STATE_COLLAPSED");
                                break;
                            case BottomSheetBehavior.STATE_EXPANDED:
                                Timber.i("STATE_EXPANDED");
                                break;
                            case BottomSheetBehavior.STATE_HIDDEN:
                                Timber.i("STATE_HIDDEN");
                                break;
                            case BottomSheetBehavior.STATE_SETTLING:
                                Timber.i("STATE_SETTLING");
                                break;
                            default:
                                break;
                        }
                    }

                    @Override
                    public void onSlide(@NonNull View bottomSheet, float slideOffset) {
                        Timber.i("slideOffset = %s", slideOffset);

                        if (slideOffset >= 0.7) {
                            //adapter.showAllHeaders();
                            PlannerHeader header = (PlannerHeader) adapter.getSectionHeader(0);
                            if (header != null) {
                                Timber.i("TITLE %s ", header.getTitle());
                            }
                        } else {
                            //adapter.hideAllHeaders();
                        }

                        //animate.animate().scaleX(1 - slideOffset).scaleY(1 - slideOffset).setDuration(0).start();
                        //showListButton.animateToPointF().scaleX(1 - slideOffset).scaleY(1 - slideOffset).setDuration(0).start();

                        // int verticalMargin = Math.round((1 - slideOffset) * initVerticalMargin);
                        //int horizontalMargin = Math.round((1 - slideOffset) * initHorizontalMargin);

                        if (isExpanded) {
                            return;
                        }

                        for (int i = 0; i < itemCount; i++) {
                            View view = recyclerView.getLayoutManager().findViewByPosition(i);
                            int verticalMargin = getMargin(slideOffset, initVerticalMargin);
                            int horizontalMargin = getMargin(slideOffset, initHorizontalMargin);
                            //Timber.i("verticalMargin %s, horizontalMargin %s", verticalMargin, horizontalMargin);
                            resizeItemView(view, verticalMargin, horizontalMargin);
                        }
                    }
                }
        );
    }

    private int getMargin(float slideOffset, int marginValue) {
        return Math.round((1 - slideOffset) * marginValue);
    }

    private void initItems() {
        for (int i = 0; i < itemCount; i++) {
            View view = recyclerView.getLayoutManager().findViewByPosition(i);
            resizeItemView(view, initVerticalMargin, initHorizontalMargin);
        }
        //recyclerView.requestLayout();
    }

    private void resizeItemView(View view, int verticalMargin, int horizontalMargin) {
        if (view == null) {
            return;
        }

        ViewGroup.MarginLayoutParams marginLayout = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        marginLayout.setMargins(horizontalMargin, verticalMargin, horizontalMargin, verticalMargin);
        //view.requestLayout();

        if (initialized) {
            view.requestLayout();
        }
    }

    public void addPlannerItems(List<AbstractFlexibleItem> items) {
        boolean added = adapter.addItems(0, items);

        Timber.i("initHorizontalMargin %s", initHorizontalMargin);
        Timber.i("initVerticalMargin %s", initVerticalMargin);

        Timber.i("added %s", added);
        Timber.i("adapter count %s", adapter.getItemCount());
        Timber.i("manager count %s", recyclerView.getLayoutManager().getChildCount());
    }

    private int itemCount = 0;
    private boolean initialized = false;

    @Override
    public void isShowingItems(int visibleItemCount) {
        if (initialized) {
            return;
        }

        itemCount = visibleItemCount;
        initItems();

        initialized = true;
    }
}
