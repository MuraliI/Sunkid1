package com.rcl.excalibur.adapters;


import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.rcl.excalibur.R;
import com.rcl.excalibur.model.DiscoverItemModel;

import java.lang.ref.WeakReference;

import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observer;

import static io.reactivex.Observable.just;

public class PlanAdapter extends BaseAdapter<DiscoverItemModel, PlanAdapter.PlanViewHolder> {

    public PlanAdapter(Observer<DiscoverItemModel> observer) {
        super(observer);
    }

    @Override
    public void onBindViewHolder(PlanViewHolder holder, int position) {
        holder.discoverItemModel = items.get(position);
        if (hasObserver()) {
            holder.observerRef = new WeakReference<>(getObserver());
        }
    }

    @Override
    protected int getLayout() {
        return R.layout.item_plan;
    }

    @NonNull
    @Override
    protected PlanViewHolder getViewHolder(View view) {
        return new PlanViewHolder(view);
    }

    static class PlanViewHolder extends RecyclerView.ViewHolder {

        private DiscoverItemModel discoverItemModel;
        private WeakReference<Observer<DiscoverItemModel>> observerRef;

        PlanViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @OnClick(R.id.card_view)
        public void onImageClick() {
            if (observerRef == null) {
                return;
            }
            just(discoverItemModel).subscribe(observerRef.get());
        }
    }
}
