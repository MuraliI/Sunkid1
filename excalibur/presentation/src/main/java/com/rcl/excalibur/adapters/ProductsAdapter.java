package com.rcl.excalibur.adapters;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.util.Pair;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.rcl.excalibur.BuildConfig;
import com.rcl.excalibur.R;
import com.rcl.excalibur.domain.Product;
import com.rcl.excalibur.domain.utils.ConstantsUtil;
import com.rcl.excalibur.utils.LocationUtils;
import com.squareup.picasso.Picasso;

import java.lang.ref.WeakReference;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observer;

import static io.reactivex.Observable.just;

public class ProductsAdapter extends BaseAdapter<Product, Pair<Product, View>, ProductsAdapter.DiscoverViewHolder> {

    public ProductsAdapter(final Observer<Pair<Product, View>> observer) {
        super(observer);
    }

    @Override
    public void onBindViewHolder(DiscoverViewHolder holder, int position) {
        final Product product = items.get(position);
        Context context = holder.imageView.getContext();
        holder.product = product;

        holder.titleTextView.setText(product.getProductTitle());
        holder.venueTextView.setText(LocationUtils.getProductVenue(product.getProductLocation()));
        holder.locationTextView.setText("Deck" + ConstantsUtil.WHITE_SPACE + LocationUtils.getProductLocation(product.getProductLocation()));
        Picasso.with(context)
                .load(BuildConfig.PREFIX_IMAGE + holder.product.getHeroImageRefLink())
                .placeholder(R.drawable.placeholder_list_item)
                .into(holder.imageView);

        if (hasObserver()) {
            holder.observerRef = new WeakReference<>(getObserver());
        }
    }

    @Override
    protected int getLayout() {
        return R.layout.item_discover;
    }

    @NonNull
    @Override
    protected DiscoverViewHolder getViewHolder(View view) {
        return new DiscoverViewHolder(view);
    }

    static class DiscoverViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.card_image) ImageView imageView;
        @Bind(R.id.card_title) TextView titleTextView;
        @Bind(R.id.card_venue) TextView venueTextView;
        @Bind(R.id.card_location) TextView locationTextView;
        private Product product;
        private WeakReference<Observer<Pair<Product, View>>> observerRef;

        DiscoverViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @OnClick(R.id.card_view)
        void onImageClick(View view) {
            if (observerRef == null) {
                return;
            }
            Pair<Product, View> pair = new Pair<>(product, view.findViewById(R.id.card_image));
            just(pair).subscribe(observerRef.get());
        }
    }
}
