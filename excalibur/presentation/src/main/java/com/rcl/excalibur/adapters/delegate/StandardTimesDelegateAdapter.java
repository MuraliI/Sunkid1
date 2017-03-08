package com.rcl.excalibur.adapters.delegate;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import com.rcl.excalibur.R;
import com.rcl.excalibur.adapters.base.DelegateAdapter;
import com.rcl.excalibur.adapters.viewtype.StandardTimesViewType;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class StandardTimesDelegateAdapter implements DelegateAdapter<StandardTimesDelegateAdapter.StandardTimesViewHolder,
        StandardTimesViewType> {
    private static final String SEPARATION = "\n\n";

    @Override
    public StandardTimesViewHolder onCreateViewHolder(ViewGroup parent) {
        return new StandardTimesViewHolder(parent);
    }

    @Override
    public void onBindViewHolder(StandardTimesViewHolder holder, StandardTimesViewType item) {
        String[] daysAndTimes = buildDaysAndTimesStrings(item);
        holder.title.setText(item.getTitle());
        holder.days.setText(daysAndTimes[0]);
        holder.times.setText(daysAndTimes[1]);
    }

    private String[] buildDaysAndTimesStrings(StandardTimesViewType item) {
        StringBuilder daysBuilder = new StringBuilder();
        StringBuilder timesBuilder = new StringBuilder();

        List<String[]> daysAndTimes = item.getDaysAndTimes();
        int size = daysAndTimes.size();
        for (int index = 0; index < size; index++) {
            String[] pair = daysAndTimes.get(index);
            daysBuilder.append(pair[0]);
            timesBuilder.append(pair[1]);

            if (index < size - 1) {
                daysBuilder.append(SEPARATION);
                timesBuilder.append(SEPARATION);
            }
        }
        return new String[]{
                daysBuilder.toString(),
                timesBuilder.toString()
        };
    }

    static class StandardTimesViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.text_module_standard_times_title) TextView title;
        @Bind(R.id.text_module_standard_times_days) TextView days;
        @Bind(R.id.text_module_standard_times_times) TextView times;

        StandardTimesViewHolder(ViewGroup parent) {
            super(LayoutInflater.from(parent.getContext()).inflate(R.layout.module_item_detail_standard_times, parent, false));
            ButterKnife.bind(this, itemView);
        }
    }
}
