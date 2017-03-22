package com.rcl.excalibur.adapters;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rcl.excalibur.R;
import com.rcl.excalibur.model.DiscoverItemModel;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observer;

import static com.rcl.excalibur.data.utils.CollectionUtils.isEmpty;
import static io.reactivex.Observable.just;

public class PlanAdapter extends BaseAdapter<PlanAdapter.PlanViewHoldel> {

    private List<DiscoverItemModel> plans;

    public PlanAdapter(final Observer observer) {
        super(observer);
        this.plans = new ArrayList();
    }

    public void removeAll() {
        plans.clear();
        notifyDataSetChanged();
    }

    public void addAll(List<DiscoverItemModel> list) {
        if (isEmpty(list)) {
            return;
        }
        plans.clear();
        plans.addAll(list);
        notifyDataSetChanged();
    }

    public void add(DiscoverItemModel discoverItemModel) {
        plans.add(discoverItemModel);
        notifyItemInserted(plans.size() - 1);
    }

    @Override
    public PlanViewHoldel onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_plan, parent, false);
        return new PlanViewHoldel(view);
    }

    @Override
    public void onBindViewHolder(PlanViewHoldel holder, int position) {
        final DiscoverItemModel discoverItemModel = plans.get(position);
        holder.discoverItemModel = discoverItemModel;
        if (hasObserver()) {
            holder.observerRef = new WeakReference<>(getObserver());
        }
    }

    @Override
    public int getItemCount() {
        return plans.size();
    }

    static class PlanViewHoldel extends RecyclerView.ViewHolder {

        private DiscoverItemModel discoverItemModel;
        private WeakReference<Observer> observerRef;

        public PlanViewHoldel(View itemView) {
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
