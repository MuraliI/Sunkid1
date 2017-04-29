package com.rcl.excalibur.activity;


import android.content.Intent;
import android.os.Bundle;

import com.rcl.excalibur.R;
import com.rcl.excalibur.data.repository.ProductDataRepository;
import com.rcl.excalibur.domain.interactor.GetProductDbUseCase;
import com.rcl.excalibur.mvp.presenter.DeckMapPresenter;
import com.rcl.excalibur.mvp.view.DeckMapView;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class DeckMapActivity extends BaseActivity {
    private static final String EXTRA_PRODUCT_ITEM_ID = "EXTRA_PRODUCT_ITEM_ID";

    protected DeckMapPresenter presenter;

    public static Intent getIntent(final BaseActivity activity, String productItemId) {
        Intent intent = new Intent(activity, DeckMapActivity.class);
        intent.putExtra(EXTRA_PRODUCT_ITEM_ID, productItemId);
        return intent;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discover_deck_map);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        if (intent == null || !intent.hasExtra(EXTRA_PRODUCT_ITEM_ID)) {
            return;
        }
        final String productItemId = intent.getStringExtra(EXTRA_PRODUCT_ITEM_ID);
        presenter = new DeckMapPresenter(new DeckMapView(this),
                new GetProductDbUseCase(new ProductDataRepository()));
        presenter.init(productItemId);
    }

    @OnClick(R.id.button_close_deck_map)
    public void onHeaderBackOnClick() {
        presenter.onHeaderBackOnClick();
    }
}
