package com.rcl.excalibur.adapters.planner.abstractitem;


import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.rcl.excalibur.BuildConfig;
import com.rcl.excalibur.R;
import com.rcl.excalibur.custom.view.PriceRangeLayout;
import com.rcl.excalibur.domain.utils.ConstantsUtil;
import com.rcl.excalibur.model.PlannerProductModel;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import eu.davidea.flexibleadapter.FlexibleAdapter;
import eu.davidea.flexibleadapter.items.AbstractSectionableItem;
import eu.davidea.viewholders.FlexibleViewHolder;

public class PlannerProductItem extends AbstractSectionableItem<PlannerProductItem.ViewHolder, PlannerHeader> {

    private int indexToBeAdded;
    private String id;
    private String timeLabel;
    private PlannerProductModel plannerProductModel;

    public PlannerProductItem(String id, String timeLabel, PlannerHeader header) {
        super(header);
        this.id = id;
        this.timeLabel = timeLabel;
    }

    @Override
    public boolean equals(Object inObject) {
        if (inObject == null) {
            return false;
        }
        if (inObject instanceof PlannerProductItem) {
            PlannerProductItem inItem = (PlannerProductItem) inObject;
            return this.id.equals(inItem.id);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setPlannerProductModel(PlannerProductModel plannerProductModel) {
        this.plannerProductModel = plannerProductModel;
    }

    public PlannerProductModel getPlannerProductModel() {
        return plannerProductModel;
    }

    @Override
    public int getLayoutRes() {
        return R.layout.planner_item_product;
    }

    @Override
    public ViewHolder createViewHolder(FlexibleAdapter adapter, LayoutInflater inflater, ViewGroup parent) {
        return new ViewHolder(inflater.inflate(getLayoutRes(), parent, false), adapter);
    }

    @Override
    public void bindViewHolder(FlexibleAdapter adapter, ViewHolder holder, int position, List payloads) {
        if (plannerProductModel.getProductMedia() != null && plannerProductModel.getProductMedia().length > 0) {
            Picasso.with(holder.itemView.getContext())
                    .load(BuildConfig.PREFIX_IMAGE + plannerProductModel.getProductMedia()[0])
                    .placeholder(R.drawable.placeholder_list_item)
                    .into(holder.productImage);
        } else {
            holder.productImage.setImageResource(R.drawable.placeholder_list_item);
        }
        //TODO: this is commented because of ticket CORE-1630 but in next sprint we will use this logic
        /*if (plannerProductModel.getUpChargeLevel() > 0) {
            holder.priceRange.setVisibility(View.VISIBLE);
            holder.priceRange.setValue(plannerProductModel.getUpChargeLevel());
        } else {
            holder.priceRange.setVisibility(View.GONE);
        }*/
        holder.productPromoted.setVisibility(plannerProductModel.isFeatured() ? View.VISIBLE : View.GONE);
        holder.productName.setText(plannerProductModel.getProductName());
        holder.productOperatingHours.setText(plannerProductModel.getOperatingHours());
        holder.productVenue.setText(plannerProductModel.getVenue());
        holder.productCategoryIcon.setImageResource(plannerProductModel.getResourceIdCategoryIcon());
        holder.productDeckAndDirection.setText(plannerProductModel.getLocation());
        holder.setTime(plannerProductModel.getStartHourText());
    }

    public int getIndexToBeAdded() {
        return indexToBeAdded;
    }

    public void setIndexToBeAdded(int indexToBeAdded) {
        this.indexToBeAdded = indexToBeAdded;
    }

    public class ViewHolder extends FlexibleViewHolder {
        @BindView(R.id.text_planner_product_name) TextView productName;
        @BindView(R.id.text_planner_product_operating_hours) TextView productOperatingHours;
        @BindView(R.id.text_planner_product_venue) TextView productVenue;
        @BindView(R.id.text_itinerary_planner_deck_and_direction) TextView productDeckAndDirection;
        @BindView(R.id.image_itinerary_product_picture) ImageView productImage;
        @BindView(R.id.image_itinerary_product_icon) ImageView productCategoryIcon;
        @BindView(R.id.image_itinerary_product_favorite) ImageView productPromoted;
        @BindView(R.id.view_itinerary_product_price_range) PriceRangeLayout priceRange;
        @BindView(R.id.layout_planner_item_separator_container) View separatorLayout;
        @BindView(R.id.text_planner_separator_time) TextView timeTextView;

        public ViewHolder(View view, FlexibleAdapter adapter) {
            super(view, adapter);
            ButterKnife.bind(this, itemView);
        }

        public void setTime(@Nullable String time) {
            if (time == null || time.isEmpty()) {
                hideTime();
            } else {
                timeTextView.setVisibility(View.VISIBLE);
                timeTextView.setText(time);
            }
        }

        public void hideTime() {
            timeTextView.setText(ConstantsUtil.EMPTY);
            timeTextView.setVisibility(View.GONE);
        }
    }
}
