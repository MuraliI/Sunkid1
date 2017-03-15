package com.rcl.excalibur.adapters.delegate;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.rcl.excalibur.R;
import com.rcl.excalibur.adapters.base.DelegateAdapter;
import com.rcl.excalibur.adapters.viewtype.PromotionViewType;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class PromotionDelegateAdapter implements DelegateAdapter<PromotionDelegateAdapter.PromotionViewHolder, PromotionViewType> {

    @Override
    public PromotionViewHolder onCreateViewHolder(ViewGroup parent) {
        return new PromotionViewHolder(parent);
    }

    @Override
    public void onBindViewHolder(PromotionViewHolder holder, PromotionViewType item) {
        holder.textContent.setText(item.getContent());
        holder.textLinkTitle.setText(item.getTitle());
    }

    public static class PromotionViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.promotion_module_content) TextView textContent;
        @Bind(R.id.promotion_module_arrow) ImageView imageArrow;
        @Bind(R.id.promotion_module_title_link) TextView textLinkTitle;


        public PromotionViewHolder(ViewGroup parent) {
            super(LayoutInflater.from(parent.getContext()).inflate(R.layout.module_item_detail_promotion, parent, false));
            ButterKnife.bind(this, itemView);
        }

        @OnClick(R.id.promotion_module_container)
        public void onClick() {
            change();
        }

        private void change() {
            final boolean isGone = View.GONE == textContent.getVisibility();
            textContent.setVisibility(isGone ? View.VISIBLE : View.GONE);
            imageArrow.setImageResource(isGone ? R.drawable.ic_arrow_drop_up : R.drawable.ic_arrow_drop_down);
        }
    }
}
