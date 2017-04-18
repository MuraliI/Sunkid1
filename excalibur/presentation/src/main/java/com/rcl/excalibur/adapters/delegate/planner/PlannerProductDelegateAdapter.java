package com.rcl.excalibur.adapters.delegate.planner;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.rcl.excalibur.BuildConfig;
import com.rcl.excalibur.R;
import com.rcl.excalibur.adapters.base.DelegateAdapter;
import com.rcl.excalibur.model.PlannerProductModel;
import com.rcl.excalibur.custom.view.PriceRangeLayout;
import com.squareup.picasso.Picasso;

import butterknife.Bind;
import butterknife.ButterKnife;


public class PlannerProductDelegateAdapter implements DelegateAdapter<
        PlannerProductDelegateAdapter.ItineraryProductViewHolder,
        PlannerProductModel> {

    @Override
    public ItineraryProductViewHolder onCreateViewHolder(ViewGroup parent) {
        return new ItineraryProductViewHolder(parent);
    }

    @Override
    public void onBindViewHolder(ItineraryProductViewHolder holder, PlannerProductModel item) {
        if (item.getProductMedia() != null && item.getProductMedia().length > 0) {
            Picasso.with(holder.itemView.getContext())
                    .load(BuildConfig.PREFIX_IMAGE + item.getProductMedia()[0])
                    .placeholder(R.drawable.placeholder_list_item)
                    .into(holder.productImage);
        } else {
            holder.productImage.setImageResource(R.drawable.placeholder_list_item);
        }
        holder.productPromoted.setVisibility(item.isPromoted() ? View.VISIBLE : View.GONE);
        if (item.getPriceRange() > 0) {
            holder.priceRange.setVisibility(View.VISIBLE);
            holder.priceRange.setValue(item.getPriceRange());
        } else {
            holder.priceRange.setVisibility(View.GONE);
        }
        holder.productName.setText(item.getProductName());
        holder.productCategoryIcon.setImageResource(item.getResourceIdCategoryIcon());
        holder.productOperatingHours.setText(item.getOperatingHours());
        holder.productLocation.setText(item.getLocation());
        holder.productDeckAndDirection.setText(item.getDeckAndDirection());
    }

    class ItineraryProductViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.text_planner_product_name) TextView productName;
        @Bind(R.id.text_planner_product_operating_hours) TextView productOperatingHours;
        @Bind(R.id.text_planner_product_location) TextView productLocation;
        @Bind(R.id.text_itinerary_planner_deck_and_direction) TextView productDeckAndDirection;
        @Bind(R.id.image_itinerary_product_picture) ImageView productImage;
        @Bind(R.id.image_itinerary_product_icon) ImageView productCategoryIcon;
        @Bind(R.id.image_itinerary_product_favorite) ImageView productPromoted;
        @Bind(R.id.view_itinerary_product_price_range) PriceRangeLayout priceRange;

        ItineraryProductViewHolder(ViewGroup parent) {
            super(LayoutInflater.from(parent.getContext()).inflate(R.layout.planner_item_product, parent, false));
            ButterKnife.bind(this, itemView);
        }
    }
}
