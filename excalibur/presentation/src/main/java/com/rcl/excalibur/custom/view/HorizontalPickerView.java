package com.rcl.excalibur.custom.view;

import android.content.Context;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Pair;
import android.view.View;
import android.widget.FrameLayout;

import com.rcl.excalibur.R;
import com.rcl.excalibur.custom.view.adapter.HorizontalPickerViewAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observer;


public class HorizontalPickerView<T> extends FrameLayout {

    public static final int HIDE_ANIMATION_DURATION = 500;
    public static final int ORIGINAL_TRANSLATION_Y = 0;

    @BindView(R.id.recycler_view_deck_list) RecyclerView deckSelector;

    private LinearLayoutManager layoutManager;
    private HorizontalPickerViewAdapter<T> horizontalPickerViewAdapter;

    private boolean isDeckSelectorShown;

    public HorizontalPickerView(@NonNull Context context) {
        super(context);
    }

    public HorizontalPickerView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public HorizontalPickerView(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        ButterKnife.bind(this);
        layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        deckSelector.setLayoutManager(layoutManager);
    }

    public void setItems(List<Pair<Integer, T>> objectList) {
        if (objectList != null) {
            if (horizontalPickerViewAdapter == null) {
                horizontalPickerViewAdapter = new HorizontalPickerViewAdapter<>(objectList);
                deckSelector.setAdapter(horizontalPickerViewAdapter);
            } else {
                horizontalPickerViewAdapter.setItems(objectList);
            }
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setTranslationY(isDeckSelectorShown ? ORIGINAL_TRANSLATION_Y : -getMeasuredHeight());
    }

    public void setLayoutManager(LinearLayoutManager layoutManager) {
        if (layoutManager != null) {
            this.layoutManager = layoutManager;
            this.layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
            deckSelector.setLayoutManager(layoutManager);
        }
    }

    public void subscribeToItemClick(Observer<Pair<Integer, T>> observer) {
        horizontalPickerViewAdapter.setItemClickObserver(observer);
    }

    public void setSelectedItem(Pair<Integer, T> item) {
        int position = horizontalPickerViewAdapter.getItems().indexOf(item);
        if (layoutManager.getChildCount() == 0) {
            horizontalPickerViewAdapter.setSelectedItem(item);
            deckSelector.smoothScrollToPosition(position);
        } else {
            View view = layoutManager.findViewByPosition(position);
            view.setFocusableInTouchMode(true);
            view.requestFocus();
            view.setOnFocusChangeListener((focusedView, hasFocus) -> {
                focusedView.setFocusableInTouchMode(hasFocus);
                focusedView.setOnFocusChangeListener(null);
            });
            if (position >= layoutManager.findFirstVisibleItemPosition() && position <= layoutManager.findLastVisibleItemPosition()) {
                scrollToRecyclerItem(view);
            }
        }
    }

    private void scrollToRecyclerItem(View item) {
        int itemWidth = item.getWidth();
        int parentWidth = getWidth();
        int calculatedScroll = item.getLeft() - ((parentWidth / 2) - (itemWidth / 2));
        deckSelector.smoothScrollBy(calculatedScroll, 0);
    }

    @Override
    public void setVisibility(int visibility) {
        isDeckSelectorShown = !(visibility == View.GONE);
        float translation = isDeckSelectorShown ? ORIGINAL_TRANSLATION_Y : -getHeight();
        animate().translationY(translation).setDuration(HIDE_ANIMATION_DURATION).start();
    }
}
