package com.rcl.excalibur.custom.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import com.rcl.excalibur.R;
import com.rcl.excalibur.utils.ViewUtils;


public class TriptychTabBarLayout extends ViewGroup implements ViewPager.OnPageChangeListener {

    public static final int POSITION_OFFSET_FOR_FIRST_CHILD = 1;

    private View tabStrip;
    private ViewPager viewPager;
    private PagerAdapter pagerAdapter;

    private View currentSelectedChild;
    private int currentSelectedItem = POSITION_OFFSET_FOR_FIRST_CHILD;

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
        TypedArray tp = context.getTheme().obtainStyledAttributes(attrs,
                R.styleable.TriptychTabBarLayout,
                defStyleAttr,
                defStyleRes);

        int tabStripHeight;
        int tabStripColor;

        try {
            tabStripColor = tp.getColor(
                    R.styleable.TriptychTabBarLayout_stripColor,
                    ViewUtils.getColor(
                            R.color.black,
                            context));

            tabStripHeight = (int) tp.getDimension(
                    R.styleable.TriptychTabBarLayout_stripThickness,
                    getResources().getDimension(R.dimen.triptych_strip_tickness));
        } finally {
            tp.recycle();
        }

        tabStrip = new View(context);
        tabStrip.setBackgroundColor(tabStripColor);
        addView(tabStrip, LayoutParams.WRAP_CONTENT, tabStripHeight);
    }

    public void setViewPager(ViewPager viewPager) {
        this.viewPager = viewPager;
        pagerAdapter = viewPager.getAdapter();
        viewPager.addOnPageChangeListener(this);
    }

    public ViewPager getViewPager() {
        return viewPager;
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        for (int i = POSITION_OFFSET_FOR_FIRST_CHILD; i < getChildCount(); i++) {
            View child = getChildAt(i);
            child.setOnClickListener(((View v) -> {
                currentSelectedChild = v;
                for (int j = 1; j < getChildCount(); j++) {
                    View newCurrent = getChildAt(j);
                    if (newCurrent.equals(currentSelectedChild)) {
                        currentSelectedItem = j;
                        viewPager.setCurrentItem(currentSelectedItem - POSITION_OFFSET_FOR_FIRST_CHILD);
                        break;
                    }
                }
                requestLayout();
            }));
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        measureChildren(widthMeasureSpec, heightMeasureSpec);

        //This will determine how height this layout is going to be
        int maxHeight = 0;
        //This will determine how broad this layout should be
        int widthUsed = 0;

        for (int i = 0; i < getChildCount(); i++) {
            View child = getChildAt(i);

            int childHeight = child.getMeasuredHeight();
            int childWidth = child.getMeasuredWidth();

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
        int childCount = getChildCount();
        int dx = getWidth() / childCount;
        int parentLeft = getLeft();
        int parentTop = getTop();

        for (int i = 0; i < childCount; i++) {
            View child = getChildAt(i);

            if (!child.equals(tabStrip)) {
                int calculatedLeft = parentLeft + (i * dx) - (child.getMeasuredWidth() / 2);
                child.layout(calculatedLeft,
                        parentTop,
                        child.getMeasuredWidth() + calculatedLeft,
                        child.getMeasuredHeight() + parentTop);
            }
        }

        int tabTop = currentSelectedChild.getTop() + currentSelectedChild.getHeight();
        int tabBottom = tabStrip.getMeasuredHeight() + currentSelectedChild.getBottom();
        tabStrip.layout(currentSelectedChild.getLeft(),
                tabTop,
                currentSelectedChild.getRight(),
                tabBottom);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        //TODO use this to create the translation animation of the tabStrip
    }

    @Override
    public void onPageSelected(int position) {
        currentSelectedItem = POSITION_OFFSET_FOR_FIRST_CHILD + position;
        currentSelectedChild = getChildAt(currentSelectedItem);
        requestLayout();
    }

    @Override
    public void onPageScrollStateChanged(int state) {
        //no op
    }
}
