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

import com.rcl.excalibur.R;
import com.rcl.excalibur.adapters.base.DelegateAdapter;
import com.rcl.excalibur.adapters.viewtype.ExpandableAccesibilityViewType;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import timber.log.Timber;

import static android.view.View.GONE;

public class ExpandableAccessibilityDelegateAdapter implements DelegateAdapter<ExpandableAccessibilityDelegateAdapter.ExpandableAccessibiltyViewHolder, ExpandableAccesibilityViewType> {

    @Override
    public ExpandableAccessibiltyViewHolder onCreateViewHolder(ViewGroup parent) {
        return new ExpandableAccessibiltyViewHolder(parent);
    }

    @Override
    public void onBindViewHolder(ExpandableAccessibiltyViewHolder holder, ExpandableAccesibilityViewType item) {
        holder.title.setText(item.getTitle());

        fillContentLines(item.getAccessibilities().size(), holder, item);
    }

    private static void fillContentLines(int numberOfContentLines, ExpandableAccessibiltyViewHolder viewHolder, ExpandableAccesibilityViewType item) {
        viewHolder.textContent.removeAllViews();
        Resources resources = viewHolder.itemView.getResources();
        Context context = viewHolder.itemView.getContext();

        for (int i = 0; i < numberOfContentLines; i++) {

            final TextView contentLine = new TextView(context);
            final TextView descriptionLine = new TextView(context);

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT);
            params.topMargin = (int) resources.getDimension(R.dimen.margin_normal);

            String subtitle = item.getAccessibilities().get(i).getSubtitle();
            if (subtitle != null) {
                contentLine.setLayoutParams(params);
                contentLine.setText(subtitle);
            }

            viewHolder.textContent.addView(contentLine);
            Timber.i("url", item.getAccessibilities().get(i).getImageUrl());

            Picasso.with(context).load("http://icons.iconarchive.com/icons/paomedia/small-n-flat/1024/sign-check-icon.png").into(new Target() {
                @Override
                public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom loadedFrom) {
                    Timber.i("onBitmapLoaded");
                    BitmapDrawable mBitmapDrawable = new BitmapDrawable(resources, bitmap);
                    contentLine.setCompoundDrawablesWithIntrinsicBounds(
                            mBitmapDrawable,
                            null,
                            null,
                            null);
                    contentLine.setCompoundDrawablePadding((int) resources.getDimension(R.dimen.margin_normal));
                }

                @Override
                public void onBitmapFailed(Drawable drawable) {
                    Timber.i("onFailed");
                }

                @Override
                public void onPrepareLoad(Drawable drawable) {
                    Timber.i("onPrepareLoad");
                }
            });

            String description = item.getAccessibilities().get(i).getDescription();
            if (!TextUtils.isEmpty(description)) {
                descriptionLine.setText(description);
                descriptionLine.setLayoutParams(params);
                viewHolder.textContent.addView(descriptionLine);
            }
        }

    }

    static class ExpandableAccessibiltyViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.expandable_accessilibility_module_title) TextView title;
        @Bind(R.id.expandable_accessibility_module_content) LinearLayout textContent;
        @Bind(R.id.expandable_accessilibility_module_arrow) ImageView imageArrow;

        @OnClick(R.id.expandable_accessibility_module_container)
        public void onTitleClick() {
            change();
        }

        public void change() {
            final boolean isGone = GONE == textContent.getVisibility();
            textContent.setVisibility(isGone ? View.VISIBLE : GONE);
        }

        ExpandableAccessibiltyViewHolder(ViewGroup parent) {
            super(LayoutInflater.from(parent.getContext()).inflate(R.layout.module_item_detail_expandable_accessibility, parent, false));
            ButterKnife.bind(this, itemView);
        }
    }
}
