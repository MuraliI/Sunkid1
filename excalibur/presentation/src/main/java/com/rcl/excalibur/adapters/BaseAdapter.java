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
    protected List<T> items;
    private WeakReference<Observer<T>> observerRef;

    public BaseAdapter(final Observer<T> observer) {
        this.observerRef = new WeakReference<>(observer);
        this.items = new ArrayList<>();
    }

    protected void onNext(T value) {
        final Observer<T> observer = observerRef.get();
        if (observer == null) {
            return;
        }
        observer.onNext(value);
    }

    boolean hasObserver() {
        return observerRef.get() != null;
    }

    Observer<T> getObserver() {
        return observerRef.get();
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

    public void add(T item) {
        items.add(item);
        notifyItemInserted(items.size() - 1);
    }

    public void add(int position, T item) {
        items.add(position, item);
        notifyItemInserted(position);
    }

    public void add(int position, List<T> newItems) {
        items.addAll(position, newItems);
        notifyItemRangeInserted(position, newItems.size());
    }

    public void add(List<Integer> positions, List<T> newItems) {
        for (int i = 0; i < newItems.size(); i++) {
            int positionToInsert = positions.get(i);

            items.add(positionToInsert, newItems.get(i));
            notifyItemInserted(positionToInsert);
        }
    }

    public void addAll(List<T> list) {
        if (isEmpty(list)) {
            return;
        }
        items.clear();
        items.addAll(list);
        notifyDataSetChanged();
    }

    public void remove(int position) {
        items.remove(position);
        notifyItemRemoved(position);
    }

    public void remove(int position, List<T> newItems) {
        items.removeAll(newItems);
        notifyItemRangeRemoved(position, newItems.size());
    }

    public void remove(List<Integer> positions, List<T> newItems) {
        for (int i = newItems.size() - 1; i >= 0; i--) {
            items.remove(newItems.get(i));
            notifyItemRemoved(positions.get(i));
        }
    }

    public void removeAll() {
        items.clear();
        notifyDataSetChanged();
    }
}
