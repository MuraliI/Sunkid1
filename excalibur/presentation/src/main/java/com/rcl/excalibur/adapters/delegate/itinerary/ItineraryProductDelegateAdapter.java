package com.rcl.excalibur.adapters.delegate.itinerary;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.rcl.excalibur.R;
import com.rcl.excalibur.adapters.base.DelegateAdapter;
import com.rcl.excalibur.model.itinerary.ItineraryProductModel;
import com.squareup.picasso.Picasso;

import butterknife.Bind;
import butterknife.ButterKnife;


public class ItineraryProductDelegateAdapter implements DelegateAdapter<
        ItineraryProductDelegateAdapter.ItineraryProductViewHolder,
        ItineraryProductModel> {

    @Override
    public ItineraryProductViewHolder onCreateViewHolder(ViewGroup parent) {
        return new ItineraryProductViewHolder(parent);
    }

    @Override
    public void onBindViewHolder(ItineraryProductViewHolder holder, ItineraryProductModel item) {
        if (item.getImageUrl() != null) {
            Picasso.with(holder.itemView.getContext()).load(item.getImageUrl()).into(holder.productImage);
        }
        if (item.getResourceIdIcon() > 0) {
            holder.productIcon.setVisibility(View.VISIBLE);
            holder.productIcon.setImageResource(item.getResourceIdIcon());
        } else {
            holder.productIcon.setVisibility(View.GONE);
        }
        if (item.isFavorite()) {
            holder.productFavorite.setVisibility(View.VISIBLE);
        } else {
            holder.productFavorite.setVisibility(View.GONE);
        }
        holder.productName.setText(item.getName());
        holder.productOperatingHours.setText(item.getOperatingHours());
        holder.productLocation.setText(item.getLocationPointer());
        holder.productDeckAndDirection.setText(item.getDeckAndDirection());
    }

    class ItineraryProductViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.text_itinerary_product_name) TextView productName;
        @Bind(R.id.text_itinerary_product_operating_hours) TextView productOperatingHours;
        @Bind(R.id.text_itinerary_product_location) TextView productLocation;
        @Bind(R.id.text_itinerary_product_deck_and_direction) TextView productDeckAndDirection;
        @Bind(R.id.image_itinerary_product_picture) ImageView productImage;
        @Bind(R.id.image_itinerary_product_icon) ImageView productIcon;
        @Bind(R.id.image_itinerary_product_favorite) ImageView productFavorite;

        ItineraryProductViewHolder(ViewGroup parent) {
            super(LayoutInflater.from(parent.getContext()).inflate(R.layout.itinerary_item_product, parent, false));
            ButterKnife.bind(this, itemView);
        }
    }
}
