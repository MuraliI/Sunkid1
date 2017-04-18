package com.rcl.excalibur.adapters.delegate.planner;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import com.rcl.excalibur.R;
import com.rcl.excalibur.adapters.base.DelegateAdapter;
import com.rcl.excalibur.adapters.viewtype.planner.PlannerHeaderViewType;

import butterknife.Bind;
import butterknife.ButterKnife;

public class PlannerHeaderDelegateAdapter implements DelegateAdapter<PlannerHeaderDelegateAdapter.ExpandableHeaderViewHolder,
        PlannerHeaderViewType> {

    @Override
    public ExpandableHeaderViewHolder onCreateViewHolder(ViewGroup parent) {
        return new ExpandableHeaderViewHolder(parent);
    }

    @Override
    public void onBindViewHolder(ExpandableHeaderViewHolder holder, PlannerHeaderViewType item) {
        holder.partOfDayText.setText(item.getLabel());
    }

    class ExpandableHeaderViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.text_planner_part_of_day) TextView partOfDayText;
        @Bind(R.id.text_planner_expandable_label) TextView expandableText;

        ExpandableHeaderViewHolder(ViewGroup parent) {
            super(LayoutInflater.from(parent.getContext()).inflate(R.layout.planner_item_header, parent, false));
            ButterKnife.bind(this, itemView);
        }

        public void expand() {
            expandableText.setText(R.string.itinerary_product_list_see_less);
            expandableText.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_chevron_up, 0);
        }

        public void collapse() {
            expandableText.setText(R.string.itinerary_product_list_see_all);
            expandableText.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_chevron_down, 0);
        }
    }
}
