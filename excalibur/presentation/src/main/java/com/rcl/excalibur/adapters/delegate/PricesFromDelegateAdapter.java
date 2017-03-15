package com.rcl.excalibur.adapters.delegate;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import com.rcl.excalibur.R;
import com.rcl.excalibur.adapters.base.DelegateAdapter;
import com.rcl.excalibur.adapters.viewtype.PricesFromViewType;

import butterknife.Bind;
import butterknife.ButterKnife;


public class PricesFromDelegateAdapter implements DelegateAdapter<PricesFromDelegateAdapter.PricesFromViewHolder, PricesFromViewType> {

    @Override
    public PricesFromViewHolder onCreateViewHolder(ViewGroup parent) {
        return new PricesFromViewHolder(parent);
    }

    @Override
    public void onBindViewHolder(PricesFromViewHolder holder, PricesFromViewType item) {
        holder.textAdult.setText(item.getAdultPrice());
        holder.textChildren.setText(item.getChildrenPrice());
    }


    public static class PricesFromViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.prices_from_adult_text) TextView textAdult;
        @Bind(R.id.prices_from_children_text) TextView textChildren;

        public PricesFromViewHolder(ViewGroup parent) {
            super(LayoutInflater.from(parent.getContext()).inflate(R.layout.module_item_detail_prices_from, parent, false));
            ButterKnife.bind(this, itemView);
        }
    }
}
