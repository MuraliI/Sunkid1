package com.rcl.excalibur.mvp.view;


import android.app.Activity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.rcl.excalibur.R;
import com.rcl.excalibur.fragments.ItineraryFragment;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ItineraryView extends FragmentView<ItineraryFragment> {
    @Bind(R.id.recycler_view)
    RecyclerView recyclerView;

    public ItineraryView(ItineraryFragment fragment) {
        super(fragment);
        ButterKnife.bind(this, fragment.getView());
    }

    public void init() {
        final Activity activity = getActivity();
        if (activity == null) {
            return;
        }
        recyclerView.setLayoutManager(new LinearLayoutManager(activity));
        //recyclerView.setAdapter(adapter);
    }
}
