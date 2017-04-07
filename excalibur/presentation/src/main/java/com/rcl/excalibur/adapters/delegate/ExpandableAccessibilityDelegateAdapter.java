package com.rcl.excalibur.adapters.delegate;

import android.content.Context;
import android.content.res.Resources;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.rcl.excalibur.BuildConfig;
import com.rcl.excalibur.R;
import com.rcl.excalibur.adapters.base.ExpandableContentDelegateAdapter;
import com.rcl.excalibur.adapters.delegate.viewholder.ExpandableAccessibiltyViewHolder;
import com.rcl.excalibur.adapters.delegate.viewholder.base.ExpandableContentViewHolder;
import com.rcl.excalibur.adapters.viewtype.ExpandableAccesibilityViewType;
import com.rcl.excalibur.model.ProductAccessibilityModel;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ExpandableAccessibilityDelegateAdapter<VT extends ExpandableAccesibilityViewType> extends
        ExpandableContentDelegateAdapter<ExpandableAccessibiltyViewHolder<VT>, VT, VT> {

    public ExpandableAccessibilityDelegateAdapter(ExpandableContentViewHolder.OnViewExpandedListener<VT> listener) {
        super(listener);
    }

    @Override
    public ExpandableAccessibiltyViewHolder<VT> onCreateViewHolder(ViewGroup parent) {
        return new ExpandableAccessibiltyViewHolder<>(parent, listener.get());
    }

    @Override
    public void onBindViewHolder(ExpandableAccessibiltyViewHolder<VT> holder, VT item) {
        holder.getTitle().setText(item.getTitle());
        holder.setViewType(item);
        fillContentLines(holder, item.getAccessibilities());
    }

    private static void fillContentLines(ExpandableAccessibiltyViewHolder viewHolder,
                                         List<ProductAccessibilityModel> accessibilityList) {
        viewHolder.getLayoutContent().removeAllViews();
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
                    .load(BuildConfig.PREFIX_IMAGE + imageUrl)
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
            viewHolder.getLayoutContent().addView(itemView);
        }
    }
}
