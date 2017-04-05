package com.rcl.excalibur.adapters;


import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;

import static com.rcl.excalibur.data.utils.CollectionUtils.isEmpty;


public abstract class BaseAdapter<T, VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<VH> {
    private WeakReference<Observer<T>> observerRef;
    protected List<T> items;

    public BaseAdapter(final Observer<T> observer) {
        this.observerRef = new WeakReference<>(observer);
        this.items = new ArrayList<>();
    }

    boolean hasObserver() {
        return observerRef.get() != null;
    }

    Observer<T> getObserver() {
        return observerRef.get();
    }

    public void removeAll() {
        items.clear();
        notifyDataSetChanged();
    }

    public void addAll(List<T> list) {
        if (isEmpty(list)) {
            return;
        }
        items.clear();
        items.addAll(list);
        notifyDataSetChanged();
    }

    public void add(T item) {
        items.add(item);
        notifyItemInserted(items.size() - 1);
    }

    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(getLayout(), parent, false);
        return getViewHolder(view);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @LayoutRes
    protected abstract int getLayout();

    @NonNull
    protected abstract VH getViewHolder(View view);
}
