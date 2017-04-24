package com.rcl.excalibur.custom.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.RectF;
import android.support.annotation.DimenRes;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import com.rcl.excalibur.R;


public class TopRoundedFrameLayout extends FrameLayout {
    private Path clipPath;
    private RectF clipBoundsRect;
    private RectF noCornersRect;

    private float radius;

    public TopRoundedFrameLayout(Context context) {
        super(context);
        init(context, null);
    }

    public TopRoundedFrameLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public TopRoundedFrameLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.getTheme().obtainStyledAttributes(attrs, R.styleable.RoundedView, 0, 0);

        try {
            radius = typedArray.getDimension(R.styleable.RoundedView_borderRadius,
                    getResources().getDimension(R.dimen.planner_top_radius));
        } finally {
            typedArray.recycle();
        }

        clipPath = new Path();
        clipBoundsRect = new RectF();
        noCornersRect = new RectF();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        clipBoundsRect.set(canvas.getClipBounds());
        noCornersRect.set(0, radius, this.getWidth(), this.getHeight());

        clipPath.addRoundRect(clipBoundsRect, radius, radius, Path.Direction.CW);
        clipPath.addRect(noCornersRect, Path.Direction.CW);
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
