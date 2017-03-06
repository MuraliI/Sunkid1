package com.rcl.excalibur.adapters;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.rcl.excalibur.R;
import com.rcl.excalibur.model.DiscoverItemModel;
import com.squareup.picasso.Picasso;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observer;

import static com.rcl.excalibur.utils.CollectionUtils.isEmpty;
import static io.reactivex.Observable.just;

public class DiscoverAdapter extends BaseAdapter<DiscoverAdapter.DiscoverViewHoldel> {

    private List<DiscoverItemModel> plans;

    public DiscoverAdapter(final Observer observer) {
        super(observer);
        this.plans = new ArrayList();
    }

    public void removeAll() {
        plans.clear();
        notifyDataSetChanged();
    }

    public void addAll(Collection<DiscoverItemModel> collection) {
        if (isEmpty(collection)) {
            return;
        }
        plans.clear();
        plans.addAll(collection);
        notifyDataSetChanged();
    }

    public void add(DiscoverItemModel discoverModel) {
        plans.add(discoverModel);
        notifyItemInserted(plans.size() - 1);
    }

    @Override
    public DiscoverViewHoldel onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_discover, parent, false);
        return new DiscoverViewHoldel(view);
    }

    @Override
    public void onBindViewHolder(DiscoverViewHoldel holder, int position) {
        final DiscoverItemModel discoverModel = plans.get(position);
        holder.model = discoverModel;
        holder.categoryTextView.setText(discoverModel.getCategory());
        holder.titleTextView.setText(discoverModel.getTitle());
        holder.rangeTextView.setText(discoverModel.getHours());
        if (hasObserver()) {
            holder.observerRef = new WeakReference(getObserver());
        }
        final Context context = holder.imageView.getContext();
        if (context == null || TextUtils.isEmpty(discoverModel.getImageUrl())) {
            return;
        }
        Picasso.with(context).load(discoverModel.getImageUrl()).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return plans.size();
    }

    static class DiscoverViewHoldel extends RecyclerView.ViewHolder {

        @Bind(R.id.card_image) ImageView imageView;
        @Bind(R.id.card_title) TextView titleTextView;
        @Bind(R.id.card_range) TextView rangeTextView;
        @Bind(R.id.card_category) TextView categoryTextView;
        private DiscoverItemModel model;
        private WeakReference<Observer> observerRef;

        public DiscoverViewHoldel(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @OnClick(R.id.card_view)
        public void onImageClick() {
            if (observerRef == null) {
                return;
            }
            just(model).subscribe(observerRef.get());
        }
    }
}
