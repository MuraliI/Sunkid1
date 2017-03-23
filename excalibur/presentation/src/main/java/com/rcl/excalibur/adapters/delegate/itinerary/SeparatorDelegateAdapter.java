package com.rcl.excalibur.adapters.delegate.itinerary;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import com.rcl.excalibur.R;
import com.rcl.excalibur.adapters.base.DelegateAdapter;
import com.rcl.excalibur.model.itinerary.ItinerarySeparatorModel;

import butterknife.Bind;
import butterknife.ButterKnife;

public class SeparatorDelegateAdapter implements DelegateAdapter<
        SeparatorDelegateAdapter.CalendarSeparatorViewHolder,
        ItinerarySeparatorModel> {

    @Override
    public CalendarSeparatorViewHolder onCreateViewHolder(ViewGroup parent) {
        return new CalendarSeparatorViewHolder(parent);
    }

    @Override
    public void onBindViewHolder(CalendarSeparatorViewHolder holder, ItinerarySeparatorModel item) {
        holder.textHour.setText(item.getLabel());
    }

    class CalendarSeparatorViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.text_hour)
        TextView textHour;

        CalendarSeparatorViewHolder(ViewGroup parent) {
            super(LayoutInflater.from(parent.getContext()).inflate(R.layout.calendar_item_separator, parent, false));
            ButterKnife.bind(this, itemView);
        }
    }
}
