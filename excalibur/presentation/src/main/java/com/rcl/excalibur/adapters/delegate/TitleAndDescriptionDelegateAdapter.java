package com.rcl.excalibur.adapters.delegate;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import com.rcl.excalibur.R;
import com.rcl.excalibur.adapters.base.DelegateAdapter;
import com.rcl.excalibur.adapters.viewtype.TitleAndDescriptionViewType;

import butterknife.Bind;
import butterknife.ButterKnife;


public class TitleAndDescriptionDelegateAdapter
        implements DelegateAdapter<TitleAndDescriptionDelegateAdapter.TitleAndDescriptionViewHolder,
        TitleAndDescriptionViewType> {


    @Override
    public TitleAndDescriptionViewHolder onCreateViewHolder(ViewGroup parent) {
        return new TitleAndDescriptionViewHolder(parent);
    }

    @Override
    public void onBindViewHolder(TitleAndDescriptionViewHolder holder, TitleAndDescriptionViewType item) {
        holder.title.setText(item.getTitle());
        holder.textDescription.setText(item.getDescription());
    }

    class TitleAndDescriptionViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.text_module_title) TextView title;
        @Bind(R.id.text_module_text) TextView textDescription;

        TitleAndDescriptionViewHolder(ViewGroup parent) {
            super(LayoutInflater.from(parent.getContext()).inflate(R.layout.module_item_detail_title_and_text, parent, false));
            ButterKnife.bind(this, itemView);
        }
    }

}
