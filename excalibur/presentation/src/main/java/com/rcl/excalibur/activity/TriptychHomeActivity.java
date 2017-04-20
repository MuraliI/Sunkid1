package com.rcl.excalibur.activity;


import android.content.Intent;
import android.os.Bundle;

import com.rcl.excalibur.R;
import com.rcl.excalibur.mvp.presenter.TriptychHomePresenter;
import com.rcl.excalibur.mvp.view.TriptychHomeView;

public class TriptychHomeActivity extends BaseActivity {
    protected TriptychHomePresenter presenter;

    public static Intent getStartIntent(final BaseActivity activity) {
        return new Intent(activity, TriptychHomeActivity.class);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_triptych_home_screen);
//        final SailingPreferences sailingPreferences = new SailingPreferenceImpl(getApplicationContext());
        presenter = new TriptychHomePresenter(new TriptychHomeView(this)/*,
                new GetSailingPreferenceUseCase(sailingPreferences),
                new GetSaildDateDbUseCase(new SailDateDataRepository())*/);
        presenter.init();
    }

}
