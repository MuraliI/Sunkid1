package com.rcl.excalibur.adapters.delegate.itinerary;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.rcl.excalibur.R;
import com.rcl.excalibur.adapters.base.DelegateAdapter;
import com.rcl.excalibur.adapters.viewtype.itinerary.ExpandableHeaderViewType;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ExpandableHeaderDelegateAdapter implements DelegateAdapter<ExpandableHeaderDelegateAdapter.ExpandableHeaderViewHolder,
        ExpandableHeaderViewType> {

    @Override
    public ExpandableHeaderViewHolder onCreateViewHolder(ViewGroup parent) {
        return new ExpandableHeaderViewHolder(parent);
    }

    @Override
    public void onBindViewHolder(ExpandableHeaderViewHolder holder, ExpandableHeaderViewType item) {
        holder.partOfDayText.setText(item.getLabel());
    }

    class ExpandableHeaderViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.text_part_of_day) TextView partOfDayText;
        @Bind(R.id.text_see_all) TextView seeAllText;
        @Bind(R.id.image_dropdown_arrow) ImageView dropdownArrowImage;

        ExpandableHeaderViewHolder(ViewGroup parent) {
            super(LayoutInflater.from(parent.getContext()).inflate(R.layout.itinerary_item_header_expandable, parent, false));
            ButterKnife.bind(this, itemView);
        }
    }
}
