package com.rcl.excalibur.adapters.planner.abstractitem;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.rcl.excalibur.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import eu.davidea.flexibleadapter.FlexibleAdapter;
import eu.davidea.flexibleadapter.items.AbstractHeaderItem;
import eu.davidea.viewholders.FlexibleViewHolder;

public class PlannerHeader extends AbstractHeaderItem<PlannerHeader.HeaderViewHolder> {

    private String id;
    private String title;

    public PlannerHeader(String id) {
        super();
        this.id = id;
    }

    @Override
    public boolean equals(Object inObject) {
        if (inObject instanceof PlannerHeader) {
            PlannerHeader inItem = (PlannerHeader) inObject;
            return this.getId().equals(inItem.getId());
        }
        return false;
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public int getLayoutRes() {
        return R.layout.planner_item_header;
    }

    @Override
    public HeaderViewHolder createViewHolder(FlexibleAdapter adapter, LayoutInflater inflater, ViewGroup parent) {
        return new HeaderViewHolder(inflater.inflate(getLayoutRes(), parent, false), adapter);
    }

    @Override
    public void bindViewHolder(FlexibleAdapter adapter, HeaderViewHolder holder, int position, List payloads) {
        holder.partOfDayText.setText(getTitle());
    }

    class HeaderViewHolder extends FlexibleViewHolder {
        @BindView(R.id.text_part_of_day) TextView partOfDayText;

        HeaderViewHolder(View view, FlexibleAdapter adapter) {
            super(view, adapter, true);
            ButterKnife.bind(this, itemView);
        }
    }
}
