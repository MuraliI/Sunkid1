package com.rcl.excalibur.adapters.base;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

public abstract class LoadMoreScrollListener extends RecyclerView.OnScrollListener {
    private static final int INITIAL_THRESHOLD = 5;
    /**
     * The minimum amount of items to have below your current scroll position
     * before loading more.
     */
    private int visibleThreshold = INITIAL_THRESHOLD;
    /**
     * The amount of item on each load.
     */
    public static final int MAX_COUNT = 10;
    /**
     * The current offset index of data you have loaded.
     */
    private int currentPage = 0;
    /**
     * The total number of items in the dataset after the last load.
     */
    private int previousTotalItemCount = 0;

    private boolean loading = true;
    private int startingPageIndex = 0;

    RecyclerView.LayoutManager mLayoutManager;

    public LoadMoreScrollListener(RecyclerView.LayoutManager layoutManager, int initPage) {
        this.mLayoutManager = layoutManager;
        this.startingPageIndex = initPage;
        this.currentPage = initPage;
    }

    public int getLastVisibleItem(int[] lastVisibleItemPositions) {
        int maxSize = 0;
        for (int i = 0; i < lastVisibleItemPositions.length; i++) {
            if (i == 0) {
                maxSize = lastVisibleItemPositions[i];
            } else if (lastVisibleItemPositions[i] > maxSize) {
                maxSize = lastVisibleItemPositions[i];
            }
        }
        return maxSize;
    }

    @Override
    public void onScrolled(RecyclerView view, int dx, int dy) {
        int lastVisibleItemPosition = 0;
        int totalItemCount = mLayoutManager.getItemCount();

        lastVisibleItemPosition = ((LinearLayoutManager) mLayoutManager).findLastVisibleItemPosition();

        if (totalItemCount < previousTotalItemCount) {
            this.currentPage = this.startingPageIndex;
            this.previousTotalItemCount = totalItemCount;
            if (totalItemCount == 0) {
                this.loading = true;
            }
        }

        if (loading && (totalItemCount > previousTotalItemCount)) {
            loading = false;
            previousTotalItemCount = totalItemCount;
        }

        if (!loading && (lastVisibleItemPosition + visibleThreshold) > totalItemCount) {
            currentPage++;
            onLoadMore(currentPage, totalItemCount, view);
            loading = true;
        }
    }

    public void resetState() {
        this.currentPage = this.startingPageIndex;
        this.previousTotalItemCount = 0;
        this.loading = true;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public abstract void onLoadMore(int page, int totalItemsCount, RecyclerView view);

}
