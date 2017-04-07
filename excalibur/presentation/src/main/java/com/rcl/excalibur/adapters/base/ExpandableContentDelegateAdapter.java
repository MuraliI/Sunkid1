package com.rcl.excalibur.adapters.base;


import com.rcl.excalibur.adapters.delegate.viewholder.ExpandableAccessibiltyViewHolder;
import com.rcl.excalibur.adapters.delegate.viewholder.base.ExpandableContentViewHolder;

import java.lang.ref.WeakReference;

public abstract class ExpandableContentDelegateAdapter<EVH extends ExpandableContentViewHolder<EXVT>,
        DVT extends RecyclerViewType,
        EXVT extends RecyclerViewType> implements DelegateAdapter<EVH, DVT> {

    protected WeakReference<ExpandableAccessibiltyViewHolder.OnViewExpandedListener<EXVT>> listener;

    public ExpandableContentDelegateAdapter(ExpandableAccessibiltyViewHolder.OnViewExpandedListener<EXVT> listener) {
        this.listener = new WeakReference<>(listener);
    }
}
