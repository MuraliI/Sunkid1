package com.rcl.excalibur.adapters.delegate;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import com.rcl.excalibur.R;
import com.rcl.excalibur.adapters.base.DelegateAdapter;
import com.rcl.excalibur.adapters.viewtype.DescriptionViewType;

import butterknife.Bind;
import butterknife.ButterKnife;

public class DescriptionDelegateAdapter implements DelegateAdapter<DescriptionDelegateAdapter.DescriptionViewHolder,
        DescriptionViewType> {


    @Override
    public DescriptionViewHolder onCreateViewHolder(ViewGroup parent) {
        return new DescriptionViewHolder(parent);
    }

    @Override
    public void onBindViewHolder(DescriptionViewHolder holder, DescriptionViewType item) {
        holder.textDescription.setText(item.getDescription());
    }

    public static class DescriptionViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.text_module_description)
        TextView textDescription;

        public DescriptionViewHolder(ViewGroup parent) {
            super(LayoutInflater.from(parent.getContext()).inflate(R.layout.module_item_detail_description, parent, false));
            ButterKnife.bind(this, itemView);
        }
    }

}
