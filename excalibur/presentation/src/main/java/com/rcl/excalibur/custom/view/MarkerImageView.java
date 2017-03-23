package com.rcl.excalibur.custom.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.Region;
import android.util.AttributeSet;

import com.davemorrissey.labs.subscaleview.ImageSource;
import com.davemorrissey.labs.subscaleview.SubsamplingScaleImageView;
import com.rcl.excalibur.R;
import com.rcl.excalibur.model.DiscoverDeckMapModel;


public class MarkerImageView extends SubsamplingScaleImageView {
    private static final int DELAY_ANIMATION = 250;
    private static final float DENSITY_FACTOR = 420f;

    private DiscoverDeckMapModel discoverDeckMap;
    //private Map<DiscoverItemModel, Region>

    private Bitmap marker;
    private Paint paint;

    public MarkerImageView(Context context, AttributeSet attr) {
        super(context, attr);
        initialize();
    }

    public MarkerImageView(Context context) {
        super(context);
        initialize();
    }

    private void initialize() {
        paint = new Paint();

        float density = getResources().getDisplayMetrics().densityDpi;
        marker = BitmapFactory.decodeResource(this.getResources(), R.drawable.marker);
        float w = (density / DENSITY_FACTOR) * marker.getWidth();
        float h = (density / DENSITY_FACTOR) * marker.getHeight();
        marker = Bitmap.createScaledBitmap(marker, (int) w, (int) h, true);
    }

    public void setImage(int resource) {
        this.setImage(ImageSource.resource(resource));
    }

    public void setDiscoverDeckMap(DiscoverDeckMapModel discoverDeckMap) {
        this.discoverDeckMap = discoverDeckMap;
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

        if (discoverDeckMap != null && marker != null) {
            PointF vPin = sourceToViewCoord(new PointF(0, 0));

            float left = vPin.x - marker.getWidth() / 2;
            float top = vPin.y - marker.getHeight();
            float right = vPin.x + marker.getWidth() / 2;
            float bottom = vPin.y;

            discoverDeckMap.setRegion(new Region((int) left, (int) top, (int) right, (int) bottom));
            canvas.drawBitmap(marker, left, top, paint);
        }
    }
}
