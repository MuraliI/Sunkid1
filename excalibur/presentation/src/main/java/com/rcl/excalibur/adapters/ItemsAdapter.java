package com.rcl.excalibur.adapters;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.rcl.excalibur.R;
import com.rcl.excalibur.model.ItemModel;
import com.squareup.picasso.Picasso;

import java.lang.ref.WeakReference;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.Observer;

public class ItemsAdapter extends RecyclerView.Adapter<ItemsAdapter.ItemViewHolder> {

    private List<ItemModel> items;
    private Observer observer;

    public ItemsAdapter(List<ItemModel> items, Observer observer) {
        this.items = items;
        this.observer = observer;
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item, parent, false);

        return new ItemViewHolder(view);

    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        final ItemModel itemModel = items.get(position);
        holder.nameTextView.setText(itemModel.getName());
        holder.itemModel = itemModel;
        holder.observerRef = new WeakReference(observer);
        final Context context = holder.imageImageView.getContext();
        if (context == null) {
            return;
        }
        Picasso.with(context).load(itemModel.getImageUrl()).placeholder(R.drawable.thumb).into(holder.imageImageView);

    }

    @Override
    public int getItemCount() {
        return items == null ? 0 : items.size();
    }

    static class ItemViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.item_name) TextView nameTextView;
        @Bind(R.id.item_image) ImageView imageImageView;
        WeakReference<Observer> observerRef;
        ItemModel itemModel;

        public ItemViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @OnClick(R.id.item_image)
        public void onImageClick() {
            if (observerRef == null) {
                return;
            }
            Observable.just(itemModel)
                    .subscribe(observerRef.get());

        }
    }
}
