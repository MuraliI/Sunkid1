package com.rcl.excalibur.adapters.base;


import com.rcl.excalibur.adapters.delegate.viewholder.ExpandableAccessibiltyViewHolder;
import com.rcl.excalibur.adapters.delegate.viewholder.base.ExpandableContentViewHolder;

import java.lang.ref.WeakReference;

public abstract class ExpandableContentDelegateAdapter<EVH extends ExpandableContentViewHolder<DVT>
        , DVT extends RecyclerViewType> implements DelegateAdapter<EVH, DVT> {

    protected WeakReference<ExpandableAccessibiltyViewHolder.OnViewExpandedListener<DVT>> listener;

    public ExpandableContentDelegateAdapter(ExpandableAccessibiltyViewHolder.OnViewExpandedListener<DVT> listener) {
        this.listener = new WeakReference<>(listener);
    }
}
