package com.rcl.excalibur.activity;


import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;

import com.rcl.excalibur.R;
import com.rcl.excalibur.data.preference.SailingPreferenceImpl;
import com.rcl.excalibur.data.repository.ProductDataRepository;
import com.rcl.excalibur.data.repository.SailDateDataRepository;
import com.rcl.excalibur.data.service.SailDateServicesImpl;
import com.rcl.excalibur.domain.interactor.GetProductDbUseCase;
import com.rcl.excalibur.domain.interactor.GetSaildDateDbUseCase;
import com.rcl.excalibur.domain.interactor.GetSaildDateUseCase;
import com.rcl.excalibur.domain.interactor.GetSailingPreferenceUseCase;
import com.rcl.excalibur.domain.preference.SailingPreferences;
import com.rcl.excalibur.mapper.SailingInformationModelDataMapper;
import com.rcl.excalibur.mvp.presenter.TriptychHomePresenter;
import com.rcl.excalibur.mvp.view.TriptychHomeView;
import com.rcl.excalibur.utils.ActivityUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TriptychHomeActivity extends BaseActivity {
    @BindView(R.id.day_picker_tab)
    View tabElement;
    @BindView(R.id.image_ship)
    View shipElement;
    @BindView(R.id.pager_triptych_pager)
    View pagerElement;

    private TriptychHomePresenter presenter;
    private SailingPreferences sailingPreferences;

    public static Intent getStartIntent(final BaseActivity activity) {
        return new Intent(activity, TriptychHomeActivity.class);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_triptych_home_screen);
        sailingPreferences = new SailingPreferenceImpl(this);
        presenter = new TriptychHomePresenter(
                new TriptychHomeView(this),
                new GetProductDbUseCase(new ProductDataRepository()),
                new GetSaildDateUseCase(new SailDateServicesImpl(new SailDateDataRepository())),
                new GetSailingPreferenceUseCase(sailingPreferences),
                new GetSaildDateDbUseCase(new SailDateDataRepository()),
                new SailingInformationModelDataMapper()
        );
        presenter.init();
    }

    public void getShipLocationInfo() {
        presenter.getShipLocationInfo();
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.getShipLocationInfo();
    }

    public void goToVoyageActivity() {
        ButterKnife.bind(this);
        Pair<View, String> tabPair = Pair.create(tabElement, getString(R.string.shared_element_tab));
        Pair<View, String> shipPair = Pair.create(shipElement, shipElement.getTransitionName());
        ActivityUtils.startActivityWithSharedElements(this, VoyageMapActivity.getStartIntent(this), tabPair, shipPair,
                presenter.getPlannerSharedElementPair());
    }
}
