package com.rcl.excalibur.adapters;

import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
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
    private Resources resources;
    private int todayPosition;
    private int positionSelected = -1;
    private int selectedDay;

    public EventsAdapter(Observer<EventModel> observer, Resources resources, int todayPosition, int selectedDay) {
        super(observer);
        this.resources = resources;
        this.todayPosition = todayPosition;
        this.selectedDay = selectedDay;
    }

    @Override
    public void onBindViewHolder(DayPickerViewHolder holder, int position) {
        holder.event = items.get(position);

        String day = holder.event.getDay();
        if (!TextUtils.isEmpty(day)) {
            if (todayPosition == position) {
                holder.isTodayImageView.setImageResource(R.drawable.icon_day_picker_ship);
                holder.dayTextView.setText(resources.getString(R.string.today_day_title));
            } else {
                holder.dayTextView.setText(resources.getString(R.string.day_title) + day);
                holder.isTodayImageView.setImageResource(0);
            }
        }
        PortModel port = holder.event.getPort();
        if (port == null) {
            return;
        }
        String portName = port.getPortName();
        String portType = port.getPortType();
        if (!TextUtils.isEmpty(portName)) {
            if (!TextUtils.isEmpty(portType)) {
                if (portType.equalsIgnoreCase(PortModel.PORT_TYPE_CRUISING)) {
                    holder.placeTextView.setText(R.string.day_picker_at_sea);
                } else {
                    holder.placeTextView.setText(portName);
                }
            }
        }
        if (!TextUtils.isEmpty(portType)) {
            if (portType.equalsIgnoreCase(PortModel.PORT_TYPE_EMBARK) || portType.equalsIgnoreCase(PortModel.PORT_TYPE_DOCKED) || portType.equalsIgnoreCase(PortModel.PORT_TYPE_DEBARK)) {
                holder.portTypeImageView.setImageResource(R.drawable.icon_day_picker_anchor);
            } else if (PortModel.PORT_TYPE_CRUISING.equalsIgnoreCase(portType)) {
                holder.portTypeImageView.setImageResource(R.drawable.icon_day_picker_sea);
            }

        }
        if (hasObserver()) {
            holder.observerRef = new WeakReference<>(getObserver());
        }
        holder.selectedDayView.setVisibility(selectedDay == position ? View.VISIBLE : View.GONE);
        holder.containerDayPicker.setSelected(position == positionSelected);
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

    class DayPickerViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.text_day) TextView dayTextView;
        @Bind(R.id.text_place) TextView placeTextView;
        @Bind(R.id.image_port_type) ImageView portTypeImageView;
        @Bind(R.id.image_is_today) ImageView isTodayImageView;
        @Bind(R.id.view_select_day) View selectedDayView;
        @Bind(R.id.container_day_picker) View containerDayPicker;
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
            positionSelected = getAdapterPosition();
            just(event).subscribe(observerRef.get());
        }
    }
}
