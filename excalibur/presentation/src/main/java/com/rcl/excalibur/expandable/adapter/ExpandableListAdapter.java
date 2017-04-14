package com.rcl.excalibur.expandable.adapter;


import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.rcl.excalibur.R;
import com.rcl.excalibur.adapters.BaseAdapter;
import com.rcl.excalibur.expandable.model.ExpandableItem;
import com.squareup.picasso.Picasso;

import butterknife.Bind;
import butterknife.ButterKnife;
import io.reactivex.Observer;
//TODO: Remove class
public class ExpandableListAdapter extends BaseAdapter<ExpandableItem, ExpandableListAdapter.ExpandableViewHolder> {

    public ExpandableListAdapter(Observer<ExpandableItem> observer) {
        super(observer);
    }

    @Override
    protected int getLayout() {
        return R.layout.item_expandable;
    }

    @NonNull
    @Override
    protected ExpandableViewHolder getViewHolder(View view) {
        return new ExpandableViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ExpandableViewHolder holder, int position) {
        ExpandableItem expandableItem = items.get(position);

        holder.numberText.setText(String.valueOf(expandableItem.getNumber()));
        /*if (!expandableItem.isShowing()) {
            holder.itemView.setVisibility(View.GONE);
        }*/

        Picasso.with(holder.itemView.getContext())
                .load("https://goo.gl/TYEzQj").into(holder.productImage);
    }

    public class ExpandableViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.text_number) TextView numberText;
        @Bind(R.id.image_product) ImageView productImage;

        public ExpandableViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
