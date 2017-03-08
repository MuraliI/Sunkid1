package com.rcl.excalibur.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rcl.excalibur.R;
import com.rcl.excalibur.mvp.presenter.DiscoverPresenter;
import com.rcl.excalibur.mvp.view.DiscoverView;

public class DiscoverFragment extends Fragment {

    public static final int ROYAL_ACTIVITY = 0;
    public static final int DINING = 1;
    public static final int SHOPPING = 2;
    public static final int SPA = 3;
    public static final int SHOREX = 4;
    public static final int ENTERTAINMENT = 5;
    private static final String ARGUMENT_TYPE = "DiscoverFragment.ARGUMENT_TYPE";
    private DiscoverPresenter presenter;

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
        presenter = new DiscoverPresenter(type, new DiscoverView(this));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_discover, container, false);
    }
}