package com.rcl.excalibur.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.rcl.excalibur.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class DayPickerFragment extends Fragment {


    @Bind(R.id.day_picker_startandend_date)
    TextView dateStarAndEnd;
    public static DayPickerFragment newInstance() {
        return new DayPickerFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_day_picker, container, false);
        ButterKnife.bind(this, view);
        dateStarAndEnd.setText("Feb 15 - Mar 3");
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}
