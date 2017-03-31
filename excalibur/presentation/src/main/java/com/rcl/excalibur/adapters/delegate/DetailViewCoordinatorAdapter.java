package com.rcl.excalibur.adapters.delegate;


import android.support.v4.util.SparseArrayCompat;

import com.rcl.excalibur.adapters.base.BaseCoordinatorAdapter;
import com.rcl.excalibur.adapters.base.RecyclerViewConstants;
import com.rcl.excalibur.adapters.base.RecyclerViewType;

import java.util.List;

import io.reactivex.Observer;

public class DetailViewCoordinatorAdapter extends BaseCoordinatorAdapter {

    private static final int VIEW_TYPE_COUNT = 1;

    public DetailViewCoordinatorAdapter(Observer observer,
                                        List<RecyclerViewType> recyclerViewTypes) {
        super(observer);
        //TODO each one will be in charge of adding its own module to the list of modules.
        delegateAdapters = new SparseArrayCompat<>(VIEW_TYPE_COUNT);
        delegateAdapters.append(RecyclerViewConstants.VIEW_TYPE_TITLE_AND_DESCRIPTION, new TitleAndDescriptionDelegateAdapter());
        addAll(recyclerViewTypes);
    }
}
