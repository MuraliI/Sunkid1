package com.rcl.excalibur.adapters;


import android.support.v7.widget.RecyclerView;

import java.lang.ref.WeakReference;

import io.reactivex.Observer;


public abstract class BaseAdapter<VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<VH> {
    private WeakReference<Observer> observerRef;

    public BaseAdapter(final Observer observer) {
        this.observerRef = new WeakReference(observer);
    }

    protected boolean hasObserver() {
        return observerRef.get() != null;
    }

    protected Observer getObserver() {
        return observerRef.get();
    }

}
