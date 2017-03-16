package com.rcl.excalibur.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rcl.excalibur.R;
import com.rcl.excalibur.mvp.presenter.ItineraryPresenter;

public class ItineraryFragment extends Fragment {
    private static final String ARGUMENT_TYPE = "ItineraryFragment.ARGUMENT_TYPE";
    private ItineraryPresenter presenter;

    public static DiscoverFragment newInstance(int type) {
        DiscoverFragment fragment = new DiscoverFragment();
        Bundle args = new Bundle();
        args.putInt(ARGUMENT_TYPE, type);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        final Bundle bundle = getArguments();
        if (bundle == null || !bundle.containsKey(ARGUMENT_TYPE)) {
            return;
        }
        final int type = bundle.getInt(ARGUMENT_TYPE);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_itenerary, container, false);
    }
}
