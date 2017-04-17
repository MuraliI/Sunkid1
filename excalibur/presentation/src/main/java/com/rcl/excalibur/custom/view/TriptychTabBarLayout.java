package com.rcl.excalibur.custom.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v4.util.Pair;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.rcl.excalibur.R;

import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;
import static android.widget.ListPopupWindow.WRAP_CONTENT;


public class TriptychTabBarLayout extends ViewGroup implements ViewPager.OnPageChangeListener {
    private static final float TABS_WEIGHT = 0.5f;
    private ViewPager viewPager;
    private LinearLayout container;
    private View tabStrip;
    private View collapsibleView;
    private View collapsibleSibling;
    private int tabsPadding;
    private int currentSelectedItem = 0;
    private float scrollOffset = 0.0f;
    private int collapsibleWidth = -1;

    public TriptychTabBarLayout(Context context) {
        super(context);
        initialize(context, null, 0);
    }

    public TriptychTabBarLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        initialize(context, attrs, 0);
    }

    public TriptychTabBarLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initialize(context, attrs, defStyleAttr);
    }

    private void initialize(Context context, AttributeSet attrs, int defStyleAttr) {
        TypedArray typedArray = context.getTheme().obtainStyledAttributes(attrs, R.styleable.TriptychTabBarLayout, defStyleAttr, 0);
        int tabStripHeight;
        int tabStripColor;
        try {
            tabStripColor = typedArray.getColor(R.styleable.TriptychTabBarLayout_stripColor,
                    ContextCompat.getColor(context, R.color.black));
            tabStripHeight = (int) typedArray.getDimension(R.styleable.TriptychTabBarLayout_stripThickness, getResources()
                    .getDimension(R.dimen.triptych_strip_tickness));
            tabsPadding = (int) typedArray.getDimension(R.styleable.TriptychTabBarLayout_tabsPadding, 0);
        } finally {
            typedArray.recycle();
        }
        tabStrip = new View(context);
        tabStrip.setBackgroundColor(tabStripColor);
        addView(tabStrip, 0, new LayoutParams(LayoutParams.WRAP_CONTENT, tabStripHeight));
    }

    public void attachToViewPager(ViewPager viewPager) {
        this.viewPager = viewPager;
        viewPager.addOnPageChangeListener(this);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        container = new LinearLayout(getContext());
        container.setOrientation(LinearLayout.HORIZONTAL);
        container.setGravity(Gravity.CENTER_VERTICAL);
        View child = getChildAt(1);
        do {
            child.setOnClickListener(((View v) -> {
                for (int position = 0; position < container.getChildCount(); position++) {
                    View newCurrent = container.getChildAt(position);
                    if (newCurrent.equals(v)) {
                        viewPager.setCurrentItem(position);
                        break;
                    }
                }
            }));

            if (R.id.collapsible_tab == child.getId()) {
                collapsibleView = child.findViewById(R.id.date_picker_plans_tab);
                collapsibleSibling = child.findViewById(R.id.image_plans_tab);
            }
            removeView(child);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0, WRAP_CONTENT);
            params.weight = TABS_WEIGHT;
            child.setPadding(tabsPadding, tabsPadding, tabsPadding, tabsPadding);
            container.addView(child, params);
            child = getChildAt(1);
        }
        while (child != null);
        addView(container, MATCH_PARENT, WRAP_CONTENT);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //This will determine how height this layout is going to be
        int maxHeight = 0;
        //This will determine how broad this layout should be
        int widthUsed = 0;
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = getChildAt(i);

            measureChildWithMargins(child, widthMeasureSpec, 0, heightMeasureSpec, 0);

            int childHeight = child.getMeasuredHeight();
            int childWidth = child.getMeasuredWidth();

            maxHeight = childHeight > maxHeight ? childHeight : maxHeight;
            widthUsed += childWidth;
        }

        //We need to add the tabStrip height to the maximum layout height so it can be displayed
        maxHeight += tabStrip.getMeasuredHeight();

        //Set this viewgroup width and height
        setMeasuredDimension(resolveSize(widthUsed, widthMeasureSpec), resolveSize(maxHeight, heightMeasureSpec));

        //Get current child and calculate the tab strip width
        View currentSelectedChild = container.getChildAt(currentSelectedItem);
        int tabStripWidthMeasureSpec = MeasureSpec.makeMeasureSpec(currentSelectedChild.getMeasuredWidth(), MeasureSpec.EXACTLY);
        int tabStripHeightMeasureSpec = MeasureSpec.makeMeasureSpec(tabStrip.getMeasuredHeight(), MeasureSpec.EXACTLY);
        tabStrip.measure(tabStripWidthMeasureSpec, tabStripHeightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        layoutForChildren();
        layoutForTabStrip();
        layoutForCollapsible();
    }

    private void layoutForChildren() {
        int childCount = getChildCount();
        int dx = getWidth() / childCount;
        for (int i = 0; i < childCount; i++) {
            View child = getChildAt(i);
            if (!child.equals(tabStrip)) {
                int calculatedLeft = getLeft() + (i * dx) - (child.getMeasuredWidth() / 2);
                child.layout(calculatedLeft,
                        getTop() + tabStrip.getMeasuredHeight(),
                        child.getMeasuredWidth() + calculatedLeft,
                        child.getMeasuredHeight() + getTop());
            }
        }
    }

    private void layoutForTabStrip() {
        Pair<Integer, Integer> tabMeasures = getTabStartAndEnd();
        tabStrip.layout(tabMeasures.first,
                getTop(),
                tabMeasures.second,
                tabStrip.getMeasuredHeight());
    }

    private void layoutForCollapsible() {
        collapsibleView.layout(collapsibleView.getLeft(),
                collapsibleView.getTop(),
                getCollapsibleWidth(),
                collapsibleView.getBottom());
        collapsibleSibling.layout(collapsibleSibling.getLeft(),
                collapsibleSibling.getTop(),
                getCollapsibleSiblingWidth(),
                collapsibleSibling.getBottom());
    }

    private int getCollapsibleWidth() {
        if (collapsibleWidth == -1) {
            collapsibleWidth = collapsibleView.getMeasuredWidth();
        }
        int newWidth;
        if (scrollOffset > 0.0f) {
            newWidth = collapsibleWidth - (int) (collapsibleWidth * scrollOffset);
            collapsibleView.setAlpha(1.0f - scrollOffset);
        } else {
            newWidth = currentSelectedItem == 0 ? collapsibleWidth : 0;
            collapsibleView.setAlpha(1.0f);
        }
        return collapsibleView.getLeft() + newWidth;
    }

    private int getCollapsibleSiblingWidth() {
        int newWidth = collapsibleSibling.getLeft() + collapsibleSibling.getMeasuredWidth();
        if (scrollOffset > 0.0f) {
            newWidth += (int) (collapsibleWidth * scrollOffset);
        } else {
            newWidth += currentSelectedItem == 0 ? 0 : collapsibleWidth;
        }
        return newWidth;
    }

    @NonNull
    private Pair<Integer, Integer> getTabStartAndEnd() {
        int tabWidth = getMeasuredWidth() / container.getChildCount();
        int tabLeft;
        int tabRight;
        if (currentSelectedItem == 0) {
            tabLeft = getLeft();
            tabRight = tabWidth;
            if (scrollOffset > 0.0f) {
                tabLeft += (int) (tabWidth * scrollOffset);
                tabRight += (int) (tabWidth * scrollOffset);
            }
        } else {
            tabLeft = tabWidth;
            tabRight = getRight();
            if (scrollOffset > 0.0f) {
                tabLeft -= (int) (tabWidth * (1.0f - scrollOffset));
                tabRight -= (int) (tabWidth * (1.0f - scrollOffset));
            }
        }
        return new Pair<>(tabLeft, tabRight);
    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new MarginLayoutParams(getContext(), attrs);
    }

    @Override
    protected LayoutParams generateLayoutParams(LayoutParams p) {
        return new MarginLayoutParams(p);
    }

    @Override
    protected LayoutParams generateDefaultLayoutParams() {
        return new MarginLayoutParams(MATCH_PARENT, MATCH_PARENT);
    }

    @Override
    protected boolean checkLayoutParams(LayoutParams p) {
        return p instanceof MarginLayoutParams;
    }

    @Override

    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        scrollOffset = positionOffset;
        requestLayout();
    }

    @Override
    public void onPageSelected(int position) {
        currentSelectedItem = position;
    }

    @Override
    public void onPageScrollStateChanged(int state) {
        // Nothing
    }
}
