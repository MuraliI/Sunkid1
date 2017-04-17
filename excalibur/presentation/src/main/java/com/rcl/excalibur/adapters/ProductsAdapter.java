package com.rcl.excalibur.adapters;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.rcl.excalibur.BuildConfig;
import com.rcl.excalibur.R;
import com.rcl.excalibur.adapters.viewtype.ProductInformationViewType;
import com.rcl.excalibur.domain.Product;
import com.rcl.excalibur.mapper.ProductInformationMapper;
import com.squareup.picasso.Picasso;

import java.lang.ref.WeakReference;

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
        ProductInformationMapper productInformationMapper = new ProductInformationMapper();
        ProductInformationViewType productInformationViewType;
        productInformationViewType = productInformationMapper.transform(product);

        holder.titleTextView.setText(product.getProductTitle());
        holder.venueTextView.setText(productInformationViewType.getVenue());
        holder.locationTextView.setText(productInformationViewType.getLocation());
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
