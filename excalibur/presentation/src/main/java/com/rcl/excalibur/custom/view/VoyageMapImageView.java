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

    private Paint paint;
    private Bitmap cruise;
    private PointF cruiseCoord;
    private Matrix shipMatrix;
    private Matrix localMatrix;
    private boolean cruiseWasDrawed = false;

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
        matrixRotation.postRotate(20);
        cruise = Bitmap.createBitmap(cruise, 0, 0, cruise.getWidth(), cruise.getHeight(), matrixRotation, true);
    }

    public void setImage(int resource) {
        this.setImage(ImageSource.resource(resource));
    }

    public void setCruiseCoord(PointF cruiseCoord) {
        this.cruiseCoord = cruiseCoord;
        initialize();
        invalidate();
        shipMatrix = new Matrix();
        localMatrix = new Matrix();
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

        //canvas.save(Canvas.MATRIX_SAVE_FLAG); //Saving the canvas and later restoring it so only this image will be rotated.
        //canvas.rotate(20);
        //shipMatrix.setRotate(20f, top, left);
/*
        if (shipMatrix.isIdentity()) {
            try {
                Field field = this.getClass().getSuperclass().getDeclaredField("matrix");
                field.setAccessible(true);
                shipMatrix = new Matrix((Matrix) field.get(this));
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            localMatrix = new Matrix(shipMatrix);
            localMatrix.postRotate(20);
            //localMatrix.setRotate(30);
            //canvas.drawBitmap(cruise, left, top, paint);
        }
        if (!localMatrix.isIdentity()) {
            canvas.drawBitmap(cruise, localMatrix, paint);
        }

        */


    }
}
