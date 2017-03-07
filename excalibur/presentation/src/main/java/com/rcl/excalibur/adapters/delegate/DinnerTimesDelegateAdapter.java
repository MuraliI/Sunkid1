package com.rcl.excalibur.adapters.delegate;

import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import com.rcl.excalibur.R;
import com.rcl.excalibur.adapters.base.DelegateAdapter;
import com.rcl.excalibur.adapters.viewtype.DinnerTimesViewType;
import com.rcl.excalibur.utils.RCViewUtils;

import butterknife.Bind;
import butterknife.ButterKnife;

public class DinnerTimesDelegateAdapter implements DelegateAdapter<
        DinnerTimesDelegateAdapter.TimesViewHolder,
        DinnerTimesViewType> {

    @Override
    public TimesViewHolder onCreateViewHolder(ViewGroup parent) {
        return new TimesViewHolder(parent);
    }

    @Override
    public void onBindViewHolder(TimesViewHolder holder, DinnerTimesViewType item) {
        holder.lunchTime.setText(item.getLunchTime());
        holder.dinnerTime.setText(item.getDinnerTime());
        // TODO: 7/03/17 Trigger intent to read the PDF
        holder.lunchMenu.setOnClickListener((view) -> Log.d(getClass().getSimpleName(), "Lunch menu opened"));
        holder.dinnerMenu.setOnClickListener((view) -> Log.d(getClass().getSimpleName(), "Dinner menu opened"));
    }

    static class TimesViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.text_module_lunch_time) TextView lunchTime;
        @Bind(R.id.text_module_lunch_menu) TextView lunchMenu;
        @Bind(R.id.text_module_dinner_time) TextView dinnerTime;
        @Bind(R.id.text_module_dinner_menu) TextView dinnerMenu;

        TimesViewHolder(ViewGroup parent) {
            super(LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.module_item_detail_dinner_times, parent, false));
            ButterKnife.bind(this, itemView);

            Drawable arrow = RCViewUtils.tintDrawable(parent.getContext(), R.drawable.ic_arrow_drop_down, R.color.detail_item_link);
            lunchMenu.setCompoundDrawablesWithIntrinsicBounds(null, null, arrow, null);
            dinnerMenu.setCompoundDrawablesWithIntrinsicBounds(null, null, arrow, null);
        }
    }
}
