package com.rcl.excalibur.adapters.delegate;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import com.rcl.excalibur.R;
import com.rcl.excalibur.adapters.base.DelegateAdapter;
import com.rcl.excalibur.adapters.viewtype.DinnerTimesViewType;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DinnerTimesDelegateAdapter implements DelegateAdapter<DinnerTimesDelegateAdapter.TimesViewHolder, DinnerTimesViewType> {

    @Override
    public TimesViewHolder onCreateViewHolder(ViewGroup parent) {
        return new TimesViewHolder(parent);
    }

    @Override
    public void onBindViewHolder(TimesViewHolder holder, DinnerTimesViewType item) {
        holder.lunchTime.setText(item.getLunchTime());
        holder.dinnerTime.setText(item.getDinnerTime());
    }

    static class TimesViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.text_module_lunch_time) TextView lunchTime;
        @Bind(R.id.text_module_dinner_time) TextView dinnerTime;

        TimesViewHolder(ViewGroup parent) {
            super(LayoutInflater.from(parent.getContext()).inflate(R.layout.module_item_detail_dinner_times, parent, false));
            ButterKnife.bind(this, itemView);
        }

        @OnClick(R.id.discover_detail_lunch_menu)
        void clickLunchMenu() {
            // TODO: 7/03/17 Change it to launch the intent to open the PDF
            Log.d(getClass().getSimpleName(), "Lunch menu opened");
        }

        @OnClick(R.id.discover_detail_dinner_menu)
        void clickDinnerMenu() {
            // TODO: 7/03/17 Change it to launch the intent to open the PDF
            Log.d(getClass().getSimpleName(), "Dinner menu opened");
        }
    }
}
