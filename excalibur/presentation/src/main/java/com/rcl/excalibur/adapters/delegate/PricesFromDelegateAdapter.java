package com.rcl.excalibur.adapters.delegate;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.rcl.excalibur.R;
import com.rcl.excalibur.adapters.base.DelegateAdapter;
import com.rcl.excalibur.adapters.viewtype.PricesFromViewType;

import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;


public class PricesFromDelegateAdapter implements DelegateAdapter<PricesFromDelegateAdapter.PricesFromViewHolder, PricesFromViewType> {

    @Override
    public PricesFromViewHolder onCreateViewHolder(ViewGroup parent) {
        return new PricesFromViewHolder(parent);
    }

    @Override
    public void onBindViewHolder(PricesFromViewHolder holder, PricesFromViewType item) {
        holder.textTitle.setText(item.getTitle());
        holder.textSubtitle.setText(item.getSubtitle());

        Context context = holder.itemView.getContext();

        for (Map.Entry pair : item.getPrices().entrySet()) {
            View itemView = LayoutInflater.from(context).inflate(R.layout.item_price, null);
            TextView text = (TextView) itemView.findViewById(R.id.text);
            TextView price = (TextView) itemView.findViewById(R.id.price);

            text.setText((CharSequence) pair.getKey());
            price.setText((CharSequence) pair.getValue());

            holder.pricesContainer.addView(itemView);
        }
    }


    public static class PricesFromViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.text_module_title)
        TextView textTitle;
        @Bind(R.id.text_module_subtitle)
        TextView textSubtitle;
        @Bind(R.id.prices_container)
        LinearLayout pricesContainer;

        public PricesFromViewHolder(ViewGroup parent) {
            super(LayoutInflater.from(parent.getContext()).inflate(R.layout.module_item_detail_prices_from, parent, false));
            ButterKnife.bind(this, itemView);
        }
    }
}
