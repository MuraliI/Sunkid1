package com.rcl.excalibur.mvp.presenter;


import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;

import com.rcl.excalibur.model.PlanModel;
import com.rcl.excalibur.mvp.view.HomeView;

import java.util.ArrayList;
import java.util.List;

public class HomePresenter implements BasePresenter, LoaderManager.LoaderCallbacks<Cursor> {
    private HomeView view;

    public HomePresenter(HomeView view) {
        this.view = view;
        init();
    }

    private void init() {
        view.init();
//        TODO hard
        List<PlanModel> planModels = new ArrayList();
        planModels.add(new PlanModel());
        planModels.add(new PlanModel());
        view.addAll(planModels);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return null;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {

    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }
}
