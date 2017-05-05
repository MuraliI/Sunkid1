package com.rcl.excalibur.adapters;


import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.rcl.excalibur.R;
import com.rcl.excalibur.model.ShipStatsModel;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observer;

public class ShipStatsAdapter extends BaseAdapter<ShipStatsModel, ShipStatsModel, ShipStatsAdapter.ShipStatsViewHolder> {

    public ShipStatsAdapter(Observer<ShipStatsModel> observer) {
        super(observer);
    }

    @Override
    protected int getLayout() {
        return R.layout.item_ship_stat;
    }

    @NonNull
    @Override
    protected ShipStatsViewHolder getViewHolder(View view) {
        return new ShipStatsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ShipStatsViewHolder holder, int position) {
        ShipStatsModel shipStatsModel = items.get(position);
        holder.itemName.setText(shipStatsModel.getName());
    }

    class ShipStatsViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.item_name)
        TextView itemName;

        ShipStatsViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
