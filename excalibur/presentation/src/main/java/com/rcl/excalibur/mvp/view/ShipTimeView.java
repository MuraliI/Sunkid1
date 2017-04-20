package com.rcl.excalibur.mvp.view;


import android.widget.TextView;

import com.rcl.excalibur.R;
import com.rcl.excalibur.activity.BaseActivity;
import com.rcl.excalibur.fragments.ShipTimeFragment;
import com.rcl.excalibur.mvp.view.base.FragmentView;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ShipTimeView extends FragmentView<ShipTimeFragment, Void, Void> {
    @Bind(R.id.ship_time_date) TextView shipTimeDate;

    public ShipTimeView(ShipTimeFragment fragment) {
        super(fragment);
        ButterKnife.bind(this, fragment.getView());
    }

    public void display(final String value) {
        final BaseActivity activity = getActivity();
        if (activity == null) {
            return;
        }
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                shipTimeDate.setText(value);

            }
        });


    }
}
