package com.rcl.excalibur.activity;


import android.os.Bundle;

import com.rcl.excalibur.R;
import com.rcl.excalibur.mvp.presenter.LoadFromDBPresenter;
import com.rcl.excalibur.mvp.view.LoadFromDBView;

public class LoadFromDBActivity extends BaseActivity {

    private LoadFromDBPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load_from_db);
        presenter = new LoadFromDBPresenter(new LoadFromDBView(this));
    }
}
