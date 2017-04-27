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
import com.rcl.excalibur.adapters.viewtype.TimesViewType;

import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TimesDelegateAdapter implements DelegateAdapter<TimesDelegateAdapter.TimesViewHolder, TimesViewType> {

    @Override
    public TimesViewHolder onCreateViewHolder(ViewGroup parent) {
        return new TimesViewHolder(parent);
    }

    @Override
    public void onBindViewHolder(TimesViewHolder holder, TimesViewType item) {
        Context context = holder.itemView.getContext();
        holder.textTitle.setText(item.getTitle());

        for (Map.Entry pair : item.getTimes().entrySet()) {
            View itemView = LayoutInflater.from(context).inflate(R.layout.item_text_dash_text, null);
            TextView text = (TextView) itemView.findViewById(R.id.text);
            TextView subtext = (TextView) itemView.findViewById(R.id.subtext);
            text.setText((CharSequence) pair.getKey());
            subtext.setText((CharSequence) pair.getValue());
            holder.timesContainer.addView(itemView);
        }
    }

    public static class TimesViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.text_module_title) TextView textTitle;
        @BindView(R.id.times_container) LinearLayout timesContainer;
        @BindView(R.id.show_more_container) LinearLayout showMoreContainer;
        @BindView(R.id.show_more_arrow) ImageView showMoreArrow;

        public TimesViewHolder(ViewGroup parent) {
            super(LayoutInflater.from(parent.getContext()).inflate(R.layout.module_item_detail_times, parent, false));
            ButterKnife.bind(this, itemView);
        }
    }
}

