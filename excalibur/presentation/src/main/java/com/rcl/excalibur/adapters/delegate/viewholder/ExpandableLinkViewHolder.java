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

import java.util.LinkedList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class ExpandableLinkViewHolder<VT extends RecyclerViewType> extends ExpandableContentViewHolder<VT> {

    @Bind(R.id.expandable_link_module_title) TextView title;
    @Bind(R.id.expandable_link_module_content) LinearLayout textContent;
    @Bind(R.id.expandable_link_module_arrow) ImageView imageArrow;

    private List<TextView> contentLines;

    public ExpandableLinkViewHolder(ViewGroup parent, OnViewExpandedListener<VT> listener) {
        super(LayoutInflater.from(parent.getContext()).inflate(
                R.layout.module_item_detail_expandable_link,
                parent,
                false),
                listener);
        ButterKnife.bind(this, itemView);
        contentLines = new LinkedList<>();
    }

    public TextView getTitle() {
        return title;
    }

    public LinearLayout getTextContent() {
        return textContent;
    }

    public ImageView getImageArrow() {
        return imageArrow;
    }

    public List<TextView> getContentLines() {
        return contentLines;
    }

    @OnClick(R.id.expandable_link_module_container)
    void onTitleClick() {
        change();
    }

    private void change() {
        final boolean needsToExpand = View.GONE == textContent.getVisibility();
        textContent.setVisibility(needsToExpand ? View.VISIBLE : View.GONE);
        imageArrow.setImageResource(needsToExpand ? R.drawable.ic_chevron_up : R.drawable.ic_chevron_down);
        notifyViewExpanded(needsToExpand);
    }
}
