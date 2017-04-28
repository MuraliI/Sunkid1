package com.rcl.excalibur.custom.view;

import android.content.Context;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Pair;
import android.widget.FrameLayout;

import com.rcl.excalibur.R;
import com.rcl.excalibur.custom.adapter.HorizontalPickerViewAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class HorizontalPickerView<T> extends FrameLayout {

    @BindView(R.id.recycler_view_deck_list) RecyclerView deckSelector;

    private LinearLayoutManager layoutManager;
    private HorizontalPickerViewAdapter<T> horizontalPickerViewAdapter;


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

    public void setLayoutManager(LinearLayoutManager layoutManager) {
        if (layoutManager != null) {
            this.layoutManager = layoutManager;
            this.layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
            deckSelector.setLayoutManager(layoutManager);
        }
    }
}
