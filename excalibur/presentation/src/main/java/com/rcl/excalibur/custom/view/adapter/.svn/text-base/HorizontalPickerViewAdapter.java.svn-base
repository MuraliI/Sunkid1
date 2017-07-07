package com.rcl.excalibur.custom.view.adapter;


import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import com.rcl.excalibur.R;

import java.lang.ref.WeakReference;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observer;
import io.reactivex.subjects.PublishSubject;


public class HorizontalPickerViewAdapter<O> extends RecyclerView.Adapter<HorizontalPickerViewAdapter.HorizontalDeckPickerViewHolder> {

    private List<Pair<Integer, O>> items;
    private int selectedItem = -1;
    private WeakReference<Observer<Pair<Integer, O>>> observerWeakReference;

    public HorizontalPickerViewAdapter(@NonNull List<Pair<Integer, O>> items) {
        this.items = items;
    }

    @Override
    public HorizontalPickerViewAdapter.HorizontalDeckPickerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new HorizontalDeckPickerViewHolder(parent);
    }

    @Override
    public void onBindViewHolder(HorizontalPickerViewAdapter.HorizontalDeckPickerViewHolder holder, int position) {
        holder.deckNumber.setText(String.valueOf(items.get(position).first));

        if (selectedItem == position) {
            holder.itemView.setFocusableInTouchMode(true);
            holder.itemView.setFocusable(true);
            holder.itemView.requestFocus();
            if (holder.itemView.getOnFocusChangeListener() == null) {
                holder.itemView.setOnFocusChangeListener((focusedView, hasFocus) -> {
                    focusedView.setFocusableInTouchMode(hasFocus);
                    focusedView.setFocusable(hasFocus);
                    focusedView.setOnFocusChangeListener(null);
                });
            }
        } else {
            holder.itemView.setFocusableInTouchMode(false);
        }

        if (observerWeakReference.get() != null) {
            holder.clickPublisher.subscribe(observerWeakReference.get());
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void setItems(@NonNull List<Pair<Integer, O>> items) {
        this.items = items;
        notifyDataSetChanged();
    }

    public List<Pair<Integer, O>> getItems() {
        return Collections.unmodifiableList(items);
    }

    public void setItemClickObserver(Observer<Pair<Integer, O>> observer) {
        observerWeakReference = new WeakReference<>(observer);
    }

    public void setSelectedItem(Pair<Integer, O> item) {
        selectedItem = items.indexOf(item);
    }

    class HorizontalDeckPickerViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.text_deck_selector_number) TextView deckNumber;

        private PublishSubject<Pair<Integer, O>> clickPublisher = PublishSubject.create();

        HorizontalDeckPickerViewHolder(ViewGroup parent) {
            super(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_deck_selector_textview, parent, false));
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(view -> {
                clickPublisher.onNext(items.get(getAdapterPosition()));
                selectedItem = getAdapterPosition();
            });
        }
    }
}
