package com.rcl.excalibur.mvp.view;


import android.app.Activity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.rcl.excalibur.R;
import com.rcl.excalibur.adapters.EventsAdapter;
import com.rcl.excalibur.fragments.DayPickerFragment;
import com.rcl.excalibur.model.EventModel;
import com.rcl.excalibur.mvp.view.base.FragmentView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class DayPickerView extends FragmentView<DayPickerFragment, Void, EventModel> {

    @Bind(R.id.day_picker_startandend_date) TextView fotterDate;
    @Bind(R.id.day_picker_header_description) TextView dateDescription;
    @Bind(R.id.day_picker_header_calendar_day) TextView dayTitle;
    @Bind(R.id.recycler_discover_item_details) RecyclerView recyclerView;

    private EventsAdapter adapter;

    public DayPickerView(DayPickerFragment fragment) {
        super(fragment);
        ButterKnife.bind(this, fragment.getView());
    }

    public void init(int todayPosition) {
        final Activity activity = getActivity();
        if (activity == null) {
            return;
        }
        adapter = new EventsAdapter(adapterObserver, activity.getResources(), todayPosition);
        recyclerView.setLayoutManager(new LinearLayoutManager(activity));
        recyclerView.setAdapter(adapter);
    }

    public void setFotterDate(String date) {
        fotterDate.setText(date);
    }

    public void setHeader(String description, String day) {
        dateDescription.setText(description);
        dayTitle.setText(day);
    }

    public void addAll(List<EventModel> list) {
        adapter.addAll(list);
    }
}

