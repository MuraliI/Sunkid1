package com.rcl.excalibur.adapters.delegate.itinerary;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
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
        Picasso.with(holder.itemView.getContext()).load(item.getImageUrl()).into(holder.productImage);
        holder.productName.setText(item.getName());
        holder.productOperatingHours.setText(item.getDate());
        holder.productLocation.setText(item.getLocationPointer());
    }

    class ItineraryProductViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.text_product_name) TextView productName;
        @Bind(R.id.text_product_operating_hours) TextView productOperatingHours;
        @Bind(R.id.text_product_location) TextView productLocation;
        @Bind(R.id.image_product) ImageView productImage;

        ItineraryProductViewHolder(ViewGroup parent) {
            super(LayoutInflater.from(parent.getContext()).inflate(R.layout.itinerary_item_product, parent, false));
            ButterKnife.bind(this, itemView);
        }
    }
}
