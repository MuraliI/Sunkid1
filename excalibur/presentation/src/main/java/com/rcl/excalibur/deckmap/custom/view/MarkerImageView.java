package com.rcl.excalibur.deckmap.custom.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.Region;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;

import com.davemorrissey.labs.subscaleview.ImageSource;
import com.davemorrissey.labs.subscaleview.SubsamplingScaleImageView;
import com.rcl.excalibur.R;
import com.rcl.excalibur.deckmap.model.ProductDeckMapModel;


public class MarkerImageView extends SubsamplingScaleImageView {
    // TODO: PENDING REFACTOR TO MVP

    private static final int DELAY_ANIMATION = 250;
    private static final float DENSITY_FACTOR = 420f;

    private ProductDeckMapModel productDeckMapModel;
    private OnMarkerClickListener listener;

    private Bitmap marker;
    private Paint paint;

    public void setOnMarkerClickListener(OnMarkerClickListener listener) {
        this.listener = listener;
    }

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

        setOnTouchListener((view, motionEvent) -> gestureDetector.onTouchEvent(motionEvent));
    }

    public void setImage(int resource) {
        this.setImage(ImageSource.resource(resource));
    }

    public void setProductDeckMap(ProductDeckMapModel productDeckMapModel) {
        this.productDeckMapModel = productDeckMapModel;
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

        if (productDeckMapModel != null && marker != null) {
            PointF vPin = sourceToViewCoord(productDeckMapModel.getCoordinate());

            float left = vPin.x - marker.getWidth() / 2;
            float top = vPin.y - marker.getHeight();
            float right = vPin.x + marker.getWidth() / 2;
            float bottom = vPin.y;

            productDeckMapModel.setRegion(new Region((int) left, (int) top, (int) right, (int) bottom));
            canvas.drawBitmap(marker, left, top, paint);
        }
    }

    private final GestureDetector gestureDetector = new GestureDetector(getContext(),
            new GestureDetector.SimpleOnGestureListener() {
                @Override
                public boolean onSingleTapConfirmed(MotionEvent e) {
                    if (isReady() && isTouchInsideRegion(new PointF(e.getX(), e.getY()))) {
                        if (listener != null) {
                            listener.isInsideRegion();
                        }
                    }
                    return true;
                }
            }
    );

    private boolean isTouchInsideRegion(PointF touchedLocation) {
        Region region = productDeckMapModel.getRegion();
        return region.contains((int) touchedLocation.x, (int) touchedLocation.y);
    }

    public interface OnMarkerClickListener {
        void isInsideRegion();
    }
}
