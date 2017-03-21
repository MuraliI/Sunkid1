package com.rcl.excalibur.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rcl.excalibur.R;


public class MeTabFragment extends Fragment {

    public static MeTabFragment newInstance() {
        return new MeTabFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_me_tab, container, false);
    }
}
