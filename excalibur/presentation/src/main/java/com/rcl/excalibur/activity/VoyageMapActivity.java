package com.rcl.excalibur.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.transition.Transition;

import com.rcl.excalibur.R;
import com.rcl.excalibur.data.preference.SailingPreferenceImpl;
import com.rcl.excalibur.domain.interactor.GetSailingPreferenceUseCase;
import com.rcl.excalibur.domain.preference.SailingPreferences;
import com.rcl.excalibur.mvp.presenter.VoyageMapPresenter;
import com.rcl.excalibur.mvp.view.VoyageMapView;

import butterknife.ButterKnife;

public class VoyageMapActivity extends BaseActivity {
    VoyageMapPresenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voyage_map);
        ButterKnife.bind(this);
        final SailingPreferences sailingPreferences = new SailingPreferenceImpl(getApplicationContext());
        presenter = new VoyageMapPresenter(new VoyageMapView(this), new GetSailingPreferenceUseCase(sailingPreferences));
        presenter.initTab();
        Transition transition = getWindow().getSharedElementEnterTransition();
        transition.addListener(new Transition.TransitionListener() {
            @Override
            public void onTransitionStart(Transition transition) {
                // Nothing
            }

            @Override
            public void onTransitionEnd(Transition transition) {
                presenter.initMap();
            }

            @Override
            public void onTransitionCancel(Transition transition) {
                // Nothing
            }

            @Override
            public void onTransitionPause(Transition transition) {
                // Nothing
            }

            @Override
            public void onTransitionResume(Transition transition) {
                // Nothing
            }
        });
    }

    @Override
    public void onBackPressed() {
        presenter.onBackPressed();
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.onResume();
    }

    public static Intent getStartIntent(BaseActivity activity) {
        return new Intent(activity, VoyageMapActivity.class);
    }
}
