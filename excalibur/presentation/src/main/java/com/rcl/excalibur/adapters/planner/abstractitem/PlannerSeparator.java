package com.rcl.excalibur.adapters.planner.abstractitem;

import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.rcl.excalibur.R;
import com.rcl.excalibur.domain.utils.ConstantsUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import eu.davidea.flexibleadapter.FlexibleAdapter;
import eu.davidea.flexibleadapter.items.AbstractSectionableItem;
import eu.davidea.viewholders.FlexibleViewHolder;

public class PlannerSeparator extends AbstractSectionableItem<PlannerSeparator.ViewHolder, PlannerHeader> {

    private int id;
    private String timeLabel;

    public PlannerSeparator(int id, PlannerHeader header) {
        super(header);
        this.id = id;
    }

    public PlannerSeparator(int id, String timeLabel, PlannerHeader header) {
        super(header);
        this.id = id;
        this.timeLabel = timeLabel;
    }

    @Override
    public int getLayoutRes() {
        return R.layout.planner_item_hour_separator;
    }

    @Override
    public ViewHolder createViewHolder(FlexibleAdapter adapter, LayoutInflater inflater, ViewGroup parent) {
        return new ViewHolder(inflater.inflate(getLayoutRes(), parent, false), adapter);
    }

    @Override
    public void bindViewHolder(FlexibleAdapter adapter, ViewHolder holder, int position, List payloads) {
        holder.setTime(timeLabel);
    }

    @Override
    public boolean equals(Object object) {
        if (object == null) {
            return false;
        }
        if (object instanceof PlannerSeparator) {
            PlannerSeparator other = (PlannerSeparator) object;
            return this.id == other.id;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return id;
    }

    public class ViewHolder extends FlexibleViewHolder {

        @BindView(R.id.text_planner_separator_time) TextView timeTextView;
        @BindView(R.id.view_planner_separator_extra_line) View extraLine;

        public ViewHolder(View view, FlexibleAdapter adapter) {
            super(view, adapter);
            ButterKnife.bind(this, itemView);
        }

        public void setTime(@Nullable String time) {
            if (time == null || time.length() == 0) {
                timeTextView.setText(ConstantsUtil.EMPTY);
                extraLine.setVisibility(View.INVISIBLE);
            } else {
                timeTextView.setText(time);
                extraLine.setVisibility(View.VISIBLE);
            }
        }
    }
}
