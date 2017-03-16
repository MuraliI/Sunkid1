package com.rcl.excalibur.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;

import com.rcl.excalibur.R;
import com.rcl.excalibur.fragments.ItineraryFragment;

public class ItineraryActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_itinerary);
        attachFragment();

    }

    public void attachFragment() {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        transaction.replace(R.id.fragment_container, ItineraryFragment.newInstance(1));
        transaction.addToBackStack(null);

        transaction.commit();
    }


}
