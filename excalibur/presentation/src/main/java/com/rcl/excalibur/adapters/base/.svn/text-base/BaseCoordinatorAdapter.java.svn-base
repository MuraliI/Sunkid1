package com.rcl.excalibur.adapters.base;

import android.support.v4.util.SparseArrayCompat;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;


public class BaseCoordinatorAdapter<VH extends RecyclerView.ViewHolder, VT extends RecyclerViewType, T> extends RecyclerView.Adapter<VH> {

    protected SparseArrayCompat<DelegateAdapter<VH, VT>> delegateAdapters;
    protected List<VT> items;

    private WeakReference<Observer<T>> observerRef;

    public BaseCoordinatorAdapter(final Observer<T> observer) {
        this.observerRef = new WeakReference<>(observer);
        items = new ArrayList<>();
    }

    protected boolean hasObserver() {
        return observerRef.get() != null;
    }

    protected Observer<T> getObserver() {
        return observerRef.get();
    }

    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        return delegateAdapters.get(viewType).onCreateViewHolder(parent);
    }

    @Override
    public void onBindViewHolder(VH holder, int position) {
        VT viewType = items.get(position);
        DelegateAdapter<VH, VT> delegateAdapter = delegateAdapters.get(viewType.getViewType());
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

    public void addAll(List<VT> items) {
        this.items.clear();
        this.items.addAll(items);
        notifyDataSetChanged();
    }
}
