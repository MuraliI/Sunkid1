package com.rcl.excalibur.mvp.view;


import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v7.widget.LinearLayoutManager;
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
import com.rcl.excalibur.fragments.PlannerFragment;
import com.rcl.excalibur.mvp.view.base.FragmentView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import eu.davidea.flexibleadapter.FlexibleAdapter;
import eu.davidea.flexibleadapter.items.AbstractFlexibleItem;
import timber.log.Timber;

public class PlannerView extends FragmentView<PlannerFragment, Void, Void> {
    private static final int TOP_OF_LIST = 0;
    private static final int NO_PEEK_HEIGHT = 0;
    private static final int NO_MARGIN = 0;
    private static final int MAX_OFFSET = 1;
    private static final float OFFSET_80 = 0.8f;

    @Bind(R.id.recycler_view) RecyclerView recyclerView;
    @Bind(R.id.layout_planner_all_day) View allDayView;
    @Bind(R.id.layout_planner_container) LinearLayout containerLayout;

    private FlexibleAdapter<AbstractFlexibleItem> adapter;

    private LinearLayout firstHeader;
    private LinearLayoutManager linearLayoutManager;
    private BottomSheetBehavior bottomSheetBehavior;
    private Animation slideUpAnim;

    private Animation animationGoIn;

    private boolean isAllDayNecessary;
    private boolean isExpanded;
    private boolean initialized;
    private boolean bottomSheetIsSliding;

    private int initHorizontalMargin = -1;
    private int initVerticalMargin = -1;
    private int peekHeight;

    public PlannerView(PlannerFragment fragment) {
        super(fragment);
        ButterKnife.bind(this, fragment.getView());
    }

    public void init() {
        final PlannerFragment fragment = getFragment();
        if (fragment == null) {
            return;
        }
        Resources resources = fragment.getResources();
        initHorizontalMargin = resources.getDimensionPixelSize(R.dimen.planner_item_init_horizontal_margin);
        initVerticalMargin = resources.getDimensionPixelSize(R.dimen.planner_item_init_vertical_margin);
        peekHeight = resources.getDimensionPixelSize(R.dimen.planner_peek_height);

        adapter = new FlexibleAdapter<>(null, fragment, true);
        adapter.setDisplayHeadersAtStartUp(true).setStickyHeaders(true);
        linearLayoutManager = new LinearLayoutManager(fragment.getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);
        recyclerView.addOnChildAttachStateChangeListener(new RecyclerView.OnChildAttachStateChangeListener() {
            @Override
            public void onChildViewAttachedToWindow(View view) {
                if (!bottomSheetIsSliding) {
                    setInitialViewState(view);
                    initialized = true;
                }
            }

            @Override
            public void onChildViewDetachedFromWindow(View view) {
                // No op
            }
        });
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
                        if (!isExpanded && newState == BottomSheetBehavior.STATE_EXPANDED) {
                            isExpanded = true;
                            bottomSheetBehavior.setPeekHeight(NO_PEEK_HEIGHT);
                        } else if (isExpanded && newState == BottomSheetBehavior.STATE_COLLAPSED) {
                            isExpanded = false;
                            bottomSheetIsSliding = false;

                            resetItemsToInitialState();

                            bottomSheetBehavior.setPeekHeight(peekHeight);
                            containerLayout.startAnimation(animationGoIn);
                        }

                        // TODO: Remove before send PR, this is only to know the current state of BS
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
                        bottomSheetIsSliding = true;

                        if (slideOffset <= 0.1 && isExpanded) {
                            hideHeadersView();
                        }
                        if (isExpanded) {
                            return;
                        }

                        int visibleChildren = linearLayoutManager.findLastVisibleItemPosition();
                        for (int i = 1; i <= visibleChildren; i++) {
                            View view = recyclerView.getLayoutManager().findViewByPosition(i);
                            int verticalMargin = getMargin(slideOffset, initVerticalMargin);
                            int horizontalMargin = getMargin(slideOffset, initHorizontalMargin);
                            if (verticalMargin <= 0 && horizontalMargin <= 0) {
                                showHeadersView();
                            }
                            resizeItemView(view, verticalMargin, horizontalMargin);

                            if (slideOffset == MAX_OFFSET) {
                                changeSeparatorVisibility(view, View.VISIBLE);
                            }

                            if (slideOffset >= OFFSET_80) {
                                setItemViewBackground(view, R.drawable.background_gradient_item_cue_card);
                            } else {
                                setItemViewBackground(view, R.drawable.background_rounded_cue_card);
                            }
                        }
                    }
                }
        );
    }

    private int getMargin(float slideOffset, int marginValue) {
        return Math.round((1 - slideOffset) * marginValue);
    }

    private void resetItemsToInitialState() {
        int visibleChildren = linearLayoutManager.findLastVisibleItemPosition();
        for (int i = 0; i <= visibleChildren; i++) {
            View view = recyclerView.getLayoutManager().findViewByPosition(i);
            if (view == null) {
                return;
            }
            if (adapter.isHeader(adapter.getItem(i))) {
                firstHeader = (LinearLayout) view.findViewById(R.id.layout_planner_header_container);
                firstHeader.setVisibility(View.INVISIBLE);
            } else {
                setInitialViewState(recyclerView.getLayoutManager().findViewByPosition(i));
            }

        }
        hideHeadersView();
    }

    private void setInitialViewState(View view) {
        // TODO: improve this
        if (view.findViewById(R.id.layout_planner_header_container) != null) {
            view.setVisibility(View.INVISIBLE);
        } else {
            resizeItemView(view, initVerticalMargin, initHorizontalMargin);
            setItemViewBackground(view, R.drawable.background_rounded_cue_card);
            changeSeparatorVisibility(view, View.INVISIBLE);
        }
    }

    private void resizeItemView(View view, int verticalMargin, int horizontalMargin) {
        if (view == null) {
            return;
        }

        ViewGroup.MarginLayoutParams marginLayout = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        marginLayout.setMargins(horizontalMargin, NO_MARGIN, horizontalMargin, verticalMargin);

        if (initialized) {
            view.requestLayout();
        }
    }

    private void setItemViewBackground(View view, int imageResource) {
        if (view == null) {
            return;
        }
        view.setBackgroundResource(imageResource);
    }

    private void changeSeparatorVisibility(View parent, int visibility) {
        if (parent == null) {
            return;
        }
        View separator = ButterKnife.findById(parent, R.id.view_planner_item_separator);
        if (separator != null) {
            separator.setVisibility(visibility);
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
}
