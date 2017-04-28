package com.rcl.excalibur.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.rcl.excalibur.R;
import com.rcl.excalibur.mvp.presenter.MenuListPresenter;

import butterknife.Bind;
import butterknife.ButterKnife;
import io.reactivex.Observer;

public class DiningMenuCardAdapter extends BaseAdapter<MenuListPresenter.Menu, Void, DiningMenuCardAdapter.TitleAndDescriptionViewHolder> {

    public DiningMenuCardAdapter(Observer<Void> observer) {
        super(observer);
    }

    @Override
    public void onBindViewHolder(TitleAndDescriptionViewHolder holder, int position) {
        final MenuListPresenter.Menu menu = items.get(position);
        holder.title.setText(menu.getTitle());
        holder.textDescription.setText(menu.getDescription());
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

        @Bind(R.id.text_module_title) TextView title;
        @Bind(R.id.text_module_text) TextView textDescription;

        TitleAndDescriptionViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
