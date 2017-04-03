package com.rcl.excalibur.adapters.delegate;

import android.content.Context;
import android.content.res.Resources;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.rcl.excalibur.R;
import com.rcl.excalibur.adapters.base.DelegateAdapter;
import com.rcl.excalibur.adapters.viewtype.ExpandableAccesibilityViewType;

import java.util.LinkedList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ExpandableAccessibilityDelegateAdapter implements DelegateAdapter<ExpandableAccessibilityDelegateAdapter.ExpandableAccessibiltyViewHolder, ExpandableAccesibilityViewType> {

    @Override
    public ExpandableAccessibiltyViewHolder onCreateViewHolder(ViewGroup parent) {
        return new ExpandableAccessibiltyViewHolder(parent);
    }

    @Override
    public void onBindViewHolder(ExpandableAccessibiltyViewHolder holder, ExpandableAccesibilityViewType item) {
        holder.title.setText(item.getTitle());

        initializeContentLines(item.getContent().length, holder, item);

       /* int i = 0;
        for (TextView textView : holder.contentLines) {
            textView.setText(StringUtils.fromHtml(item.getContent()[i]));
            i++;
        }*/
    }

    private static void initializeContentLines(int numberOfContentLines, ExpandableAccessibiltyViewHolder viewHolder, ExpandableAccesibilityViewType item) {
        viewHolder.contentLines.clear();
        viewHolder.textContent.removeAllViews();

        Resources resources = viewHolder.itemView.getResources();
        Context context = viewHolder.itemView.getContext();

        for (int i = 0; i < numberOfContentLines; i++) {
            TextView contentLine, descriptionLine;
            //Create TextView;
            contentLine = new TextView(context);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT);
            params.topMargin = (int) resources.getDimension(R.dimen.margin_normal);
            contentLine.setLayoutParams(params);

            contentLine.setCompoundDrawablesWithIntrinsicBounds(
                    resources.getDrawable(R.drawable.ic_blue_checkbox, context.getTheme()),
                    null,
                    null,
                    null);
            contentLine.setCompoundDrawablePadding((int) resources.getDimension(R.dimen.margin_normal));

            //viewHolder.textContent.addView(contentLine);
            contentLine.setText(item.getContent().toString());
            viewHolder.textContent.addView(contentLine);

            if (!TextUtils.isEmpty(item.getDescription()[i])) {
                descriptionLine = new TextView(context);
                descriptionLine.setLayoutParams(params);
                descriptionLine.setText(item.getDescription().toString());
                viewHolder.textContent.addView(contentLine);
            }
            //viewHolder.contentLines.add(contentLine);
        }
    }

    static class ExpandableAccessibiltyViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.expandable_accessilibility_module_title) TextView title;
        @Bind(R.id.expandable_accessibility_module_content) LinearLayout textContent;
        @Bind(R.id.expandable_accessilibility_module_arrow) ImageView imageArrow;
        private List<ImageView> imageUrls;
        private List<TextView> contentLines;
        private List<TextView> descriptionLines;

        @OnClick(R.id.expandable_accessibility_module_container)
        public void onTitleClick() {
            change();
        }

        public void change() {
            final boolean isGone = View.GONE == textContent.getVisibility();
            textContent.setVisibility(isGone ? View.VISIBLE : View.GONE);
        }

        ExpandableAccessibiltyViewHolder(ViewGroup parent) {
            super(LayoutInflater.from(parent.getContext()).inflate(R.layout.module_item_detail_expandable_accessibility, parent, false));
            ButterKnife.bind(this, itemView);
            imageUrls = new LinkedList<>();
            contentLines = new LinkedList<>();
            descriptionLines = new LinkedList<>();

        }
    }
}
