package com.rcl.excalibur.mvp.view;


import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v4.util.Pair;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.rcl.excalibur.R;
import com.rcl.excalibur.activity.BaseActivity;
import com.rcl.excalibur.activity.ProductDetailActivity;
import com.rcl.excalibur.adapters.planner.abstractitem.PlannerProductItem;
import com.rcl.excalibur.fragments.PlannerFragment;
import com.rcl.excalibur.model.EventModel;
import com.rcl.excalibur.mvp.view.base.FragmentView;
import com.rcl.excalibur.utils.ActivityUtils;
import com.rcl.excalibur.utils.RoundedImageView;
import com.rcl.excalibur.utils.DayInformationUtils;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import eu.davidea.flexibleadapter.FlexibleAdapter;
import eu.davidea.flexibleadapter.items.AbstractFlexibleItem;

public class PlannerView extends FragmentView<PlannerFragment, Void, Void> {
    private static final int TOP_OF_LIST = 0;
    private static final int NO_PEEK_HEIGHT = 0;
    private static final int NO_MARGIN = 0;
    private static final float OFFSET_OVER_95_PERCENT = 0.95f;
    private static final float MAX_SLIDE_OFFSET = 1.0f;

    @Bind(R.id.recycler_view) RecyclerView recyclerView;
    @Bind(R.id.layout_planner_all_day) View allDayView;
    @Bind(R.id.layout_planner_container) LinearLayout containerLayout;
    @Bind(R.id.progress_service_call_planner) View progressBar;
    @Bind(R.id.text_arrivin_debarking_time) TextView shipArrivingDebanrkingLabel;

    private FlexibleAdapter<AbstractFlexibleItem> adapter;

    private LinearLayout firstHeader;
    private LinearLayoutManager linearLayoutManager;
    private BottomSheetBehavior bottomSheetBehavior;
    private Animation slideUpAnimation;
    private Animation slideUpAllDayAnimation;
    private Animation animationGoIn;

    private boolean isAllDayNecessary;
    private boolean isExpanded;
    private boolean initialized;
    private boolean bottomSheetIsSliding;

    private int initHorizontalMargin = -1;
    private int initVerticalMargin = -1;
    private int initImageMargin;
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
        initImageMargin = resources.getDimensionPixelSize(R.dimen.margin_small);
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
                    resetItemsToInitialState();
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
        slideUpAnimation = AnimationUtils.loadAnimation(getContext(), R.anim.view_slide_up);
        slideUpAllDayAnimation = AnimationUtils.loadAnimation(getContext(), R.anim.view_slide_up_all_day);
    }

    public void initBottomSheetBehavior() {
        bottomSheetBehavior = BottomSheetBehavior.from(containerLayout);
        bottomSheetBehavior.setBottomSheetCallback(
                new BottomSheetBehavior.BottomSheetCallback() {
                    @Override
                    public void onStateChanged(@NonNull View bottomSheet, int newState) {
                        switch (newState) {
                            case BottomSheetBehavior.STATE_EXPANDED:
                                if (!isExpanded) {
                                    setBottomSheetExpandedState();
                                }
                                break;
                            case BottomSheetBehavior.STATE_COLLAPSED:
                                if (isExpanded) {
                                    setBottomSheetCollapsingState();
                                }
                                break;
                            default:
                                break;
                        }
                    }

                    @Override
                    public void onSlide(@NonNull View bottomSheet, float slideOffset) {
                        bottomSheetIsSliding = true;

                        if (shipArrivingDebanrkingLabel != null) {
                            shipArrivingDebanrkingLabel.setAlpha(1.0f - slideOffset);
                        }

                        if (isExpanded) {
                            return;
                        }
                        calculateItemMargins(slideOffset);
                    }
                }
        );
    }

    private void setBottomSheetExpandedState() {
        isExpanded = true;

        bottomSheetBehavior.setPeekHeight(NO_PEEK_HEIGHT);
        calculateItemMargins(MAX_SLIDE_OFFSET);
        showHeadersView();
    }

    private void setBottomSheetCollapsingState() {
        isExpanded = false;

        resetItemsToInitialState();

        bottomSheetBehavior.setPeekHeight(peekHeight);
        containerLayout.startAnimation(animationGoIn);
        hideHeadersView();
    }

    private void calculateItemMargins(float slideOffset) {
        int visibleChildren = linearLayoutManager.findLastVisibleItemPosition();
        for (int i = 1; i <= visibleChildren; i++) {
            View view = recyclerView.getLayoutManager().findViewByPosition(i);
            int verticalMargin = getMargin(slideOffset, initVerticalMargin);
            int horizontalMargin = getMargin(slideOffset, initHorizontalMargin);
            resizeItemView(view, verticalMargin, horizontalMargin);

            int imageMargin = Math.round(slideOffset * initImageMargin);
            resizeImage(view, imageMargin);

            RoundedImageView imageView = ButterKnife.findById(view, R.id.image_itinerary_product_picture);
            imageView.setRadius(R.dimen.default_radius);

            if (slideOffset >= OFFSET_OVER_95_PERCENT) {
                changeSeparatorVisibility(view, View.VISIBLE);
                setItemViewBackground(view, R.drawable.background_cue_card);
            } else {
                setItemViewBackground(view, R.drawable.background_rounded_cue_card);
            }
        }
    }

    private int getMargin(float slideOffset, int marginValue) {
        return (int) ((MAX_SLIDE_OFFSET - slideOffset) * marginValue);
    }

    private void resetItemsToInitialState() {
        int visibleChildren = linearLayoutManager.findLastVisibleItemPosition();
        for (int i = 0; i <= visibleChildren; i++) {
            View view = recyclerView.getLayoutManager().findViewByPosition(i);
            if (view != null) {
                if (adapter.isHeader(adapter.getItem(i))) {
                    firstHeader = (LinearLayout) view.findViewById(R.id.layout_planner_header_container);
                    firstHeader.setVisibility(View.INVISIBLE);
                } else {
                    setInitialViewState(recyclerView.getLayoutManager().findViewByPosition(i));
                }
            }
        }
        hideHeadersView();
    }

    private void setInitialViewState(View view) {
        // FIXME: improve this
        if (view.findViewById(R.id.layout_planner_header_container) != null) {
            view.setVisibility(View.INVISIBLE);
        } else {
            resizeItemView(view, initVerticalMargin, initHorizontalMargin);
            resizeImage(view, NO_MARGIN);
            setItemViewBackground(view, R.drawable.background_rounded_cue_card);
            changeSeparatorVisibility(view, View.GONE);
            RoundedImageView imageView = ButterKnife.findById(view, R.id.image_itinerary_product_picture);
            imageView.setRadius(R.dimen.zero_radius);
        }
    }

    private void resizeItemView(View view, int verticalMargin, int horizontalMargin) {
        if (view != null) {
            ViewGroup.MarginLayoutParams marginLayout = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
            marginLayout.setMargins(horizontalMargin, NO_MARGIN, horizontalMargin, verticalMargin);

            if (initialized) {
                view.requestLayout();
            }
        }
    }

    private void setItemViewBackground(View view, int imageResource) {
        if (view != null) {
            view.setBackgroundResource(imageResource);
        }
    }

    private void resizeImage(View parent, int margin) {
        if (parent != null) {
            RoundedImageView image = ButterKnife.findById(parent, R.id.image_itinerary_product_picture);
            if (image != null) {
                ViewGroup.MarginLayoutParams marginLayout = (ViewGroup.MarginLayoutParams) image.getLayoutParams();
                marginLayout.setMargins(NO_MARGIN, margin, margin, margin);

                if (initialized) {
                    image.requestLayout();
                }
            }
        }
    }

    private void changeSeparatorVisibility(View parent, int visibility) {
        if (parent != null) {
            View separator = ButterKnife.findById(parent, R.id.view_planner_item_separator);
            if (separator != null) {
                separator.setVisibility(visibility);
            }
        }
    }

    private void showHeadersView() {
        slideUpAnimation.reset();
        if (isAllDayNecessary) {
            if (firstHeader != null) {
                firstHeader.setBackgroundResource(R.drawable.background_cue_card);
                firstHeader.startAnimation(slideUpAnimation);
                firstHeader.setVisibility(View.VISIBLE);
            }
            allDayView.setBackgroundResource(R.drawable.background_planner_header);
            allDayView.startAnimation(slideUpAllDayAnimation);
            allDayView.setVisibility(View.VISIBLE);
        } else if (firstHeader != null) {
            firstHeader.setBackgroundResource(R.drawable.background_planner_header);
            firstHeader.startAnimation(slideUpAnimation);
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
        containerLayout.setVisibility(View.VISIBLE);
    }

    public void onItemClick(int position) {
        AbstractFlexibleItem item = adapter.getItem(position);
        if (item instanceof PlannerProductItem) {
            PlannerProductItem productItem = (PlannerProductItem) item;
            BaseActivity activity = getActivity();
            if (activity == null) {
                return;
            }

            View sharedItemView = recyclerView
                    .getLayoutManager()
                    .findViewByPosition(position)
                    .findViewById(R.id.image_itinerary_product_picture);

            ActivityUtils.startActivityWithSharedElement(getActivity(),
                    ProductDetailActivity.getIntent(activity, productItem.getPlannerProductModel().getProductId()),
                    sharedItemView,
                    getActivity().getString(R.string.shared_element_transition_name));
        }
    }

    public void showAllDayLayout() {
        isAllDayNecessary = true;
        allDayView.setVisibility(View.INVISIBLE);
    }

    public void showProgressBar(boolean show) {
        progressBar.setVisibility(show ? View.VISIBLE : View.GONE);
    }

    public void addArrivingDebanrkingValues(@NonNull List<EventModel> events, int day) {
        if (events == null) {
            shipArrivingDebanrkingLabel.setText(getActivity().getResources().getString(R.string.empty_string));
            shipArrivingDebanrkingLabel.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
        } else {
            Pair<String, Integer> stringIntegerPair = DayInformationUtils.getArrivalDebarkDescription(events, day);
            shipArrivingDebanrkingLabel.setText(stringIntegerPair.first);
            shipArrivingDebanrkingLabel.setCompoundDrawablesWithIntrinsicBounds(stringIntegerPair.second, 0, 0, 0);
        }
    }
}
