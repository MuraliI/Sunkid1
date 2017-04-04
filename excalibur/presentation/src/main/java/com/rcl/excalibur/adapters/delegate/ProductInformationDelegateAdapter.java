package com.rcl.excalibur.adapters.delegate;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.fernandocejas.arrow.strings.Strings;
import com.rcl.excalibur.R;
import com.rcl.excalibur.adapters.base.DelegateAdapter;
import com.rcl.excalibur.adapters.viewtype.ProductInformationViewType;

import java.lang.ref.WeakReference;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.Observer;


public class ProductInformationDelegateAdapter implements DelegateAdapter<ProductInformationDelegateAdapter.ProductInformationViewHolder,
        ProductInformationViewType> {

    private static final int ONE = 1;
    private static final int TWO = 2;
    private static final int THREE = 3;
    private static final int FOUR = 4;

    private WeakReference<Observer<String>> findOnDeckObserver;

    public ProductInformationDelegateAdapter(final Observer<String> findOnDeckObserver) {
        this.findOnDeckObserver = new WeakReference<>(findOnDeckObserver);
    }

    @Override
    public ProductInformationViewHolder onCreateViewHolder(ViewGroup parent) {
        return new ProductInformationViewHolder(parent);
    }

    @Override
    public void onBindViewHolder(ProductInformationViewHolder holder, ProductInformationViewType item) {
        holder.name.setText(item.getProductName());
        holder.venue.setText(item.getVenue());
        holder.deckAndDirection.setVisibility(!Strings.isNullOrEmpty(item.getLocation()) ? View.VISIBLE : View.GONE);
        holder.deckAndDirection.setText(item.getLocation());
        holder.port.setVisibility(!Strings.isNullOrEmpty(item.getPort()) ? View.VISIBLE : View.GONE);
        holder.port.setText(item.getPort());
        holder.reservationLayout.setVisibility(item.isReservationRequired() ? View.VISIBLE : View.GONE);
        holder.productId = item.getProductId();

        updateUpChargeIndicator(item.getUpChargeLevel(), holder);

        if (hasObserver()) {
            holder.findOnDeckObserver = new WeakReference<>(findOnDeckObserver.get());
        }
    }

    private boolean hasObserver() {
        return findOnDeckObserver.get() != null;
    }

    private void updateUpChargeIndicator(int upChargeLevel, ProductInformationViewHolder holder) {
        Context context = holder.itemView.getContext();

        changeTint(context, holder.imageDollar1, upChargeLevel >= ONE);
        changeTint(context, holder.imageDollar2, upChargeLevel >= TWO);
        changeTint(context, holder.imageDollar3, upChargeLevel >= THREE);
        changeTint(context, holder.imageDollar4, upChargeLevel >= FOUR);
    }

    private void changeTint(final Context context, final ImageView imageView, final boolean valid) {
        imageView.setColorFilter(ContextCompat.getColor(context, valid ? R.color.black : R.color.grey));
    }

    class ProductInformationViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.text_product_detail_name) TextView name;
        @Bind(R.id.text_product_venue) TextView venue;
        @Bind(R.id.text_product_deck_and_direction) TextView deckAndDirection;
        @Bind(R.id.text_product_port) TextView port;
        @Bind(R.id.price_range_module_dollar_1) ImageView imageDollar1;
        @Bind(R.id.price_range_module_dollar_2) ImageView imageDollar2;
        @Bind(R.id.price_range_module_dollar_3) ImageView imageDollar3;
        @Bind(R.id.price_range_module_dollar_4) ImageView imageDollar4;
        @Bind(R.id.layout_reservation) View reservationLayout;

        private String productId;
        private WeakReference<Observer<String>> findOnDeckObserver;

        ProductInformationViewHolder(ViewGroup parent) {
            super(LayoutInflater.from(parent.getContext()).inflate(R.layout.module_product_detail_information, parent, false));
            ButterKnife.bind(this, itemView);
        }

        @OnClick(R.id.button_find_on_deck)
        void onFindOnDeckClicked() {
            if (this.findOnDeckObserver != null && this.findOnDeckObserver.get() != null) {
                Observable.just(this.productId).subscribe(findOnDeckObserver.get());
            }
        }
    }
}
