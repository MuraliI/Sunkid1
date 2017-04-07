package com.rcl.excalibur.adapters.delegate.viewholder;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.rcl.excalibur.R;
import com.rcl.excalibur.adapters.base.RecyclerViewType;
import com.rcl.excalibur.adapters.delegate.viewholder.base.ExpandableContentViewHolder;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class ExpandableAccessibiltyViewHolder<VT extends RecyclerViewType> extends ExpandableContentViewHolder<VT> {

    @Bind(R.id.expandable_accessilibility_module_title) TextView title;
    @Bind(R.id.expandable_accessibility_module_content) LinearLayout layoutContent;
    @Bind(R.id.expandable_accessilibility_module_arrow) ImageView imageArrow;

    public ExpandableAccessibiltyViewHolder(ViewGroup parent, OnViewExpandedListener<VT> listener) {
        super(LayoutInflater.from(parent.getContext()).inflate(
                R.layout.module_item_detail_expandable_accessibility,
                parent,
                false),
                listener);
        ButterKnife.bind(this, itemView);
    }

    public TextView getTitle() {
        return title;
    }

    public LinearLayout getLayoutContent() {
        return layoutContent;
    }

    public ImageView getImageArrow() {
        return imageArrow;
    }

    @OnClick(R.id.expandable_accessibility_module_container)
    public void onTitleClick() {
        change();
    }

    public void change() {
        final boolean needsToExpand = View.GONE == layoutContent.getVisibility();
        layoutContent.setVisibility(needsToExpand ? View.VISIBLE : View.GONE);
        imageArrow.setImageResource(needsToExpand ? R.drawable.ic_chevron_up : R.drawable.ic_chevron_down);
        notifyViewExpanded(needsToExpand);
    }
}
