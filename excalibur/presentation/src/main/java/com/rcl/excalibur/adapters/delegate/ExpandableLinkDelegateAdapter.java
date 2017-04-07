package com.rcl.excalibur.adapters.delegate;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import com.rcl.excalibur.R;
import com.rcl.excalibur.adapters.base.ExpandableContentDelegateAdapter;
import com.rcl.excalibur.adapters.delegate.viewholder.ExpandableLinkViewHolder;
import com.rcl.excalibur.adapters.delegate.viewholder.base.ExpandableContentViewHolder;
import com.rcl.excalibur.adapters.viewtype.ExpandableLinkViewType;
import com.rcl.excalibur.utils.StringUtils;


public class ExpandableLinkDelegateAdapter<VT extends ExpandableLinkViewType> extends
        ExpandableContentDelegateAdapter<ExpandableLinkViewHolder<VT>,
                VT,
                VT> {

    public ExpandableLinkDelegateAdapter(ExpandableContentViewHolder.OnViewExpandedListener<VT> listener) {
        super(listener);
    }

    @Override
    public ExpandableLinkViewHolder<VT> onCreateViewHolder(ViewGroup parent) {
        return new ExpandableLinkViewHolder<>(parent, listener.get());
    }

    @Override
    public void onBindViewHolder(ExpandableLinkViewHolder<VT> holder, VT item) {
        holder.getTitle().setText(item.getTitle());
        holder.setViewType(item);
        initializeContentLines(item, holder);
    }

    private static void initializeContentLines(ExpandableLinkViewType item, ExpandableLinkViewHolder viewHolder) {
        viewHolder.getContentLines().clear();
        viewHolder.getTextContent().removeAllViews();

        Context context = viewHolder.itemView.getContext();

        for (int i = 0; i < item.getContent().length; i++) {
            //Create TextView;
            TextView contentLine = (TextView) LayoutInflater.from(context).inflate(R.layout.item_expandable_link, null);
            contentLine.setText(StringUtils.fromHtml(item.getContent()[i]));

            viewHolder.getTextContent().addView(contentLine);
            viewHolder.getContentLines().add(contentLine);
        }
    }
}
