package com.rcl.excalibur.custom.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.RectF;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.view.MotionEvent;

import com.davemorrissey.labs.subscaleview.ImageSource;
import com.davemorrissey.labs.subscaleview.SubsamplingScaleImageView;
import com.rcl.excalibur.R;

public class DeckMapImageView extends SubsamplingScaleImageView {
    private static final int DELAY_ANIMATION = 250;
    private static final float DENSITY_FACTOR = 420f;

    private RectF markerArea;
    private PointF productCoord;

    private Bitmap marker;
    private Paint paint;

    public DeckMapImageView(Context context, AttributeSet attr) {
        super(context, attr);
        initialize();
    }

    public DeckMapImageView(Context context) {
        super(context);
        initialize();
    }

    private void initialize() {
        paint = new Paint();
        markerArea = new RectF();

        float density = getResources().getDisplayMetrics().densityDpi;
        marker = BitmapFactory.decodeResource(this.getResources(), R.drawable.marker);
        float w = (density / DENSITY_FACTOR) * marker.getWidth();
        float h = (density / DENSITY_FACTOR) * marker.getHeight();
        marker = Bitmap.createScaledBitmap(marker, (int) w, (int) h, true);
    }

    public void setImage(int resource) {
        this.setImage(ImageSource.resource(resource));
    }

    public void setProductCoord(PointF productCoord) {
        this.productCoord = productCoord;
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
    public boolean onTouchEvent(@NonNull MotionEvent event) {
        return super.onTouchEvent(event);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (!isReady()) {
            return;
        }

        paint.setAntiAlias(true);

        if (productCoord == null || marker == null) {
            return;
        }

        PointF vPin = sourceToViewCoord(productCoord);

        float left = vPin.x - marker.getWidth() / 2;
        float top = vPin.y - marker.getHeight();
        float right = vPin.x + marker.getWidth() / 2;
        float bottom = vPin.y;

        markerArea.set(left, top, right, bottom);
        canvas.drawBitmap(marker, left, top, paint);
    }

    public RectF getMarkerArea() {
        return markerArea;
    }

    public float getMarketHeight() {
        return marker.getHeight();
    }
}
