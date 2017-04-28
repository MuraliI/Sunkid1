package com.rcl.excalibur.custom.adapter;


import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import com.rcl.excalibur.R;

import java.util.List;


public class HorizontalPickerViewAdapter<T> extends RecyclerView.Adapter<HorizontalPickerViewAdapter.HorizontalDeckPickerViewHolder> {

    private List<Pair<Integer, T>> items;

    public HorizontalPickerViewAdapter(@NonNull List<Pair<Integer, T>> items) {
        this.items = items;
    }

    @Override
    public HorizontalPickerViewAdapter.HorizontalDeckPickerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new HorizontalDeckPickerViewHolder(parent);
    }

    @Override
    public void onBindViewHolder(HorizontalPickerViewAdapter.HorizontalDeckPickerViewHolder holder, int position) {
        ((TextView) holder.itemView).setText(String.valueOf(items.get(position).first));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void setItems(@NonNull List<Pair<Integer, T>> items) {
        this.items = items;
        notifyDataSetChanged();
    }

    class HorizontalDeckPickerViewHolder extends RecyclerView.ViewHolder {

        HorizontalDeckPickerViewHolder(ViewGroup parent) {
            super(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_deck_selector_textview, parent, false));
        }
    }
}
