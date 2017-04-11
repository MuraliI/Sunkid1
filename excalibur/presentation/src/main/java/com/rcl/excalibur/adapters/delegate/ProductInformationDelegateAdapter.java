package com.rcl.excalibur.adapters.delegate;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.rcl.excalibur.R;
import com.rcl.excalibur.adapters.base.DelegateAdapter;
import com.rcl.excalibur.adapters.viewtype.ProductInformationViewType;
import com.rcl.excalibur.custom.view.PriceRangeLayout;
import com.rcl.excalibur.domain.ProductType;

import java.lang.ref.WeakReference;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.Observer;


public class ProductInformationDelegateAdapter implements DelegateAdapter<ProductInformationDelegateAdapter.ProductInformationViewHolder,
        ProductInformationViewType> {

    private WeakReference<Observer<Long>> findOnDeckObserver;

    public ProductInformationDelegateAdapter(final Observer<Long> findOnDeckObserver) {
        this.findOnDeckObserver = new WeakReference<>(findOnDeckObserver);
    }

    @Override
    public ProductInformationViewHolder onCreateViewHolder(ViewGroup parent) {
        return new ProductInformationViewHolder(parent);
    }

    @Override
    public void onBindViewHolder(ProductInformationViewHolder holder, ProductInformationViewType item) {
        holder.productId = item.getProductId();
        holder.name.setText(item.getProductName());

        boolean isShorex = ProductType.SHOREX_TYPE.equals(item.getProductType());
        holder.findOnDeck.setVisibility(isShorex ? View.GONE : View.VISIBLE);

        holder.venue.setVisibility(isShorex ? View.GONE : View.VISIBLE);
        holder.venue.setText(item.getVenue());

        holder.deckAndDirection.setVisibility(isShorex ? View.GONE : View.VISIBLE);
        holder.deckAndDirection.setText(item.getLocation());

        holder.port.setVisibility(isShorex ? View.VISIBLE : View.GONE);
        holder.port.setText(item.getPort());

        holder.reservationLayout.setVisibility(item.isReservationRequired() ? View.VISIBLE : View.GONE);

        holder.priceRange.setVisibility(item.getUpChargeLevel() > 0 ? View.VISIBLE : View.GONE);
        holder.priceRange.setValue(item.getUpChargeLevel());

        if (hasObserver()) {
            holder.findOnDeckObserver = new WeakReference<>(findOnDeckObserver.get());
        }
    }

    private boolean hasObserver() {
        return findOnDeckObserver.get() != null;
    }

    class ProductInformationViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.text_product_detail_name) TextView name;
        @Bind(R.id.text_product_venue) TextView venue;
        @Bind(R.id.text_product_deck_and_direction) TextView deckAndDirection;
        @Bind(R.id.text_product_port) TextView port;
        @Bind(R.id.layout_reservation) View reservationLayout;
        @Bind(R.id.button_find_on_deck) View findOnDeck;
        @Bind(R.id.price_range_product) PriceRangeLayout priceRange;

        private Long productId;
        private WeakReference<Observer<Long>> findOnDeckObserver;

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
