package com.rcl.excalibur.custom.view.layoutmanager;


import android.content.Context;
import android.support.v7.widget.LinearSmoothScroller;
import android.support.v7.widget.RecyclerView;
import android.view.View;

class PlannerSmoothScroller extends LinearSmoothScroller {
    PlannerSmoothScroller(Context context) {
        super(context);
    }

    @Override
    public int calculateDyToMakeVisible(View view, int snapPreference) {
        final RecyclerView.LayoutManager layoutManager = getLayoutManager();
        if (layoutManager == null || !layoutManager.canScrollVertically()) {
            return 0;
        }
        final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams)
                view.getLayoutParams();
        final int top = layoutManager.getDecoratedTop(view) - params.topMargin;
        final int bottom = layoutManager.getDecoratedBottom(view) + params.bottomMargin;
        final int start = layoutManager.getPaddingTop();
        final int end = layoutManager.getHeight() - layoutManager.getPaddingBottom();
        return calculateDtToFit(top, bottom, start, end, SNAP_TO_START);
    }
}
