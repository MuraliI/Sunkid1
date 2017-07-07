package com.rcl.excalibur.utils;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.RectF;
import android.support.annotation.DimenRes;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;

import com.rcl.excalibur.R;

public class RoundedImageView extends AppCompatImageView {

    private float radius;

    public RoundedImageView(Context context) {
        super(context);
        init();
    }

    public RoundedImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public RoundedImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init() {
        radius = getContext().getResources().getDimension(R.dimen.default_radius);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Path clipPath = new Path();
        RectF rect = new RectF(0, 0, this.getWidth(), this.getHeight());
        clipPath.addRoundRect(rect, radius, radius, Path.Direction.CW);
        canvas.clipPath(clipPath);
        super.onDraw(canvas);
    }

    public float getRadius() {
        return radius;
    }

    public void setRadius(@DimenRes int radius) {
        this.radius = getResources().getDimension(radius);
    }
}
