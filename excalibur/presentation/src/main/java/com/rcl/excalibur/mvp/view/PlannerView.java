package com.rcl.excalibur.mvp.view;

import android.content.res.Resources;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v4.util.Pair;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.OnScrollListener;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.rcl.excalibur.R;
import com.rcl.excalibur.activity.BaseActivity;
import com.rcl.excalibur.activity.ProductDeckMapActivity;
import com.rcl.excalibur.activity.ProductDetailActivity;
import com.rcl.excalibur.adapters.planner.PlannerAdapter;
import com.rcl.excalibur.adapters.planner.abstractitem.PlannerProductItem;
import com.rcl.excalibur.custom.view.TopRoundedFrameLayout;
import com.rcl.excalibur.fragments.PlannerFragment;
import com.rcl.excalibur.mvp.view.base.FragmentView;
import com.rcl.excalibur.utils.ActivityUtils;
import com.rcl.excalibur.utils.RoundedImageView;
import com.rcl.excalibur.utils.comparator.PlannerProductItemComparator;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import eu.davidea.flexibleadapter.items.IFlexible;
import eu.davidea.flexibleadapter.items.IHeader;
import eu.davidea.flexibleadapter.items.ISectionable;
import io.reactivex.Observer;

public class PlannerView extends FragmentView<PlannerFragment, Integer, Void> {
    private static final float OFFSET_OVER_95_PERCENT = 0.95f;
    private static final float MAX_SLIDE_OFFSET = 1.0f;
    private static final int DELAY_MILLIS_COLLAPSE = 200;
    private static final int DELAY_MILLIS_SCROLL = 100;
    private static final int ADD_DURATION_MILLIS = 150;
    private static final int NO_PEEK_HEIGHT = 0;
    private static final int TOP_OF_LIST = 0;
    private static final int NO_MARGIN = 0;

    @BindView(R.id.recycler_view) RecyclerView recyclerView;
    @BindView(R.id.layout_planner_container) LinearLayout containerLayout;
    @BindView(R.id.progress_service_call_planner) View progressBar;
    @BindView(R.id.image_ship_invisible) FrameLayout imageShipInvisible;
    @BindView(R.id.text_arriving_debarking_time) TextView shipArrivingDebarkingLabel;
    @BindView(R.id.layout_planner_recycler_container) TopRoundedFrameLayout recyclerContainerLayout;

    private PlannerAdapter adapter;

    private LinearLayoutManager linearLayoutManager;
    private BottomSheetBehavior bottomSheetBehavior;
    private Animation slideUpAnimation;
    private Animation animationGoIn;

    private Observer<List<IHeader>> expandCollapseObserver;
    private List<Integer> itemsToRemove;
    private List<View> headerViewList;

    private PlannerProductItemComparator productItemComparator;

    private Handler handler;

    private boolean isExpanded;
    private boolean initialized;
    private boolean bottomSheetIsSliding;

    private int headerHeight;
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
        headerHeight = resources.getDimensionPixelSize(R.dimen.height_planner_header);

        handler = new Handler();
        itemsToRemove = new ArrayList<>();
        headerViewList = new ArrayList<>();
        productItemComparator = new PlannerProductItemComparator();

        DefaultItemAnimator itemAnimator = new DefaultItemAnimator();
        itemAnimator.setAddDuration(ADD_DURATION_MILLIS);

        adapter = new PlannerAdapter(null, fragment, true);
        adapter.setDisplayHeadersAtStartUp(true).setStickyHeaders(true);
        linearLayoutManager = new LinearLayoutManager(fragment.getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);
        recyclerView.setItemAnimator(itemAnimator);
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
                // Do Nothing...
            }
        });
        recyclerView.addOnScrollListener(new OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                onViewNext(getVerticalLocationOnScreen(recyclerView) + headerHeight);
            }
        });
    }

    public void initAnimation() {
        animationGoIn = AnimationUtils.loadAnimation(getContext(), R.anim.planner_preview_slide_in);
        slideUpAnimation = AnimationUtils.loadAnimation(getContext(), R.anim.view_slide_up);
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
                                    handler.postDelayed(() -> recyclerView.scrollToPosition(TOP_OF_LIST), DELAY_MILLIS_SCROLL);
                                    handler.postDelayed(() -> setBottomSheetCollapsingState(), DELAY_MILLIS_COLLAPSE);
                                }
                                break;
                            default:
                                break;
                        }
                    }

                    @Override
                    public void onSlide(@NonNull View bottomSheet, float slideOffset) {
                        bottomSheetIsSliding = true;

                        if (shipArrivingDebarkingLabel != null) {
                            shipArrivingDebarkingLabel.setAlpha(MAX_SLIDE_OFFSET - slideOffset);
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

        shipArrivingDebarkingLabel.setVisibility(View.GONE);

        bottomSheetBehavior.setPeekHeight(NO_PEEK_HEIGHT);
        calculateItemMargins(MAX_SLIDE_OFFSET);

        showHeadersView();

        setRecyclerViewBackground(R.drawable.background_rounded_top_planner_white);
    }

    private void setBottomSheetCollapsingState() {
        isExpanded = false;

        shipArrivingDebarkingLabel.setVisibility(View.VISIBLE);

        resetItemsToInitialState();

        bottomSheetBehavior.setPeekHeight(peekHeight);
        containerLayout.startAnimation(animationGoIn);

        setRecyclerViewBackground(R.drawable.background_rounded_top_planner_transparent);
    }

    private void setRecyclerViewBackground(int backgroundRes) {
        PlannerFragment fragment = getFragment();
        if (fragment == null) {
            return;
        }
        recyclerView.setBackgroundResource(backgroundRes);
    }

    private void calculateItemMargins(float slideOffset) {
        int visibleChildren = linearLayoutManager.findLastVisibleItemPosition();
        for (int i = 0; i <= visibleChildren; i++) {
            if (adapter.isHeader(adapter.getItem(i))) {
                continue;
            }

            View view = recyclerView.getLayoutManager().findViewByPosition(i);
            if (view == null) {
                return;
            }

            int verticalMargin = getMargin(slideOffset, initVerticalMargin);
            int horizontalMargin = getMargin(slideOffset, initHorizontalMargin);
            resizeItemView(view, verticalMargin, horizontalMargin);

            int imageMargin = Math.round(slideOffset * initImageMargin);
            resizeImage(view, imageMargin);

            RoundedImageView imageView = ButterKnife.findById(view, R.id.image_itinerary_product_picture);
            if (imageView != null) {
                imageView.setRadius(R.dimen.default_radius);
            }

            if (slideOffset >= OFFSET_OVER_95_PERCENT) {
                changeSeparatorVisibility(view, View.VISIBLE);
                setItemViewBackground(view, R.drawable.background_cue_card);
            } else {
                setItemViewBackground(view, R.drawable.background_rounded_cue_card);
            }
        }
    }

    private int getMargin(float slideOffset, int marginValue) {
        return Math.round((MAX_SLIDE_OFFSET - slideOffset) * marginValue);
    }

    private void resetItemsToInitialState() {
        int visibleChildren = linearLayoutManager.findLastVisibleItemPosition();
        for (int i = 0; i <= visibleChildren; i++) {
            View view = recyclerView.getLayoutManager().findViewByPosition(i);
            if (view != null) {
                setInitialViewState(view);
            }
        }
    }

    private void setInitialViewState(View view) {
        if (view.findViewById(R.id.layout_planner_header_container) != null) {
            if (!headerViewList.contains(view)) {
                headerViewList.add(view);
            }
            hideView(view);
            return;
        }

        resizeItemView(view, initVerticalMargin, initHorizontalMargin);
        resizeImage(view, NO_MARGIN);
        setItemViewBackground(view, R.drawable.background_rounded_cue_card);
        changeSeparatorVisibility(view, View.GONE);
        RoundedImageView imageView = ButterKnife.findById(view, R.id.image_itinerary_product_picture);
        if (imageView != null) {
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
            View separator = ButterKnife.findById(parent, R.id.view_planner_separator_line);
            if (separator != null) {
                separator.setVisibility(visibility);
            }
        }
    }

    private void showHeadersView() {
        slideUpAnimation.reset();

        for (View view : headerViewList) {
            if (view != null && view.getVisibility() == View.INVISIBLE) {
                view.startAnimation(slideUpAnimation);
                view.setVisibility(View.VISIBLE);
            }
        }
        headerViewList.clear();
    }

    private void hideView(View view) {
        if (view != null && view.getVisibility() == View.VISIBLE) {
            view.setVisibility(View.INVISIBLE);
        }
    }

    public void onItemClick(int position) {
        IFlexible item = adapter.getItem(position);
        if (!adapter.isHeader(item)) {
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
        } else {
            onExpandCollapseNext(adapter.getHeaderItems());
        }
    }

    public void addPlannerItems(List<IFlexible> items) {
        adapter.addItems(TOP_OF_LIST, items);
        containerLayout.setVisibility(View.VISIBLE);
    }

    public void addItemToSection(ISectionable itemToAdd, IHeader header) {
        itemsToRemove.add(adapter.addItemToSection(itemToAdd, header, productItemComparator));
    }

    public void removeItems() {
        recyclerView.smoothScrollToPosition(TOP_OF_LIST);
        adapter.removeItems(itemsToRemove);
        itemsToRemove.clear();
    }

    public void showProgressBar(boolean show) {
        progressBar.setVisibility(show ? View.VISIBLE : View.GONE);
    }

    public void setTextCompoundDrawableDayInfo(String text, int drawable) {
        shipArrivingDebarkingLabel.setText(text);
        shipArrivingDebarkingLabel.setCompoundDrawablesWithIntrinsicBounds(drawable, 0, 0, 0);
    }

    @OnClick(R.id.image_ship_invisible)
    void shipOnClick() {
        //Fixme temp onClick on Transparent ImageView
        final BaseActivity activity = getActivity();
        if (activity == null) {
            return;
        }
        ActivityUtils.startActivity(activity, ProductDeckMapActivity.getIntent(activity, null));
    }

    public void setShipInvisibleHeight(Pair<Integer, Integer> pair) {
        if (imageShipInvisible != null) {
            ViewGroup.LayoutParams params = imageShipInvisible.getLayoutParams();
            //Pair first is width, second height
            params.width = pair.first;
            params.height = pair.second;
            imageShipInvisible.setLayoutParams(params);
        }
    }

    public int getFirstItemPosition() {
        return linearLayoutManager.findFirstCompletelyVisibleItemPosition();
    }

    public View getViewItemAt(int position) {
        return linearLayoutManager.findViewByPosition(position);
    }

    public int getVerticalLocationOnScreen(@NonNull View view) {
        int[] location = new int[2];
        view.getLocationOnScreen(location);
        return location[1];
    }

    @Nullable
    public PlannerProductItem getNextItem(int index) {
        if (!adapter.isHeader(adapter.getItem(index))) {
            return (PlannerProductItem) adapter.getItem(index);
        }
        return null;
    }

    public void updateHeader() {
        adapter.notifyItemChanged(0);
    }

    public void updateHeader(IHeader header) {
        int headerIndex = adapter.getGlobalPositionOf(header);
        adapter.notifyItemChanged(headerIndex);
    }

    // COLLAPSE | EXPAND - OBSERVER

    private void onExpandCollapseNext(List<IHeader> headers) {
        if (expandCollapseObserver == null) {
            return;
        }
        expandCollapseObserver.onNext(headers);
    }

    public void setExpandCollapseObserver(Observer<List<IHeader>> expandCollapseObserver) {
        this.expandCollapseObserver = expandCollapseObserver;
    }
}
