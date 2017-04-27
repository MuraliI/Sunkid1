package com.rcl.excalibur.custom.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;
import android.util.AttributeSet;

import com.davemorrissey.labs.subscaleview.SubsamplingScaleImageView;
import com.rcl.excalibur.R;

public class VoyageMapImageView extends SubsamplingScaleImageView {
    private static final float DENSITY_FACTOR = 420f;

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
        cruise = BitmapFactory.decodeResource(this.getResources(), R.drawable.ship_big);
        float w = (density / DENSITY_FACTOR) * cruise.getWidth();
        float h = (density / DENSITY_FACTOR) * cruise.getHeight();
        cruise = Bitmap.createScaledBitmap(cruise, (int) w, (int) h, true);
    }

    public void setImage(int resource) {
        //this.setImage(ImageSource.resource(resource));
        this.setScaleX(10000);
        this.setScaleY(10000);
    }

    public void setCruiseCoord(PointF cruiseCoord) {
        this.cruiseCoord = cruiseCoord;
        initialize();
        invalidate();
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
        canvas.save(Canvas.MATRIX_SAVE_FLAG); //Saving the canvas and later restoring it so only this image will be rotated.
        canvas.rotate(-20);
        // Matrix matrix = new Matrix();
        //matrix.setRotate(20, top, left);
        //canvas.drawBitmap(cruise, matrix, null);
        //canvas.drawBitmap(cruise, top, left, paint);
        canvas.restore();
    }
}
