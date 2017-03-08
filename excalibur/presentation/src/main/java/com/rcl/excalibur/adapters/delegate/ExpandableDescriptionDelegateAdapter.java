package com.rcl.excalibur.adapters.delegate;

import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.rcl.excalibur.R;
import com.rcl.excalibur.adapters.base.DelegateAdapter;
import com.rcl.excalibur.adapters.viewtype.ExpandableDescriptionViewType;
import com.rcl.excalibur.custom.view.ExpandableDescriptionLayout;


public class ExpandableDescriptionDelegateAdapter implements
        DelegateAdapter<ExpandableDescriptionDelegateAdapter.ExpandableDescriptionViewHolder,
                ExpandableDescriptionViewType> {

    @Override
    public ExpandableDescriptionViewHolder onCreateViewHolder(ViewGroup parent) {
        return new ExpandableDescriptionViewHolder(parent);
    }

    @Override
    public void onBindViewHolder(ExpandableDescriptionViewHolder holder, ExpandableDescriptionViewType item) {
        holder.descriptionLayout.setDescription(Html.fromHtml(item.getDescription()));
    }

    static class ExpandableDescriptionViewHolder extends RecyclerView.ViewHolder {

        private ExpandableDescriptionLayout descriptionLayout;

        ExpandableDescriptionViewHolder(ViewGroup parent) {
            super(LayoutInflater.from(parent.getContext()).inflate(R.layout.module_expandable_description, parent, false));
            descriptionLayout = (ExpandableDescriptionLayout) itemView;
        }
    }
}
