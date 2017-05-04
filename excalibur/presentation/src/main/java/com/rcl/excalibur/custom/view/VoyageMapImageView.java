package com.rcl.excalibur.custom.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;
import android.util.AttributeSet;

import com.davemorrissey.labs.subscaleview.ImageSource;
import com.davemorrissey.labs.subscaleview.SubsamplingScaleImageView;
import com.rcl.excalibur.R;

public class VoyageMapImageView extends SubsamplingScaleImageView {
    public static final float DENSITY_FACTOR = 1400f;
    public static final long DELAY_ANIMATION = 600;
    public static final int SCALE_FACTOR = 5;

    private Paint paint;
    private Bitmap cruise;
    private PointF cruiseCoord;

    public VoyageMapImageView(Context context, AttributeSet attr) {
        super(context, attr);
        initialize();
    }

    public VoyageMapImageView(Context context) {
        super(context, null);
        initialize();
    }

    private void initialize() {
        paint = new Paint();
        float density = getResources().getDisplayMetrics().densityDpi;
        cruise = BitmapFactory.decodeResource(this.getResources(), R.drawable.voyage_cruise);
        float w = (density / DENSITY_FACTOR) * cruise.getWidth();
        float h = (density / DENSITY_FACTOR) * cruise.getHeight();
        cruise = Bitmap.createScaledBitmap(cruise, (int) w, (int) h, true);
    }

    public void setImage(int resource) {
        this.setImage(ImageSource.resource(resource));
    }

    public void setCruiseCoord(PointF cruiseCoord) {
        this.cruiseCoord = cruiseCoord;
        initialize();
        invalidate();
    }

    public void animatePointToCenter(PointF pointF, OnAnimationEventListener event) {
        animateScaleAndCenter(getMaxScale() / SCALE_FACTOR, pointF)
                .withDuration(DELAY_ANIMATION)
                .withEasing(SubsamplingScaleImageView.EASE_OUT_QUAD)
                .withInterruptible(false)
                .withOnAnimationEventListener(event)
                .start();
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (!isReady()) {
            return;
        }
        paint.setAntiAlias(true);

        if (cruiseCoord == null || cruise == null) {
            return;
        }

        PointF vCruise = sourceToViewCoord(cruiseCoord);

        float left = vCruise.x - (cruise.getWidth() / 2);
        float top = vCruise.y - cruise.getHeight();
        canvas.drawBitmap(cruise, left, top, paint);
    }
}
