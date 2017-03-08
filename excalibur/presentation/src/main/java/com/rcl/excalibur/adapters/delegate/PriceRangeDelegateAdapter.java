package com.rcl.excalibur.adapters.delegate;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.rcl.excalibur.R;
import com.rcl.excalibur.adapters.base.DelegateAdapter;
import com.rcl.excalibur.adapters.viewtype.PriceRangeViewType;

import butterknife.Bind;
import butterknife.ButterKnife;


public class PriceRangeDelegateAdapter implements DelegateAdapter<PriceRangeDelegateAdapter.PriceRangeViewHolder, PriceRangeViewType> {

    @Override
    public PriceRangeViewHolder onCreateViewHolder(ViewGroup parent) {
        return new PriceRangeViewHolder(parent);
    }

    @Override
    public void onBindViewHolder(PriceRangeViewHolder holder, PriceRangeViewType item) {
        final Context context = holder.imageDolar1.getContext();
        if (context == null) {
            return;
        }
        final int count = item.getCount();
        changeTint(context, holder.imageDolar1, count >= 1);
        changeTint(context, holder.imageDolar2, count >= 2);
        changeTint(context, holder.imageDolar3, count >= 3);
        changeTint(context, holder.imageDolar4, count >= 4);
        changeTint(context, holder.imageDolar5, count >= 5);
    }

    private void changeTint(final Context context, final ImageView imageView, final boolean valid) {
        imageView.setColorFilter(ContextCompat.getColor(context, valid ? R.color.black : R.color.grey));

    }

    public static class PriceRangeViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.price_range_module_dolar_1) ImageView imageDolar1;
        @Bind(R.id.price_range_module_dolar_2) ImageView imageDolar2;
        @Bind(R.id.price_range_module_dolar_3) ImageView imageDolar3;
        @Bind(R.id.price_range_module_dolar_4) ImageView imageDolar4;
        @Bind(R.id.price_range_module_dolar_5) ImageView imageDolar5;

        public PriceRangeViewHolder(ViewGroup parent) {
            super(LayoutInflater.from(parent.getContext()).inflate(R.layout.module_item_detail_price_range, parent, false));
            ButterKnife.bind(this, itemView);
        }
    }
}
