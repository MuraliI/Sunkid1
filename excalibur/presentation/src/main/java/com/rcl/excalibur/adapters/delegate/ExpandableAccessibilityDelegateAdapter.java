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
import com.rcl.excalibur.model.ProductAccessibilityModel;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ExpandableAccessibilityDelegateAdapter implements
        DelegateAdapter<ExpandableAccessibilityDelegateAdapter.ExpandableAccessibiltyViewHolder, ExpandableAccesibilityViewType> {
    @Override
    public ExpandableAccessibiltyViewHolder onCreateViewHolder(ViewGroup parent) {
        return new ExpandableAccessibiltyViewHolder(parent);
    }

    @Override
    public void onBindViewHolder(ExpandableAccessibiltyViewHolder holder, ExpandableAccesibilityViewType item) {
        holder.title.setText(item.getTitle());
        fillContentLines(holder, item.getAccessibilities());
    }

    private static void fillContentLines(ExpandableAccessibiltyViewHolder viewHolder,
                                         List<ProductAccessibilityModel> accessibilityList) {
        viewHolder.layoutContent.removeAllViews();
        Context context = viewHolder.itemView.getContext();
        Resources resources = viewHolder.itemView.getResources();

        for (int i = 0; i < accessibilityList.size(); i++) {
            View itemView = LayoutInflater.from(context).inflate(R.layout.item_accessibility, null);
            TextView subtitleLine = (TextView) itemView.findViewById(R.id.text_subtitle);
            TextView descriptionLine = (TextView) itemView.findViewById(R.id.text_description);
            ImageView image = (ImageView) itemView.findViewById(R.id.image_accessibility);
            final int iconSize = (int) resources.getDimension(R.dimen.icon_size);

            String imageUrl = accessibilityList.get(i).getImageUrl();
            Picasso.with(context)
                    .load(imageUrl)
                    .resize(iconSize, iconSize)
                    .placeholder(R.drawable.ic_blue_checkbox)
                    .into(image);

            String subtitle = accessibilityList.get(i).getSubtitle();
            if (!TextUtils.isEmpty(subtitle)) {
                subtitleLine.setText(subtitle);
            }

            String description = accessibilityList.get(i).getDescription();
            if (!TextUtils.isEmpty(description)) {
                descriptionLine.setText(description);
            } else {
                descriptionLine.setVisibility(View.GONE);
            }
            viewHolder.layoutContent.addView(itemView);
        }
    }

    static class ExpandableAccessibiltyViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.expandable_accessilibility_module_title) TextView title;
        @Bind(R.id.expandable_accessibility_module_content) LinearLayout layoutContent;
        @Bind(R.id.expandable_accessilibility_module_arrow) ImageView imageArrow;

        ExpandableAccessibiltyViewHolder(ViewGroup parent) {
            super(LayoutInflater.from(parent.getContext()).inflate(R.layout.module_item_detail_expandable_accessibility,
                    parent, false));
            ButterKnife.bind(this, itemView);
        }

        @OnClick(R.id.expandable_accessibility_module_container)
        public void onTitleClick() {
            change();
        }

        public void change() {
            final boolean isGone = View.GONE == layoutContent.getVisibility();
            layoutContent.setVisibility(isGone ? View.VISIBLE : View.GONE);
            imageArrow.setImageResource(isGone ? R.drawable.ic_chevron_up : R.drawable.ic_chevron_down);
        }
    }
}
