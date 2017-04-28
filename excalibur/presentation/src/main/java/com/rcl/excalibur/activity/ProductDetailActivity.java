package com.rcl.excalibur.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;

import com.rcl.excalibur.R;
import com.rcl.excalibur.data.repository.OfferingDataRepository;
import com.rcl.excalibur.data.repository.ProductDataRepository;
import com.rcl.excalibur.data.repository.SailDateDataRepository;
import com.rcl.excalibur.domain.interactor.GetOfferingsDbUseCase;
import com.rcl.excalibur.domain.interactor.GetProductDbUseCase;
import com.rcl.excalibur.domain.interactor.GetSaildDateDbUseCase;
import com.rcl.excalibur.mvp.presenter.ProductDetailPresenter;
import com.rcl.excalibur.mvp.view.ProductDetailView;
import com.rcl.excalibur.utils.IntentExtraUtils;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class ProductDetailActivity extends BaseActivity implements AppBarLayout.OnOffsetChangedListener {
    private static final String EXTRA_DISCOVER_ITEM_ID = "EXTRA_DISCOVER_ITEM_ID";
    protected ProductDetailPresenter presenter;

    public static Intent getIntent(final BaseActivity activity, String productId) {
        Intent intent = new Intent(activity, ProductDetailActivity.class);
        intent.putExtra(EXTRA_DISCOVER_ITEM_ID, IntentExtraUtils.encodePutExtraString(productId));
        return intent;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        if (intent == null || !intent.hasExtra(EXTRA_DISCOVER_ITEM_ID)) {
            return;
        }
        String productId = intent.getExtras().getString(EXTRA_DISCOVER_ITEM_ID);
        presenter = new ProductDetailPresenter(new ProductDetailView(this)
                , new GetProductDbUseCase(new ProductDataRepository())
                , new GetOfferingsDbUseCase(new OfferingDataRepository())
                , new GetSaildDateDbUseCase(new SailDateDataRepository()));
        presenter.init(IntentExtraUtils.decodeGetExtraString(productId));
    }

    @OnClick(R.id.back_arrow)
    void onBackClicked() {
        presenter.onBackClicked();
    }

    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
        presenter.onOffsetChanged(verticalOffset, appBarLayout.getTotalScrollRange());
    }
}
