package com.rcl.excalibur.adapters.planner.abstractitem;


import android.support.annotation.StringRes;
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

    @StringRes private int title;
    private String id;

    private boolean isAllDayHeader;
    private boolean isSectionExpanded;

    public PlannerHeader(String id) {
        super();
        this.id = id;
    }

    @Override
    public boolean equals(Object inObject) {
        if (inObject == null) {
            return false;
        }
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

    @StringRes
    public int getTitle() {
        return title;
    }

    public void setTitle(@StringRes int title) {
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
        if (isSectionExpanded()) {
            holder.expand();
        } else {
            holder.collapse();
        }
    }

    class HeaderViewHolder extends FlexibleViewHolder {

        @BindView(R.id.text_planner_part_of_day) TextView partOfDayText;
        @BindView(R.id.text_planner_expandable_label) TextView expandableText;
        HeaderViewHolder(View view, FlexibleAdapter adapter) {
            super(view, adapter, true);
            ButterKnife.bind(this, itemView);
        }

        void expand() {
            expandableText.setText(R.string.planner_header_see_less);
            expandableText.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_chevron_up, 0);
        }

        void collapse() {
            expandableText.setText(R.string.planner_header_see_all);
            expandableText.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_chevron_down, 0);
        }

    }

    public boolean isAllDayHeader() {
        return isAllDayHeader;
    }

    public void setAllDayHeader(boolean allDayHeader) {
        isAllDayHeader = allDayHeader;
    }

    private boolean isSectionExpanded() {
        return isSectionExpanded;
    }

    public void setSectionExpanded(boolean sectionExpanded) {
        isSectionExpanded = sectionExpanded;
    }
}
