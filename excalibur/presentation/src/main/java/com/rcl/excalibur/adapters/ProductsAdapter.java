package com.rcl.excalibur.adapters;


import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.rcl.excalibur.R;
import com.rcl.excalibur.domain.Product;

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
        holder.product = product;
        // TODO: 22/03/17 Make it show the product information
        /*holder.categoryTextView.setText(product.getCategory());
        holder.titleTextView.setText(product.getTitle());
        holder.rangeTextView.setText(product.getHours());
        if (hasObserver()) {
            holder.observerRef = new WeakReference<>(getObserver());
        }
        final Context context = holder.imageView.getContext();
        if (context == null || TextUtils.isEmpty(product.getImageUrl())) {
            return;
        }
        Picasso.with(context).load(product.getImageUrl()).into(holder.imageView);*/
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
