package com.rcl.excalibur.adapters.delegate;


import android.support.v4.util.SparseArrayCompat;
import android.support.v7.widget.RecyclerView;

import com.rcl.excalibur.adapters.base.BaseCoordinatorAdapter;
import com.rcl.excalibur.adapters.base.DelegateAdapter;
import com.rcl.excalibur.adapters.base.RecyclerViewConstants;
import com.rcl.excalibur.adapters.base.RecyclerViewType;
import com.rcl.excalibur.adapters.delegate.viewholder.base.ExpandableContentViewHolder;

import java.util.List;

import io.reactivex.Observer;

public class DetailViewCoordinatorAdapter<VH extends RecyclerView.ViewHolder, VT extends RecyclerViewType>
        extends BaseCoordinatorAdapter<VH, VT, String> implements ExpandableContentViewHolder.OnViewExpandedListener<VT> {

    public interface OnViewExpandedListener {
        void onViewExpanded(Integer integer);
    }

    private static final int VIEW_TYPE_COUNT = 7;

    private OnViewExpandedListener listener;

    @SuppressWarnings("unchecked")
    public DetailViewCoordinatorAdapter(Observer<String> observer, List<VT> recyclerViewTypes) {
        super(observer);
        //TODO each one will be in charge of adding its own module to the list of modules.
        delegateAdapters = new SparseArrayCompat<>(VIEW_TYPE_COUNT);

        delegateAdapters.append(RecyclerViewConstants.VIEW_TYPE_PRICES_FROM,
                (DelegateAdapter<VH, VT>) new PricesFromDelegateAdapter());
        delegateAdapters.append(RecyclerViewConstants.VIEW_TYPE_PRODUCT_BASIC_INFORMATION,
                (DelegateAdapter<VH, VT>) new ProductInformationDelegateAdapter(getObserver()));
        delegateAdapters.append(RecyclerViewConstants.VIEW_TYPE_TITLE_AND_DESCRIPTION,
                (DelegateAdapter<VH, VT>) new TitleAndDescriptionDelegateAdapter());
        delegateAdapters.append(RecyclerViewConstants.VIEW_TYPE_PRODUCT_BASIC_INFORMATION,
                (DelegateAdapter<VH, VT>) new ProductInformationDelegateAdapter(getObserver()));
        delegateAdapters.append(RecyclerViewConstants.VIEW_TYPE_DESCRIPTION,
                (DelegateAdapter<VH, VT>) new DescriptionDelegateAdapter());
        delegateAdapters.append(RecyclerViewConstants.VIEW_TYPE_EXPANDABLE_LINK,
                (DelegateAdapter<VH, VT>) new ExpandableLinkDelegateAdapter(this));
        delegateAdapters.append(RecyclerViewConstants.VIEW_TYPE_ACCESSIBILITY_VIEW,
                (DelegateAdapter<VH, VT>) new ExpandableAccessibilityDelegateAdapter(this));
        delegateAdapters.append(RecyclerViewConstants.VIEW_TYPE_TIMES,
                (DelegateAdapter<VH, VT>) new TimesDelegateAdapter());
        addAll(recyclerViewTypes);
    }

    public void setOnViewExpandedListener(OnViewExpandedListener listener) {
        this.listener = listener;
    }

    @Override
    public void onViewExpanded(VT viewType) {
        if (listener != null) {
            listener.onViewExpanded(items.indexOf(viewType));
        }
    }
}
