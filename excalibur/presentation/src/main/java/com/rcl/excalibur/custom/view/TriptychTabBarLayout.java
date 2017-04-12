package com.rcl.excalibur.custom.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v4.util.Pair;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import com.rcl.excalibur.R;


public class TriptychTabBarLayout extends ViewGroup implements ViewPager.OnPageChangeListener {
    public static final int POSITION_OFFSET_FOR_FIRST_CHILD = 1;
    private ViewPager viewPager;
    private View tabStrip;
    private View currentSelectedChild;
    private int currentSelectedItem = POSITION_OFFSET_FOR_FIRST_CHILD;
    private float scrollOffset = 0.0f;

    public TriptychTabBarLayout(Context context) {
        super(context);
        initialize(context, null, 0, 0);
    }

    public TriptychTabBarLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        initialize(context, attrs, 0, 0);
    }

    public TriptychTabBarLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initialize(context, attrs, defStyleAttr, 0);
    }

    public TriptychTabBarLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initialize(context, attrs, defStyleAttr, defStyleRes);
    }

    private void initialize(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        TypedArray typedArray = context.getTheme().obtainStyledAttributes(attrs, R.styleable.TriptychTabBarLayout, defStyleAttr,
                defStyleRes);
        int tabStripHeight;
        int tabStripColor;
        try {
            tabStripColor = typedArray.getColor(R.styleable.TriptychTabBarLayout_stripColor,
                    ContextCompat.getColor(context, R.color.black));
            tabStripHeight = (int) typedArray.getDimension(R.styleable.TriptychTabBarLayout_stripThickness, getResources().getDimension(R.dimen.triptych_strip_tickness));
        } finally {
            typedArray.recycle();
        }
        tabStrip = new View(context);
        tabStrip.setBackgroundColor(tabStripColor);
        addView(tabStrip, LayoutParams.WRAP_CONTENT, tabStripHeight);
    }

    public void attachToViewPager(ViewPager viewPager) {
        this.viewPager = viewPager;
        viewPager.addOnPageChangeListener(this);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        for (int index = POSITION_OFFSET_FOR_FIRST_CHILD; index < getChildCount(); index++) {
            View child = getChildAt(index);
            child.setOnClickListener(((View v) -> {
                for (int position = 0; position < getChildCount(); position++) {
                    View newCurrent = getChildAt(position + 1);
                    if (newCurrent.equals(v)) {
                        viewPager.setCurrentItem(position);
                        break;
                    }
                }
            }));
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //This will determine how height this layout is going to be
        int maxHeight = 0;
        //This will determine how broad this layout should be
        int widthUsed = 0;

        for (int i = 0; i < getChildCount(); i++) {
            View child = getChildAt(i);

            measureChildWithMargins(child, widthMeasureSpec, 0, heightMeasureSpec, 0);

            int topMargin = ((MarginLayoutParams) child.getLayoutParams()).topMargin;
            int rightMargin = ((MarginLayoutParams) child.getLayoutParams()).rightMargin;
            int leftMargin = ((MarginLayoutParams) child.getLayoutParams()).leftMargin;
            int bottomMargin = ((MarginLayoutParams) child.getLayoutParams()).bottomMargin;

            int childHeight = child.getMeasuredHeight() + topMargin + bottomMargin;
            int childWidth = child.getMeasuredWidth() + leftMargin + rightMargin;

            maxHeight = childHeight > maxHeight ? childHeight : maxHeight;
            widthUsed += childWidth;
        }

        //Calculate margins to include it in laying out the whole view
        //TODO include padding calculation
        int topMargin = ((MarginLayoutParams) getLayoutParams()).topMargin;
        int bottomMargin = ((MarginLayoutParams) getLayoutParams()).bottomMargin;

        //We need to add the tabStrip height to the maximum layout height so it can be displayed
        maxHeight += tabStrip.getMeasuredHeight() + topMargin + bottomMargin;

        //Set this viewgroup width and height
        setMeasuredDimension(resolveSize(widthUsed, widthMeasureSpec), resolveSize(maxHeight, heightMeasureSpec));

        //Get current child and calculate the tab strip width
        currentSelectedChild = getChildAt(currentSelectedItem);
        int tabStripWidthMeasureSpec = MeasureSpec.makeMeasureSpec(currentSelectedChild.getMeasuredWidth(), MeasureSpec.EXACTLY);
        int tabStripHeightMeasureSpec = MeasureSpec.makeMeasureSpec(tabStrip.getMeasuredHeight(), MeasureSpec.EXACTLY);
        tabStrip.measure(tabStripWidthMeasureSpec, tabStripHeightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        layoutForChildren();
        layoutForTabStrip();
    }

    private void layoutForChildren() {
        int childCount = getChildCount();
        int dx = getWidth() / childCount;

        for (int i = 0; i < childCount; i++) {
            View child = getChildAt(i);

            if (!child.equals(tabStrip)) {
                int topMargin = ((MarginLayoutParams) child.getLayoutParams()).topMargin;
                int rightMargin = ((MarginLayoutParams) child.getLayoutParams()).rightMargin;
                int leftMargin = ((MarginLayoutParams) child.getLayoutParams()).leftMargin;
                int bottomMargin = ((MarginLayoutParams) child.getLayoutParams()).bottomMargin;

                int calculatedLeft = getLeft() + (i * dx) - (child.getMeasuredWidth() / 2) + leftMargin;
                child.layout(calculatedLeft,
                        getTop() + tabStrip.getMeasuredHeight() + topMargin,
                        child.getMeasuredWidth() + calculatedLeft - rightMargin,
                        child.getMeasuredHeight() + getTop() + topMargin - bottomMargin);
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

    @NonNull
    private Pair<Integer, Integer> getTabStartAndEnd() {
        int tabWidth = getMeasuredWidth() / (getChildCount() - 1);
        int tabLeft;
        int tabRight;
        if (currentSelectedItem == POSITION_OFFSET_FOR_FIRST_CHILD) {
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
        return new MarginLayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
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
        currentSelectedItem = POSITION_OFFSET_FOR_FIRST_CHILD + position;
        currentSelectedChild = getChildAt(currentSelectedItem);
    }

    @Override
    public void onPageScrollStateChanged(int state) {
        // Nothing
    }
}
