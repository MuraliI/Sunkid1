package com.rcl.excalibur.mvp.view;


import android.app.Activity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.rcl.excalibur.R;
import com.rcl.excalibur.adapters.base.RecyclerViewType;
import com.rcl.excalibur.adapters.itinerary.ItineraryCoodinatorAdapter;
import com.rcl.excalibur.fragments.ItineraryFragment;
import com.rcl.excalibur.model.itinerary.ProductModel;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ItineraryView extends FragmentView<ItineraryFragment> {

    @Bind(R.id.recycler_view) RecyclerView recyclerView;

    private ItineraryCoodinatorAdapter adapter;

    public ItineraryView(ItineraryFragment fragment) {
        super(fragment);
        ButterKnife.bind(this, fragment.getView());
    }

    public void init() {
        final Activity activity = getActivity();
        if (activity == null) {
            return;
        }
        LinearLayoutManager layoutManager = new LinearLayoutManager(activity);
        adapter = new ItineraryCoodinatorAdapter(null);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), layoutManager.getOrientation()));
        recyclerView.setAdapter(adapter);
    }

    public void setGreetingText(RecyclerViewType greetingText) {
        adapter.addViewTypeOnceAndNotify(greetingText);
    }

    public void addPlans(List<ProductModel> productModelList) {
        adapter.addAll(productModelList);
    }

}
