package com.rcl.excalibur.adapters.delegate;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.rcl.excalibur.R;
import com.rcl.excalibur.adapters.base.DelegateAdapter;
import com.rcl.excalibur.adapters.viewtype.PricesFromViewType;

import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;

public class TimesDelegateAdapter implements DelegateAdapter<TimesDelegateAdapter.TimesViewHolder, PricesFromViewType> {

    @Override
    public TimesViewHolder onCreateViewHolder(ViewGroup parent) {
        return new TimesViewHolder(parent);
    }

    @Override
    public void onBindViewHolder(TimesViewHolder holder, PricesFromViewType item) {
        Context context = holder.itemView.getContext();
        holder.textTitle.setText(item.getTitle());

        for (Map.Entry pair : item.getPrices().entrySet()) {
            View itemView = LayoutInflater.from(context).inflate(R.layout.item_text_dash_text, null);
            TextView text = (TextView) itemView.findViewById(R.id.text);
            TextView price = (TextView) itemView.findViewById(R.id.price);
            text.setText((CharSequence) pair.getKey());
            price.setText((CharSequence) pair.getValue());
            holder.timesContainer.addView(itemView);
        }
    }

    public static class TimesViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.text_module_title) TextView textTitle;
        @Bind(R.id.times_container) LinearLayout timesContainer;
        @Bind(R.id.show_more_container) LinearLayout showMoreContainer;
        @Bind(R.id.show_more_arrow) ImageView showMoreArrow;

        public TimesViewHolder(ViewGroup parent) {
            super(LayoutInflater.from(parent.getContext()).inflate(R.layout.module_item_detail_times, parent, false));
            ButterKnife.bind(this, itemView);
        }
    }
}

