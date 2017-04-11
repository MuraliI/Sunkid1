package com.rcl.excalibur.custom.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.rcl.excalibur.R;

public class PriceRangeLayout extends LinearLayout {

    public static final int DEFAULT_MAX_RANGE = 4;
    public static final int DEFAULT_VALUE = 0;
    public static final int DEFAULT_ON_COLOR = Color.BLACK;
    public static final int DEFAULT_OFF_COLOR = Color.GRAY;

    private ImageView[] dollars;
    private LinearLayout rootLayout;

    private int maxRange = DEFAULT_MAX_RANGE;
    private int currentValue = DEFAULT_VALUE;
    private int onColor = DEFAULT_ON_COLOR;
    private int offColor = DEFAULT_OFF_COLOR;


    public PriceRangeLayout(Context context) {
        super(context);
        init(context);
    }

    public PriceRangeLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        TypedArray attributes = context.getTheme().obtainStyledAttributes(attrs, R.styleable.PriceRangeLayout, 0, 0);
        try {
            maxRange = attributes.getInt(R.styleable.PriceRangeLayout_maxRange, DEFAULT_MAX_RANGE);
            currentValue = attributes.getInt(R.styleable.PriceRangeLayout_value, DEFAULT_VALUE);
            onColor = attributes.getColor(R.styleable.PriceRangeLayout_onColor, DEFAULT_ON_COLOR);
            offColor = attributes.getColor(R.styleable.PriceRangeLayout_offColor, DEFAULT_OFF_COLOR);
        } finally {
            attributes.recycle();
        }
        init(context);
    }

    private void init(Context context) {
        View view = inflate(context, R.layout.view_price_range, this);
        rootLayout = (LinearLayout) view.findViewById(R.id.layout_price_range_container);
        setMaxRange(maxRange);
        setValue(currentValue);
    }

    public void setMaxRange(int maxRange) {
        if (maxRange < 1) {
            return;
        }
        this.maxRange = maxRange;
        dollars = new ImageView[maxRange];
        for (int i = 0; i < maxRange; i++) {
            dollars[i] = (ImageView) LayoutInflater.from(getContext()).inflate(R.layout.view_price_range_dollar, null, false);
            rootLayout.addView(dollars[i]);
        }
        invalidate();
        requestLayout();
    }

    public void setValue(int value) {
        if (value < 0 || value > maxRange) {
            return;
        }
        this.currentValue = value;
        for (int i = 0; i < maxRange; i++) {
            changeTint(dollars[i], i < value);
        }
    }

    private void changeTint(ImageView imageView, boolean valid) {
        imageView.setColorFilter(valid ? onColor : offColor);
    }
}
