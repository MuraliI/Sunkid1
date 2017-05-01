package com.rcl.excalibur.adapters.delegate;

import android.content.Context;
import android.support.v4.util.Pair;
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

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TimesDelegateAdapter implements DelegateAdapter<TimesDelegateAdapter.TimesViewHolder, TimesViewType> {

    @Override
    public TimesViewHolder onCreateViewHolder(ViewGroup parent) {
        return new TimesViewHolder(parent);
    }

    @Override
    public void onBindViewHolder(TimesViewHolder holder, TimesViewType item) {
        Context context = holder.itemView.getContext();
        holder.textTitle.setText(item.getTitle());

        for (Pair<String, String> pair : item.getTimes()) {
            View itemView = LayoutInflater.from(context).inflate(R.layout.item_text_dash_text, null);
            TextView text = (TextView) itemView.findViewById(R.id.text);
            TextView subtext = (TextView) itemView.findViewById(R.id.subtext);
            text.setText(context.getResources().getString(R.string.day_arg, pair.first));
            subtext.setText(pair.second);
            holder.timesContainer.addView(itemView);
        }

        holder.showMoreContainer.setVisibility(item.getTimes().size() > 1 ? View.VISIBLE : View.INVISIBLE);
        holder.collapseOrExpandContent(true);
    }

    public static class TimesViewHolder extends RecyclerView.ViewHolder {

        private boolean collapsed = true;

        @BindView(R.id.text_module_title) TextView textTitle;
        @BindView(R.id.times_container) LinearLayout timesContainer;
        @BindView(R.id.show_more_container) LinearLayout showMoreContainer;
        @BindView(R.id.show_more_arrow) ImageView showMoreArrow;

        public TimesViewHolder(ViewGroup parent) {
            super(LayoutInflater.from(parent.getContext()).inflate(R.layout.module_item_detail_times, parent, false));
            ButterKnife.bind(this, itemView);
        }

        @OnClick(R.id.show_more_container)
        public void showMoreClick() {
            collapsed = !collapsed;
            collapseOrExpandContent(collapsed);
        }

        public void collapseOrExpandContent(boolean collapse) {
            for (int i = 0; i < timesContainer.getChildCount(); i++) {
                View v = timesContainer.getChildAt(i);
                if (i > 0) {
                    v.setVisibility(collapse ? View.GONE : View.VISIBLE);
                }
            }
            showMoreArrow.setImageResource(collapsed ? R.drawable.ic_chevron_down : R.drawable.ic_chevron_up);
        }
    }
}

