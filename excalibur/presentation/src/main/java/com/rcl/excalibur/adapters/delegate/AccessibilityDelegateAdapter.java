package com.rcl.excalibur.adapters.delegate;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.rcl.excalibur.R;
import com.rcl.excalibur.adapters.base.DelegateAdapter;
import com.rcl.excalibur.adapters.viewtype.AccessibilityViewType;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class AccessibilityDelegateAdapter implements DelegateAdapter<AccessibilityDelegateAdapter.AccessibilityViewHolder,
        AccessibilityViewType> {


    @Override
    public AccessibilityViewHolder onCreateViewHolder(ViewGroup parent) {
        return new AccessibilityViewHolder(parent);
    }

    @Override
    public void onBindViewHolder(AccessibilityViewHolder holder, AccessibilityViewType item) {
        holder.textContent.setText(item.getContent());
    }


    public static class AccessibilityViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.accessibility_module_content) TextView textContent;
        @Bind(R.id.accessibility_module_arrow) ImageView imageArrow;

        public AccessibilityViewHolder(ViewGroup parent) {
            super(LayoutInflater.from(parent.getContext()).inflate(R.layout.module_item_detail_accessibility, parent, false));
            ButterKnife.bind(this, itemView);
        }

        @OnClick(R.id.accessibility_module_title)
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
