package com.rcl.excalibur.adapters.delegate.itinerary;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import com.rcl.excalibur.R;
import com.rcl.excalibur.adapters.base.DelegateAdapter;
import com.rcl.excalibur.adapters.delegate.itinerary.ItineraryBottomDelegateAdapter.ItineraryBottomViewHolder;
import com.rcl.excalibur.model.itinerary.ItineraryBottomModel;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ItineraryBottomDelegateAdapter implements DelegateAdapter<
        ItineraryBottomViewHolder, ItineraryBottomModel> {

    @Override
    public ItineraryBottomViewHolder onCreateViewHolder(ViewGroup parent) {
        return new ItineraryBottomViewHolder(parent);
    }

    @Override
    public void onBindViewHolder(ItineraryBottomViewHolder holder, ItineraryBottomModel item) {

    }

    class ItineraryBottomViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.text_itinerary_list_bottom_emergency) TextView emergencyText;
        @Bind(R.id.text_itinerary_list_bottom_legal) TextView legalText;
        @Bind(R.id.text_itinerary_list_bottom_about) TextView aboutText;

        ItineraryBottomViewHolder(ViewGroup parent) {
            super(LayoutInflater.from(parent.getContext()).inflate(R.layout.itinerary_item_bottom, parent, false));
            ButterKnife.bind(this, itemView);
        }
    }
}
