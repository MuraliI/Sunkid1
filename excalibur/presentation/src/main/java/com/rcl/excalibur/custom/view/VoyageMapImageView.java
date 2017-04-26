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
    private static final int DELAY_ANIMATION = 250;
    private static final float DENSITY_FACTOR = 420f;

    private PointF sPin;

    private Paint paint;
    private Bitmap cruise;

    public VoyageMapImageView(Context context, AttributeSet attr) {
        super(context, attr);
        initialize();
    }

    public VoyageMapImageView(Context context) {
        super(context);
        initialize();
    }

    private void initialize() {
        paint = new Paint();
        float density = getResources().getDisplayMetrics().densityDpi;
        cruise = BitmapFactory.decodeResource(this.getResources(), R.drawable.marker);
        float w = (density / DENSITY_FACTOR) * cruise.getWidth();
        float h = (density / DENSITY_FACTOR) * cruise.getHeight();
        cruise = Bitmap.createScaledBitmap(cruise, (int) w, (int) h, true);
    }

    public void setPin(PointF sPin) {
        this.sPin = sPin;
        initialize();
        invalidate();
    }

    public PointF getPin() {
        return sPin;
    }

    public void setImage(int resource) {
        this.setImage(ImageSource.resource(resource));
        //this.setPanLimit(PAN_LIMIT_OUTSIDE);
    }

    public void setCruiseCoord(PointF productCoord) {
        invalidate();
    }

    public void animatePointToCenter(PointF pointF) {
        animateScaleAndCenter(getMaxScale(), pointF)
                .withDuration(DELAY_ANIMATION)
                .withEasing(SubsamplingScaleImageView.EASE_OUT_QUAD)
                .withInterruptible(false)
                .start();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (!isReady()) {
            return;
        }
        paint.setAntiAlias(true);

        if (sPin != null && cruise != null) {
            float left = 300 - cruise.getWidth() / 2;
            float top = 530 - cruise.getHeight();
            canvas.drawBitmap(cruise, top, left, paint);
        }
    }
}
