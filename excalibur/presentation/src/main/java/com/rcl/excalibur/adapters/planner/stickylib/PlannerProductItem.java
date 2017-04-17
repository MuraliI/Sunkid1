package com.rcl.excalibur.adapters.planner.stickylib;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.rcl.excalibur.BuildConfig;
import com.rcl.excalibur.R;
import com.rcl.excalibur.model.PlannerProductModel;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import eu.davidea.flexibleadapter.FlexibleAdapter;
import eu.davidea.flexibleadapter.items.AbstractSectionableItem;
import eu.davidea.viewholders.FlexibleViewHolder;

public class PlannerProductItem extends AbstractSectionableItem<PlannerProductItem.ViewHolder, PlannerHeader> {

    private String id;

    private PlannerProductModel plannerProductModel;

    public PlannerProductItem(String id, PlannerHeader header) {
        super(header);
        this.id = id;
    }

    @Override
    public boolean equals(Object inObject) {
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
        holder.productName.setText(plannerProductModel.getProductName());
        holder.productOperatingHours.setText(plannerProductModel.getOperatinghours());
        holder.productLocation.setText(plannerProductModel.getLocation());
    }

    public class ViewHolder extends FlexibleViewHolder {
        @Bind(R.id.text_product_name) TextView productName;
        @Bind(R.id.text_product_operating_hours) TextView productOperatingHours;
        @Bind(R.id.text_product_location) TextView productLocation;
        @Bind(R.id.image_product) ImageView productImage;

        public ViewHolder(View view, FlexibleAdapter adapter) {
            super(view, adapter);
            ButterKnife.bind(this, itemView);
        }
    }
}
