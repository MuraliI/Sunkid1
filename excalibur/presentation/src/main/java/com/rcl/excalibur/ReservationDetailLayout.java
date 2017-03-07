package com.rcl.excalibur;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.github.mmin18.widget.RealtimeBlurView;

import butterknife.Bind;
import butterknife.ButterKnife;


public class ReservationDetailLayout extends FrameLayout {

    @Bind(R.id.realtime_blur_view) RealtimeBlurView realtimeBlurView;
    @Bind(R.id.text_icon_plus_reservation_text) TextView textView;
    @Bind(R.id.image_reservation) ImageView reservationImage;

    private View completeLayout;

    public ReservationDetailLayout(Context context) {
        super(context, null);
        initialize();
    }

    public ReservationDetailLayout(Context context, AttributeSet attrs) {
        super(context, attrs, 0);
        initialize();
    }

    public ReservationDetailLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr, 0);
        initialize();
    }

    public ReservationDetailLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initialize();
    }

    private void initialize() {
        completeLayout = LayoutInflater.from(getContext()).inflate(R.layout.module_reservation_detail, this);
        ButterKnife.bind(this, completeLayout);
    }

    public void showOnlyReservationIcon() {
        int height = getLayoutParams().height;

        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, height);
        params.addRule(RelativeLayout.ALIGN_PARENT_END);
        setLayoutParams(params);

        realtimeBlurView.setVisibility(View.GONE);
        textView.setVisibility(View.GONE);
        reservationImage.setVisibility(View.VISIBLE);

        invalidate();
    }

}
