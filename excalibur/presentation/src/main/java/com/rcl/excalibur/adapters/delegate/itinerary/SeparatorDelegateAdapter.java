package com.rcl.excalibur.adapters.delegate.itinerary;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.rcl.excalibur.R;
import com.rcl.excalibur.adapters.base.DelegateAdapter;
import com.rcl.excalibur.model.itinerary.SeparatorModel;

import butterknife.ButterKnife;

public class SeparatorDelegateAdapter implements DelegateAdapter<
        SeparatorDelegateAdapter.SeparatorViewHolder,
        SeparatorModel> {

    @Override
    public SeparatorViewHolder onCreateViewHolder(ViewGroup parent) {
        return new SeparatorViewHolder(parent);
    }

    @Override
    public void onBindViewHolder(SeparatorViewHolder holder, SeparatorModel item) {
    }

    class SeparatorViewHolder extends RecyclerView.ViewHolder {

        SeparatorViewHolder(ViewGroup parent) {
            super(LayoutInflater.from(parent.getContext()).inflate(R.layout.itinerary_item_separator, parent, false));
            ButterKnife.bind(this, itemView);
        }
    }
}
