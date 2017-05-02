package com.rcl.excalibur.custom.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PointF;
import android.util.AttributeSet;

import com.davemorrissey.labs.subscaleview.ImageSource;
import com.davemorrissey.labs.subscaleview.SubsamplingScaleImageView;
import com.rcl.excalibur.R;

public class VoyageMapImageView extends SubsamplingScaleImageView {
    private static final float DENSITY_FACTOR = 1400f;
    private static final long DELAY_ANIMATION = 600;

    private Paint paint;
    private Bitmap cruise;
    private PointF cruiseCoord;
    private long angle;

    public long getAngle() {
        return angle;
    }

    public void setAngle(long angle) {
        this.angle = angle;
    }

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
        Matrix matrixRotation = new Matrix();
        matrixRotation.postRotate(getAngle());
        cruise = Bitmap.createBitmap(cruise, 0, 0, cruise.getWidth(), cruise.getHeight(), matrixRotation, true);
    }

    public void setImage(int resource) {
        this.setImage(ImageSource.resource(resource));
    }

    public void setCruiseCoord(PointF cruiseCoord) {
        this.cruiseCoord = cruiseCoord;
        initialize();
        invalidate();
    }

    public void animatePointToCenter(PointF pointF) {
        animateScaleAndCenter(getMaxScale() / 3, pointF)
                .withDuration(DELAY_ANIMATION)
                .withEasing(SubsamplingScaleImageView.EASE_OUT_QUAD)
                .withInterruptible(false)
                .start();
    }

    public PointF getPin() {
        return cruiseCoord;
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
