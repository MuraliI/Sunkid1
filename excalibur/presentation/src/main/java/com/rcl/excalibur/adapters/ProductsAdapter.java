package com.rcl.excalibur.adapters;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.rcl.excalibur.BuildConfig;
import com.rcl.excalibur.R;
import com.rcl.excalibur.domain.MediaItem;
import com.rcl.excalibur.domain.Product;
import com.rcl.excalibur.domain.ProductCategory;
import com.squareup.picasso.Picasso;

import java.lang.ref.WeakReference;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observer;

import static io.reactivex.Observable.just;

public class ProductsAdapter extends BaseAdapter<Product, ProductsAdapter.DiscoverViewHolder> {

    public ProductsAdapter(final Observer<Product> observer) {
        super(observer);
    }

    @Override
    public void onBindViewHolder(DiscoverViewHolder holder, int position) {
        final Product product = items.get(position);
        Context context = holder.imageView.getContext();
        holder.product = product;

        List<ProductCategory> productCategory = product.getProductCategory();
        if (!productCategory.isEmpty()) {
            holder.categoryTextView.setText(productCategory.get(0).getProductTags().get(0).getDescription());
        }

        holder.titleTextView.setText(product.getProductTitle());

        holder.rangeTextView.setText(product.getTimeFrame());

        List<MediaItem> mediaItems = product.getProductMedia().getMediaItem();
        if (!mediaItems.isEmpty()) {
            Picasso.with(context)
                    .load(BuildConfig.PREFIX_IMAGE + mediaItems.get(0).getMediaRefLink())
                    .placeholder(R.drawable.thumb)
                    .into(holder.imageView);
        }

        if (hasObserver()) {
            holder.observerRef = new WeakReference<>(getObserver());
        }
    }

    @Override
    int getLayout() {
        return R.layout.item_discover;
    }

    @NonNull
    @Override
    DiscoverViewHolder getViewHolder(View view) {
        return new DiscoverViewHolder(view);
    }

    static class DiscoverViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.card_image) ImageView imageView;
        @Bind(R.id.card_title) TextView titleTextView;
        @Bind(R.id.card_range) TextView rangeTextView;
        @Bind(R.id.card_category) TextView categoryTextView;
        private Product product;
        private WeakReference<Observer<Product>> observerRef;

        DiscoverViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @OnClick(R.id.card_view)
        void onImageClick() {
            if (observerRef == null) {
                return;
            }
            just(product).subscribe(observerRef.get());
        }
    }
}
