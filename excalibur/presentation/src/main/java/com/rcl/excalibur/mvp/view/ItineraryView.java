package com.rcl.excalibur.mvp.view;


import android.app.Activity;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.rcl.excalibur.R;
import com.rcl.excalibur.adapters.base.RecyclerViewType;
import com.rcl.excalibur.adapters.itinerary.ItineraryCoodinatorAdapter;
import com.rcl.excalibur.fragments.ItineraryFragment;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ItineraryView extends FragmentView<ItineraryFragment> {

    @Bind(R.id.recycler_view)
    RecyclerView recyclerView;

    private static final int SCROLL_OFFSET = 20;
    private static final int SCROLL_DELAY = 200;

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

        adapter = new ItineraryCoodinatorAdapter(null);
        recyclerView.setLayoutManager(new LinearLayoutManager(activity));
        recyclerView.setAdapter(adapter);
    }

    public void scrollToPosition(int pos) {
        if (pos < adapter.getItemCount()) {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    ((LinearLayoutManager) recyclerView.getLayoutManager()).scrollToPositionWithOffset(pos, SCROLL_OFFSET);
                }
            }, SCROLL_DELAY);
        }
    }

    public void setGreetingText(RecyclerViewType greetingText) {
        adapter.addViewTypeOnceAndNotify(greetingText);
    }

    public void addPlans(List<RecyclerViewType> productModelList) {
        adapter.addAll(productModelList);
    }

}
