package com.rcl.excalibur.adapters.base;


import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

public interface DelegateAdapter<VH extends RecyclerView.ViewHolder, T extends RecyclerViewType> {

    VH onCreateViewHolder(ViewGroup parent);

    void onBindViewHolder(VH holder, T item);
}
