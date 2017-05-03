package com.rcl.excalibur.custom.view;

import android.content.Context;
import android.support.v4.util.Pair;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;

import com.rcl.excalibur.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.functions.Consumer;
import io.reactivex.subjects.PublishSubject;

public class ShipView extends RelativeLayout {
    private static final float SHIP_NEXT_TAB_SCALE = 0.75f;
    private static final float SHIP_ORIGINAL_SCALE = 0.5f;
    @BindView(R.id.image_ship) View ship;
    @BindView(R.id.image_cloud_left) View leftCloud;
    @BindView(R.id.image_cloud_right) View rightCloud;
    @BindView(R.id.text_ship_status) View shipLabel;
    private int selectedPage;
    private float scrollOffset;
    private PublishSubject<Pair<Integer, Integer>> publisherSubject = PublishSubject.create();

    public ShipView(Context context) {
        super(context);
    }

    public ShipView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ShipView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public ShipView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        ButterKnife.bind(this);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        if (selectedPage == 0) {
            if (scrollOffset > 0.0f) {
                layoutShipOnScroll();
                layoutCloudsOnScroll();
                layoutLabelShipOnScroll();
            } else {
                layoutShipOnOrigin();
                layoutCloudsOnOrigin();
                layoutLabelShipOnOrigin();
            }
        } else if (scrollOffset > 0.0f) {
            layoutShipOnScroll();
            layoutCloudsOnScroll();
            layoutLabelShipOnScroll();
        } else {
            layoutShipOnNextTab();
            layoutCloudsOnNextTab();
            layoutLabelShipOnNextTab();
        }
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        publisherSubject.onComplete();
        publisherSubject = null;
    }

    private void layoutLabelShipOnScroll() {
        shipLabel.setAlpha(1.0f - scrollOffset);
    }

    private void layoutLabelShipOnNextTab() {
        shipLabel.setAlpha(0.0f);
    }

    private void layoutLabelShipOnOrigin() {
        shipLabel.setAlpha(1.0f);
    }

    private void layoutCloudsOnOrigin() {
        int leftCloudOriginalWidth = leftCloud.getMeasuredWidth() / 2;
        int leftCloudOriginalHeight = leftCloud.getMeasuredHeight() / 2;
        int rightCloudOriginalWidth = rightCloud.getMeasuredWidth() / 2;
        int rightCloudOriginalHeight = rightCloud.getMeasuredHeight() / 2;
        leftCloud.layout(leftCloud.getLeft() - leftCloudOriginalWidth,
                leftCloud.getTop(),
                leftCloud.getRight() - leftCloudOriginalWidth,
                leftCloud.getTop() + leftCloudOriginalHeight);
        rightCloud.layout(rightCloud.getLeft() + rightCloudOriginalWidth,
                rightCloud.getTop(),
                rightCloud.getRight() + rightCloudOriginalWidth,
                rightCloud.getTop() + rightCloudOriginalHeight);
    }

    private void layoutCloudsOnNextTab() {
        leftCloud.layout(0, 0, 0, 0);
        rightCloud.layout(0, 0, 0, 0);
    }

    private void layoutCloudsOnScroll() {
        int leftCloudOriginalWidth = (leftCloud.getMeasuredWidth() / 2);
        int leftCloudOriginalHeight = leftCloud.getMeasuredHeight() / 2;
        int rightCloudOriginalWidth = rightCloud.getMeasuredWidth() / 2;
        int rightCloudOriginalHeight = rightCloud.getMeasuredHeight() / 2;
        int leftDelta = (int) (leftCloudOriginalWidth * scrollOffset);
        int rightDelta = (int) (rightCloudOriginalWidth * scrollOffset);
        leftCloud.layout(leftCloud.getLeft() - leftCloudOriginalWidth - leftDelta,
                leftCloud.getTop(),
                leftCloud.getRight() - leftCloudOriginalWidth - leftDelta,
                leftCloud.getTop() + leftCloudOriginalHeight);
        rightCloud.layout(rightCloud.getLeft() + rightCloudOriginalWidth + rightDelta,
                rightCloud.getTop(),
                rightCloud.getRight() + rightCloudOriginalWidth + rightDelta,
                rightCloud.getTop() + rightCloudOriginalHeight);
    }

    private void layoutShipOnOrigin() {
        ship.layout(ship.getLeft(), ship.getTop(), ship.getRight(), (int) (getBottom() * SHIP_ORIGINAL_SCALE));
        notifyObserver();
    }

    private void layoutShipOnNextTab() {
        ship.layout(ship.getLeft(), getTop(), ship.getRight(), (int) (getBottom() * SHIP_NEXT_TAB_SCALE));
    }

    private void layoutShipOnScroll() {
        int startTop = ship.getTop();
        int topDelta = (int) ((startTop - getTop()) * scrollOffset);
        int startBottom = (int) (getBottom() * SHIP_ORIGINAL_SCALE);
        int bottomDelta = (int) ((getBottom() * SHIP_NEXT_TAB_SCALE - startBottom) * scrollOffset);
        ship.layout(ship.getLeft(), startTop - topDelta, ship.getRight(), startBottom + bottomDelta);
    }

    public void syncTabsScroll(Pair<Integer, Float> integerFloatPair) {
        selectedPage = integerFloatPair.first;
        scrollOffset = integerFloatPair.second;
        requestLayout();
    }

    private void notifyObserver() {
        if (publisherSubject != null) {
            publisherSubject.onNext(new Pair<>(ship.getWidth(), ship.getHeight()));
        }
    }

    public void subscribeToSizeUpdate(Consumer<Pair<Integer, Integer>> pairConsumer) {
        publisherSubject.subscribe(pairConsumer);
    }
}
