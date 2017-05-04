package com.rcl.excalibur.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.rcl.excalibur.R;
import com.rcl.excalibur.data.repository.MenuDataRepository;
import com.rcl.excalibur.data.service.MenuServicesImpl;
import com.rcl.excalibur.data.utils.StringUtils;
import com.rcl.excalibur.domain.interactor.GetMenuDbUseCase;
import com.rcl.excalibur.domain.interactor.GetMenusUseCase;
import com.rcl.excalibur.mvp.presenter.DiningMenuPresenter;
import com.rcl.excalibur.mvp.view.DiningMenuView;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class DiningMenuActivity extends BaseActivity {
    private static final String EXTRA_VENUE_CODE = "EXTRA_VENUE_CODE";
    public DiningMenuPresenter presenter;

    public static Intent getStartIntent(final BaseActivity activity, String venueCode) {
        Intent intent = new Intent(activity, DiningMenuActivity.class);
        intent.putExtra(DiningMenuActivity.EXTRA_VENUE_CODE, StringUtils.encodeString(venueCode));
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dining_menu);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        if (intent != null && intent.hasExtra(EXTRA_VENUE_CODE)) {
            final String venueCode = intent.getStringExtra(EXTRA_VENUE_CODE);
            presenter = new DiningMenuPresenter(
                    new DiningMenuView(this),
                    new GetMenuDbUseCase(new MenuDataRepository()),
                    new GetMenusUseCase(new MenuServicesImpl(new MenuDataRepository())));
            presenter.init(StringUtils.decodeString(venueCode));
        }
    }

    @OnClick(R.id.menu_header_back)
    public void onHeaderBackOnClick() {
        presenter.onHeaderBackOnClick();
    }
}
