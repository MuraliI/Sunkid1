package com.rcl.excalibur.adapters.delegate;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.rcl.excalibur.R;
import com.rcl.excalibur.adapters.base.DelegateAdapter;
import com.rcl.excalibur.adapters.viewtype.StandardTimesViewType;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class StandardTimesDelegateAdapter implements DelegateAdapter<StandardTimesDelegateAdapter.StandardTimesViewHolder,
        StandardTimesViewType> {

    private static final String SEPARATION = "\n\n";
    private static final int THRESHOLD = 3;

    private static String[] buildDaysAndTimesStrings(List<String[]> daysAndTimes, final int size) {
        StringBuilder daysBuilder = new StringBuilder();
        StringBuilder timesBuilder = new StringBuilder();

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

    @Override
    public StandardTimesViewHolder onCreateViewHolder(ViewGroup parent) {
        return new StandardTimesViewHolder(parent);
    }

    @Override
    public void onBindViewHolder(StandardTimesViewHolder holder, StandardTimesViewType item) {
        int count = item.getDaysAndTimes().size();
        boolean valid = count <= THRESHOLD;
        holder.linkContainer.setVisibility(valid ? View.GONE : View.VISIBLE);
        holder.expanded = valid;
        holder.daysAndTimes = item.getDaysAndTimes();

        int size = valid ? count : THRESHOLD;
        String[] daysAndTimes = buildDaysAndTimesStrings(holder.daysAndTimes, size);
        holder.title.setText(item.getTitle());
        holder.days.setText(daysAndTimes[0]);
        holder.times.setText(daysAndTimes[1]);
    }

    static class StandardTimesViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.text_module_standard_times_title) TextView title;
        @Bind(R.id.text_module_standard_times_days) TextView days;
        @Bind(R.id.text_module_standard_times_times) TextView times;
        @Bind(R.id.link_container) LinearLayout linkContainer;
        @Bind(R.id.link_title) TextView linkTitle;
        @Bind(R.id.link_arrow) ImageView linkArrow;
        boolean expanded;
        List<String[]> daysAndTimes;

        StandardTimesViewHolder(ViewGroup parent) {
            super(LayoutInflater.from(parent.getContext()).inflate(R.layout.module_item_detail_standard_times, parent, false));
            ButterKnife.bind(this, itemView);
        }


        @OnClick(R.id.link_container)
        void onLinkClick() {
            change();
        }

        private void change() {
            linkTitle.setText(expanded ? R.string.discover_item_detail_see_more_btn
                    : R.string.discover_item_detail_description_expanded_text);
            linkArrow.setImageResource(expanded ? R.drawable.ic_arrow_drop_down : R.drawable.ic_arrow_drop_up);
            int size = expanded ? THRESHOLD : this.daysAndTimes.size();

            final String[] daysAndTimes = buildDaysAndTimesStrings(this.daysAndTimes, size);
            days.setText(daysAndTimes[0]);
            times.setText(daysAndTimes[1]);
            expanded = !expanded;

        }

    }
}
