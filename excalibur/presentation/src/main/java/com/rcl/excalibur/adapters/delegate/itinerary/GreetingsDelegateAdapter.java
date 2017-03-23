package com.rcl.excalibur.adapters.delegate.itinerary;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import com.rcl.excalibur.R;
import com.rcl.excalibur.adapters.base.DelegateAdapter;
import com.rcl.excalibur.adapters.viewtype.itinerary.GreetingViewType;

import butterknife.Bind;
import butterknife.ButterKnife;

public class GreetingsDelegateAdapter implements DelegateAdapter<GreetingsDelegateAdapter.GreetingsViewHolder, GreetingViewType> {

    @Override
    public GreetingsViewHolder onCreateViewHolder(ViewGroup parent) {
        return new GreetingsViewHolder(parent);
    }

    @Override
    public void onBindViewHolder(GreetingsViewHolder holder, GreetingViewType item) {
        holder.greetingsText.setText(item.getGreeting());
    }

    class GreetingsViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.text_greeting) TextView greetingsText;

        GreetingsViewHolder(ViewGroup parent) {
            super(LayoutInflater.from(parent.getContext()).inflate(R.layout.itinerary_item_header_greeting, parent, false));
            ButterKnife.bind(this, itemView);
        }
    }
}
