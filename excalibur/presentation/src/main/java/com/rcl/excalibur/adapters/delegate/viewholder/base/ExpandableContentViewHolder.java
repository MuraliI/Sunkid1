package com.rcl.excalibur.adapters.delegate.viewholder.base;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewTreeObserver;

import com.rcl.excalibur.adapters.base.RecyclerViewType;
import com.rcl.excalibur.adapters.delegate.viewholder.ExpandableAccessibiltyViewHolder;

import java.lang.ref.WeakReference;


public abstract class ExpandableContentViewHolder<DVT extends RecyclerViewType> extends RecyclerView.ViewHolder {

    public interface OnViewExpandedListener<VT extends RecyclerViewType> {
        void onViewExpanded(VT viewType);
    }

    private WeakReference<OnViewExpandedListener<DVT>> listener;
    private DVT viewType;

    public ExpandableContentViewHolder(View itemView,
                                       ExpandableAccessibiltyViewHolder.OnViewExpandedListener<DVT> listener) {
        super(itemView);
        this.listener = new WeakReference<>(listener);
    }

    public DVT getViewType() {
        return viewType;
    }

    public void setViewType(DVT viewType) {
        this.viewType = viewType;
    }

    protected void notifyViewExpanded(boolean wasExpanded) {
        itemView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                itemView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                if (listener.get() != null && wasExpanded) {
                    listener.get().onViewExpanded(viewType);
                }
            }
        });
    }
}
