package com.rcl.excalibur.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.rcl.excalibur.R;
import com.rcl.excalibur.model.EventModel;
import com.rcl.excalibur.model.PortModel;

import java.lang.ref.WeakReference;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observer;

import static io.reactivex.Observable.just;

public class EventsAdapter extends BaseAdapter<EventModel, EventsAdapter.DayPickerViewHolder> {

    public EventsAdapter(Observer<EventModel> observer) {
        super(observer);
    }

    @Override
    public void onBindViewHolder(DayPickerViewHolder holder, int position) {
        holder.event = items.get(position);
        Context context = holder.portTypeImageView.getContext();

        if (!TextUtils.isEmpty(holder.event.getDay())) {
            holder.dayTextView.setText(holder.event.getDay());
        }
        PortModel port = holder.event.getPort();
        if (port == null) {
            return;
        }
        String portName = port.getPortName();
        if (!TextUtils.isEmpty(portName)) {
            holder.placeTextView.setText(portName);
        }
        String portType = port.getPortType();
        if (!TextUtils.isEmpty(portType)) {
            if (portType.equalsIgnoreCase(PortModel.PORT_TYPE_EMBARK) || portType.equalsIgnoreCase(PortModel.PORT_TYPE_DOCKED)) {
                holder.portTypeImageView.setBackgroundResource(R.drawable.icon_day_picker_anchor);
            } else {
                if (portType.equalsIgnoreCase(PortModel.PORT_TYPE_CRUISING)) {
                    holder.portTypeImageView.setBackgroundResource(R.drawable.icon_day_picker_sea);
                }
            }
        }
    }

    @Override
    protected int getLayout() {
        return R.layout.item_day_picker;
    }

    @NonNull
    @Override
    protected DayPickerViewHolder getViewHolder(View view) {
        return new DayPickerViewHolder(view);
    }

    static class DayPickerViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.text_day) TextView dayTextView;
        @Bind(R.id.text_place) TextView placeTextView;
        @Bind(R.id.image_port_type) ImageView portTypeImageView;
        @Bind(R.id.image_is_today) ImageView isTodayImageView;

        @Bind(R.id.container_day_picker) RelativeLayout container;
        private EventModel event;
        private WeakReference<Observer<EventModel>> observerRef;

        DayPickerViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @OnClick(R.id.container_day_picker)
        void onItemClick() {
            if (observerRef == null) {
                return;
            }
            just(event).subscribe(observerRef.get());
        }
    }
}
