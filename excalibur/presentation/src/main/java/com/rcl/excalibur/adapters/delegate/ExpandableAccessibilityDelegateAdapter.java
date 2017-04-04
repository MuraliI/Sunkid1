package com.rcl.excalibur.adapters.delegate;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.rcl.excalibur.BuildConfig;
import com.rcl.excalibur.R;
import com.rcl.excalibur.adapters.base.DelegateAdapter;
import com.rcl.excalibur.adapters.viewtype.ExpandableAccesibilityViewType;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import uk.co.chrisjenx.calligraphy.CalligraphyUtils;

import static android.view.View.GONE;

public class ExpandableAccessibilityDelegateAdapter implements
        DelegateAdapter<ExpandableAccessibilityDelegateAdapter.ExpandableAccessibiltyViewHolder, ExpandableAccesibilityViewType> {
    @Override
    public ExpandableAccessibiltyViewHolder onCreateViewHolder(ViewGroup parent) {
        return new ExpandableAccessibiltyViewHolder(parent);
    }

    @Override
    public void onBindViewHolder(ExpandableAccessibiltyViewHolder holder, ExpandableAccesibilityViewType item) {
        holder.title.setText(item.getTitle());
        fillContentLines(item.getAccessibilities().size(), holder, item);
    }

    private static void fillContentLines(int numberOfContentLines, ExpandableAccessibiltyViewHolder viewHolder,
                                         ExpandableAccesibilityViewType item) {
        viewHolder.textContentParent.removeAllViews();
        Resources resources = viewHolder.itemView.getResources();
        Context context = viewHolder.itemView.getContext();

        for (int i = 0; i < numberOfContentLines; i++) {
            final TextView subtitleLine = new TextView(context);
            final TextView descriptionLine = new TextView(context);

            final Target target = new Target() {
                @Override
                public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom loadedFrom) {
                    BitmapDrawable drawable = new BitmapDrawable(context.getResources(), bitmap);
                    setCompoundDrawable(subtitleLine, drawable, context, resources);
                }

                @Override
                public void onBitmapFailed(Drawable drawable) {
                    setCompoundDrawable(subtitleLine, R.drawable.ic_blue_checkbox, context, resources);
                }

                @Override
                public void onPrepareLoad(Drawable drawable) {

                }
            };

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT);
            params.topMargin = (int) resources.getDimension(R.dimen.margin_normal);

            String subtitle = item.getAccessibilities().get(i).getSubtitle();
            if (subtitle != null) {
                subtitleLine.setLayoutParams(params);
                subtitleLine.setText(subtitle);
                CalligraphyUtils.applyFontToTextView(context, descriptionLine, context.getResources().getString(R.string.proximanova_regular));
                viewHolder.textContentParent.addView(subtitleLine);
            }

            String imageUrl = item.getAccessibilities().get(i).getImageUrl();
            viewHolder.targetList.add(target);
            final int iconSize = 10;
            if (TextUtils.isEmpty(imageUrl)) {
                Picasso.with(context)
                        .load(BuildConfig.PREFIX_IMAGE + imageUrl)
                        .resize(iconSize, iconSize)
                        .into(target);
            } else {
                setCompoundDrawable(subtitleLine, R.drawable.ic_blue_checkbox, context, resources);
            }

            String description = item.getAccessibilities().get(i).getDescription();
            if (!TextUtils.isEmpty(description)) {
                descriptionLine.setText(description);
                params.leftMargin = (int) resources.getDimension(R.dimen.margin_normal);
                CalligraphyUtils.applyFontToTextView(context, descriptionLine, context.getResources().getString(R.string.proximanova_regular));
                descriptionLine.setLayoutParams(params);
                viewHolder.textContentParent.addView(descriptionLine);
            }
        }
    }

    private static void setCompoundDrawable(TextView textView, BitmapDrawable drawable, Context context, Resources resources) {
        textView.setCompoundDrawablesWithIntrinsicBounds(
                drawable,
                null,
                null,
                null);
        textView.setCompoundDrawablePadding((int) resources.getDimension(R.dimen.margin_normal));
    }

    private static void setCompoundDrawable(TextView textView, int drawableResource, Context context, Resources resources) {
        textView.setCompoundDrawablesWithIntrinsicBounds(
                resources.getDrawable(R.drawable.ic_blue_checkbox),
                null,
                null,
                null);
        textView.setCompoundDrawablePadding((int) resources.getDimension(R.dimen.margin_normal));
    }

    static class ExpandableAccessibiltyViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.expandable_accessilibility_module_title)
        TextView title;
        @Bind(R.id.expandable_accessibility_module_content_parent)
        LinearLayout textContentParent;
        @Bind(R.id.expandable_accessibility_module_content_child)
        LinearLayout textContentChild;
        @Bind(R.id.expandable_accessilibility_module_arrow)
        ImageView imageArrow;
        public List<Target> targetList = new ArrayList<>();

        @OnClick(R.id.expandable_accessibility_module_container)
        public void onTitleClick() {
            change();
        }

        public void change() {
            final boolean isGone = GONE == textContentParent.getVisibility();
            textContentParent.setVisibility(isGone ? View.VISIBLE : GONE);
            textContentChild.setVisibility(isGone ? View.VISIBLE : GONE);
            imageArrow.setImageResource(isGone ? R.drawable.ic_arrow_drop_up : R.drawable.ic_arrow_drop_down);
        }

        ExpandableAccessibiltyViewHolder(ViewGroup parent) {
            super(LayoutInflater.from(parent.getContext()).inflate(R.layout.module_item_detail_expandable_accessibility,
                    parent, false));
            ButterKnife.bind(this, itemView);
        }
    }
}
