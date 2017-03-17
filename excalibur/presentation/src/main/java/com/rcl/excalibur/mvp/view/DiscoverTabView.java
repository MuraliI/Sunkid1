package com.rcl.excalibur.mvp.view;

import android.app.Activity;
import com.rcl.excalibur.fragments.DiscoverTabFragment;
import butterknife.ButterKnife;

/**
 * Created by asielgil on 3/17/17.
 */

public class DiscoverTabView extends FragmentView<DiscoverTabFragment> {

    public DiscoverTabView(DiscoverTabFragment fragment) {
        super(fragment);
        ButterKnife.bind(this, fragment.getView());
    }

    public void init() {
        final Activity activity = getActivity();
        if (activity == null) {
            return;
        }
    }
}
