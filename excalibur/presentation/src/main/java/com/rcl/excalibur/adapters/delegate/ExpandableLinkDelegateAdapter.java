package com.rcl.excalibur.adapters.delegate;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.rcl.excalibur.R;
import com.rcl.excalibur.adapters.base.DelegateAdapter;
import com.rcl.excalibur.adapters.viewtype.ExpandableLinkViewType;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class ExpandableLinkDelegateAdapter implements DelegateAdapter<ExpandableLinkDelegateAdapter.ExpandableLinkViewHolder,
        ExpandableLinkViewType> {


    @Override
    public ExpandableLinkViewHolder onCreateViewHolder(ViewGroup parent) {
        return new ExpandableLinkViewHolder(parent);
    }

    @Override
    public void onBindViewHolder(ExpandableLinkViewHolder holder, ExpandableLinkViewType item) {
        holder.title.setText(item.getTitle());
        holder.textContent.setText(item.getContent());
    }


    public static class ExpandableLinkViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.expandable_link_module_title) TextView title;
        @Bind(R.id.expandable_link_module_content) TextView textContent;
        @Bind(R.id.expandable_link_module_arrow) ImageView imageArrow;

        public ExpandableLinkViewHolder(ViewGroup parent) {
            super(LayoutInflater.from(parent.getContext()).inflate(R.layout.module_item_detail_expandable_link, parent, false));
            ButterKnife.bind(this, itemView);
        }

        @OnClick(R.id.expandable_link_module_container)
        public void onTitleClick() {
            change();
        }

        private void change() {
            final boolean isGone = View.GONE == textContent.getVisibility();
            textContent.setVisibility(isGone ? View.VISIBLE : View.GONE);
            imageArrow.setImageResource(isGone ? R.drawable.ic_arrow_drop_up : R.drawable.ic_arrow_drop_down);
        }
    }
}
