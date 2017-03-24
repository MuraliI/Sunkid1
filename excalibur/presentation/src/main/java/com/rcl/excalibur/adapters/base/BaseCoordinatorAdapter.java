package com.rcl.excalibur.adapters.base;

import android.support.v4.util.SparseArrayCompat;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;


public class BaseCoordinatorAdapter<VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<VH> {

    protected SparseArrayCompat<DelegateAdapter<VH, RecyclerViewType>> delegateAdapters;
    protected List<RecyclerViewType> items;

    private WeakReference<Observer> observerRef;

    public BaseCoordinatorAdapter(final Observer observer) {
        this.observerRef = new WeakReference<>(observer);
        items = new ArrayList<>();
    }

    protected boolean hasObserver() {
        return observerRef.get() != null;
    }

    protected Observer getObserver() {
        return observerRef.get();
    }

    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        return delegateAdapters.get(viewType).onCreateViewHolder(parent);
    }

    @Override
    public void onBindViewHolder(VH holder, int position) {
        RecyclerViewType viewType = items.get(position);
        DelegateAdapter<VH, RecyclerViewType> delegateAdapter = delegateAdapters.get(viewType.getViewType());
        if (delegateAdapter == null) {
            throw new RuntimeException("No delegate adapter for " + viewType.getViewType());
        } else {
            delegateAdapter.onBindViewHolder(holder, viewType);
        }
    }

    @Override
    public int getItemViewType(int position) {
        return items.get(position).getViewType();
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void addAll(List<RecyclerViewType> items) {
        this.items.addAll(items);
        notifyDataSetChanged();
    }

    public void addViewTypeOnceAndNotify(RecyclerViewType viewType) {
        items.add(viewType);
        notifyItemInserted(items.indexOf(viewType));
    }

    public void clearItemsAndNotify() {
        items.clear();
        notifyDataSetChanged();
    }
}
