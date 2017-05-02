package com.rcl.excalibur.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.rcl.excalibur.R;
import com.rcl.excalibur.domain.MenuItem;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observer;

public class DiningMenuCardAdapter extends BaseAdapter<MenuItem, Void, DiningMenuCardAdapter.TitleAndDescriptionViewHolder> {

    public DiningMenuCardAdapter(Observer<Void> observer) {
        super(observer);
    }

    @Override
    public void onBindViewHolder(TitleAndDescriptionViewHolder holder, int position) {
        final MenuItem menu = items.get(position);
        holder.title.setText(menu.getMenuItemTitle());
        holder.textDescription.setText(menu.getMenuItemDescription());
    }

    @Override
    protected int getLayout() {
        return R.layout.dining_menu_item_detail_and_text;
    }

    @NonNull
    @Override
    protected TitleAndDescriptionViewHolder getViewHolder(View view) {
        return new TitleAndDescriptionViewHolder(view);
    }

    class TitleAndDescriptionViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.text_module_title) TextView title;
        @BindView(R.id.text_module_text) TextView textDescription;

        TitleAndDescriptionViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
