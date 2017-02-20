package com.rcl.excalibur.adapters;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rcl.excalibur.R;
import com.rcl.excalibur.model.PlanModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;

import static com.rcl.excalibur.utils.CollectionUtils.isEmpty;

public class PlanAdapter extends RecyclerView.Adapter<PlanAdapter.PlanViewHoldel> {

    private List<PlanModel> plans;

    public PlanAdapter() {
        this.plans = new ArrayList();
    }

    public void removeAll() {
        plans.clear();
        notifyDataSetChanged();
    }

    public void addAll(List<PlanModel> list) {
        if (isEmpty(list)) {
            return;
        }
        plans.clear();
        plans.addAll(list);
        notifyDataSetChanged();
    }

    public void add(PlanModel planModel) {
        plans.add(planModel);
        notifyItemInserted(plans.size() - 1);
    }

    @Override
    public PlanViewHoldel onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_card, parent, false);
        return new PlanViewHoldel(view);
    }

    @Override
    public void onBindViewHolder(PlanViewHoldel holder, int position) {
//TODO        final PlanModel planModel = plans.get(position);


    }

    @Override
    public int getItemCount() {
        return plans != null ? plans.size() : 0;
    }

    static class PlanViewHoldel extends RecyclerView.ViewHolder {

        public PlanViewHoldel(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
