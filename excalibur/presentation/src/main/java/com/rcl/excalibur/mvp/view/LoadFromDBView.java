package com.rcl.excalibur.mvp.view;


import android.app.Activity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Button;

import com.rcl.excalibur.activity.LoadFromDBActivity;
import com.rcl.excalibur.adapters.ItemsAdapter;
import com.rcl.excalibur.R;
import com.rcl.excalibur.model.ItemModel;

import java.util.Collection;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;

public class LoadFromDBView extends ActivityView<LoadFromDBActivity> {
    public static final String ACTION_VIEW_ON_CLICK = "LoadFromDBView.ACTION_VIEW_ON_CLICK";
    @Bind(R.id.recycler_view) RecyclerView recyclerView;
    @Bind(R.id.btn) Button button;

    public LoadFromDBView(LoadFromDBActivity activity) {
        super(activity);
        ButterKnife.bind(this, activity);
    }

    @OnClick(R.id.btn)
    public void onClick() {
        if (viewObserver == null) {
            return;
        }
        Observable.just(ACTION_VIEW_ON_CLICK)
                .subscribe(viewObserver);
    }

    public void render(Collection<ItemModel> itemModelsCollection) {
        final Activity activity = getActivity();
        if (activity == null) {
            return;
        }
        Log.d("", "");
        recyclerView.setLayoutManager(new LinearLayoutManager(activity));
        ItemsAdapter adapter = new ItemsAdapter((List<ItemModel>) itemModelsCollection, adapterObserver);
        recyclerView.setAdapter(adapter);
    }
}
