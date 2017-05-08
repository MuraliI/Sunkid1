package com.rcl.excalibur.mvp.view;

import android.content.res.Resources;
import android.support.annotation.DimenRes;
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
import com.rcl.excalibur.activity.DeckMapActivity;
import com.rcl.excalibur.activity.ProductDetailActivity;
import com.rcl.excalibur.adapters.planner.PlannerAdapter;
import com.rcl.excalibur.adapters.planner.abstractitem.PlannerHeader;
import com.rcl.excalibur.adapters.planner.abstractitem.PlannerProductItem;
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

import static butterknife.ButterKnife.findById;

public class PlannerView extends FragmentView<PlannerFragment, Integer, Void> {
    private static final float OFFSET_OVER_95_PERCENT = 0.95f;
    private static final int ADD_DURATION_MILLIS = 150;
    private static final int TOP_OF_LIST = 0;
    private static final int NO_MARGIN = 0;

    @BindView(R.id.recycler_view) RecyclerView recyclerView;
    @BindView(R.id.layout_planner_container) LinearLayout containerLayout;
    @BindView(R.id.progress_service_call_planner) View progressBar;
    @BindView(R.id.image_ship_invisible) FrameLayout imageShipInvisible;
    @BindView(R.id.text_arriving_debarking_time) TextView shipArrivingDebarkingLabel;

    private PlannerAdapter adapter;

    private LinearLayoutManager linearLayoutManager;
    private BottomSheetBehavior bottomSheetBehavior;

    private Animation slideUpAnimation;
    private Animation animationGoIn;

    private Observer<PlannerHeader> expandCollapseObserver;
    private Observer<View> attachedObserver;
    private Observer<Float> slideObserver;
    private Observer<Integer> stateChangeObserver;

    private List<View> headerViewList;

    private PlannerProductItemComparator productItemComparator;

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
                onAttachedNext(view);
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
                        onStateChangeNext(newState);
                    }

                    @Override
                    public void onSlide(@NonNull View bottomSheet, float slideOffset) {
                        onSlideNext(slideOffset);
                    }
                }
        );
    }

    public void setRecyclerViewBackground(int backgroundRes) {
        PlannerFragment fragment = getFragment();
        if (fragment == null) {
            return;
        }
        recyclerView.setBackgroundResource(backgroundRes);
    }

    private boolean isIndexAHeader(int index) {
        return adapter.isHeader(adapter.getItem(index));
    }

    public void resetItemsToInitialState() {
        int visibleChildren = linearLayoutManager.findLastVisibleItemPosition();
        for (int i = 0; i <= visibleChildren; i++) {
            View view = recyclerView.getLayoutManager().findViewByPosition(i);
            if (view != null) {
                setInitialViewState(view);
            }
        }
    }

    public void setInitialViewState(View view) {
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
        setRoundedImageRadius(view, R.dimen.zero_radius);
    }

    private void resizeItemView(View view, int verticalMargin, int horizontalMargin) {
        if (view != null) {
            ViewGroup.MarginLayoutParams marginLayout = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
            marginLayout.setMargins(horizontalMargin, NO_MARGIN, horizontalMargin, verticalMargin);
        }
    }

    private void setItemViewBackground(View view, int imageResource) {
        if (view != null) {
            view.setBackgroundResource(imageResource);
        }
    }

    private void resizeImage(View parent, int margin) {
        if (parent != null) {
            RoundedImageView image = findById(parent, R.id.image_itinerary_product_picture);
            if (image != null) {
                ViewGroup.MarginLayoutParams marginLayout = (ViewGroup.MarginLayoutParams) image.getLayoutParams();
                marginLayout.setMargins(NO_MARGIN, margin, margin, margin);
            }
        }
    }

    private void requestLayoutView(@NonNull View view) {
        view.requestLayout();
    }

    private void changeSeparatorVisibility(View parent, int visibility) {
        if (parent != null) {
            View separator = findById(parent, R.id.view_planner_separator_line);
            if (separator != null) {
                separator.setVisibility(visibility);
            }
        }
    }

    public void showHeadersView() {
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
            onExpandCollapseNext((PlannerHeader) item);
        }
    }

    public void addPlannerItems(List<IFlexible> items) {
        adapter.addItems(TOP_OF_LIST, items);
        containerLayout.setVisibility(View.VISIBLE);
    }

    public void addItemToSection(ISectionable itemToAdd, IHeader header) {
        adapter.addItemToSection(itemToAdd, header, productItemComparator);
    }

    public void removeItemsFromSection(IHeader header) {
        adapter.removeItemsFromSection(header);
    }

    public void scrollToHeader(IHeader header) {
        int headerPosition = adapter.getGlobalPositionOf(header);
        recyclerView.smoothScrollToPosition(headerPosition);
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
        ActivityUtils.startActivity(activity, DeckMapActivity.getIntent(activity, null));
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

    @OnClick(R.id.text_arriving_debarking_time)
    void showVoyageMap() {
        if (getActivity() != null) {
//            ((TriptychHomeActivity) getActivity()).goToVoyageActivity();
        }
    }

    public void calculateItemMargins(float slideOffset, int imageMargin, int verticalMargin, int horizontalMargin) {
        int visibleChildren = linearLayoutManager.findLastVisibleItemPosition();

        for (int i = 0; i <= visibleChildren; i++) {
            if (isIndexAHeader(i)) {
                continue;
            }

            View itemView = linearLayoutManager.findViewByPosition(i);
            if (itemView == null) {
                return;
            }

            resizeItemView(itemView, verticalMargin, horizontalMargin);
            resizeImage(itemView, imageMargin);
            requestLayoutView(itemView);

            setRoundedImageRadius(itemView, R.dimen.default_radius);

            if (slideOffset >= OFFSET_OVER_95_PERCENT) {
                changeSeparatorVisibility(itemView, View.VISIBLE);
                setItemViewBackground(itemView, R.drawable.background_cue_card);
            } else {
                setItemViewBackground(itemView, R.drawable.background_rounded_cue_card);
            }
        }
    }

    private void setRoundedImageRadius(View parent, @DimenRes int radius) {
        RoundedImageView imageView = findById(parent, R.id.image_itinerary_product_picture);
        if (imageView != null) {
            imageView.setRadius(radius);
        }
    }

    public int getFirstItemPosition() {
        return linearLayoutManager.findFirstCompletelyVisibleItemPosition();
    }

    private int getVerticalLocationOnScreen(@NonNull View view) {
        int[] location = new int[2];
        view.getLocationOnScreen(location);
        return location[1];
    }

    public int getVerticalLocationOnScreen(int position) {
        View view = linearLayoutManager.findViewByPosition(position);
        int verticalPosition = -1;
        if (view != null) {
            verticalPosition = getVerticalLocationOnScreen(view);
        }
        return verticalPosition;
    }

    @Nullable
    public PlannerProductItem getNextItem(int index) {
        if (!isIndexAHeader(index)) {
            return (PlannerProductItem) adapter.getItem(index);
        }
        return null;
    }

    public void updateHeader(IHeader header) {
        adapter.updateItem(header, null);
    }

    public void setAttachedObserver(Observer<View> attachedObserver) {
        this.attachedObserver = attachedObserver;
    }

    private void onAttachedNext(View itemView) {
        if (attachedObserver != null) {
            attachedObserver.onNext(itemView);
        }
    }

    // COLLAPSE | EXPAND - OBSERVER

    private void onExpandCollapseNext(PlannerHeader header) {
        if (expandCollapseObserver != null) {
            expandCollapseObserver.onNext(header);
        }
    }

    public void setExpandCollapseObserver(Observer<PlannerHeader> expandCollapseObserver) {
        this.expandCollapseObserver = expandCollapseObserver;
    }

    // SLIDE OBSERVER

    private void onSlideNext(Float slideOffset) {
        if (slideObserver != null) {
            slideObserver.onNext(slideOffset);
        }
    }

    public void setSlideObserver(Observer<Float> slideObserver) {
        this.slideObserver = slideObserver;
    }

    // STATE CHANGE OBSERVER

    private void onStateChangeNext(Integer newState) {
        if (stateChangeObserver == null) {
            return;
        }
        stateChangeObserver.onNext(newState);
    }

    public void setStateChangeObserver(Observer<Integer> stateChangeObserver) {
        this.stateChangeObserver = stateChangeObserver;
    }

    public int getInitImageMargin() {
        return initImageMargin;
    }

    public int getInitHorizontalMargin() {
        return initHorizontalMargin;
    }

    public int getInitVerticalMargin() {
        return initVerticalMargin;
    }

    public int getPeekHeight() {
        return peekHeight;
    }

    public void setShipArrivingDebarkingAlpha(float alpha) {
        if (shipArrivingDebarkingLabel != null) {
            shipArrivingDebarkingLabel.setAlpha(alpha);
        }
    }

    public void setShipArrivingDebarkingVisibility(int visibility) {
        if (shipArrivingDebarkingLabel != null) {
            shipArrivingDebarkingLabel.setVisibility(visibility);
        }
    }

    public void setBottomSheetPeekHeight(int peekHeight) {
        if (bottomSheetBehavior != null) {
            bottomSheetBehavior.setPeekHeight(peekHeight);
        }
    }

    public void scrollToTopOfList() {
        if (recyclerView != null) {
            recyclerView.scrollToPosition(TOP_OF_LIST);
        }
    }

    public void animateContainerLayout() {
        containerLayout.startAnimation(animationGoIn);
    }
}
