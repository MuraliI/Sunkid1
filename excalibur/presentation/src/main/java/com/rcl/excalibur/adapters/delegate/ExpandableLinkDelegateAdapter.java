package com.rcl.excalibur.adapters.delegate;

import android.content.Context;
import android.content.res.Resources;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.rcl.excalibur.R;
import com.rcl.excalibur.adapters.base.DelegateAdapter;
import com.rcl.excalibur.adapters.viewtype.ExpandableLinkViewType;
import com.rcl.excalibur.utils.StringUtils;

import java.util.LinkedList;
import java.util.List;

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

        initializeContentLines(item.getContent().length, item.isContentWithCheckMark(), holder);

        int i = 0;
        for (TextView textView : holder.contentLines) {
            textView.setText(StringUtils.fromHtml(item.getContent()[i]));
            textView.setTextAppearance(textView.getContext(), R.style.AppTheme_ProductDetailDescriptionModuleTextView);
            i++;
        }
    }

    private static void initializeContentLines(int numberOfContentLines, boolean hasCheckMarks, ExpandableLinkViewHolder viewHolder) {
        viewHolder.contentLines.clear();
        viewHolder.textContent.removeAllViews();

        Resources resources = viewHolder.itemView.getResources();
        Context context = viewHolder.itemView.getContext();

        for (int i = 0; i < numberOfContentLines; i++) {
            //Create TextView;
            TextView contentLine = new TextView(context);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT);
            params.topMargin = (int) resources.getDimension(R.dimen.margin_normal);
            contentLine.setLayoutParams(params);

            //Add checkmark
            if (hasCheckMarks) {
                contentLine.setCompoundDrawablesWithIntrinsicBounds(
                        resources.getDrawable(R.drawable.ic_blue_checkbox, context.getTheme()),
                        null,
                        null,
                        null);
                contentLine.setCompoundDrawablePadding((int) resources.getDimension(R.dimen.margin_normal));
            }
            viewHolder.textContent.addView(contentLine);
            viewHolder.contentLines.add(contentLine);
        }
    }


    static class ExpandableLinkViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.expandable_link_module_title) TextView title;
        @Bind(R.id.expandable_link_module_content) LinearLayout textContent;
        @Bind(R.id.expandable_link_module_arrow) ImageView imageArrow;

        private List<TextView> contentLines;

        ExpandableLinkViewHolder(ViewGroup parent) {
            super(LayoutInflater.from(parent.getContext()).inflate(R.layout.module_item_detail_expandable_link, parent, false));
            ButterKnife.bind(this, itemView);
            contentLines = new LinkedList<>();
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
