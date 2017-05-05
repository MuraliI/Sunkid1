package com.rcl.excalibur.adapters.delegate;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.rcl.excalibur.R;
import com.rcl.excalibur.activity.BaseActivity;
import com.rcl.excalibur.activity.DiningMenuActivity;
import com.rcl.excalibur.adapters.base.DelegateAdapter;
import com.rcl.excalibur.adapters.viewtype.DiningTimesViewType;
import com.rcl.excalibur.data.utils.CollectionUtils;
import com.rcl.excalibur.domain.LocationOperationHour;
import com.rcl.excalibur.domain.ProductAdvisement;
import com.rcl.excalibur.utils.ActivityUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DiningTimesDelegateAdapter implements DelegateAdapter<TimesDelegateAdapter.TimesViewHolder, DiningTimesViewType> {

    private static final String DINING_TYPE_SPECIALTY = "Specialty";
    private BaseActivity baseActivity;

    public DiningTimesDelegateAdapter(BaseActivity activity) {
        baseActivity = activity;
    }

    @Override
    public TimesDelegateAdapter.TimesViewHolder onCreateViewHolder(ViewGroup parent) {
        return new TimesDelegateAdapter.TimesViewHolder(parent);
    }

    @Override
    public void onBindViewHolder(TimesDelegateAdapter.TimesViewHolder holder, DiningTimesViewType item) {
        Context context = holder.itemView.getContext();
        holder.textTitle.setText(item.getTitle());

        if (item.getProduct().getProductLocation() != null
                && item.getProduct().getProductLocation().getLocationOperationHours() != null) {
            List<LocationOperationHour> operationHours = item.getProduct().getProductLocation().getLocationOperationHours();

            Map<String, List<LocationOperationHour>> map = new HashMap<>();
            List<String> listDays = new ArrayList<>();

            for (LocationOperationHour operatingHour : operationHours) {
                if (map.containsKey(operatingHour.getDayNumber())) {
                    map.get(operatingHour.getDayNumber()).add(operatingHour);
                } else {
                    List<LocationOperationHour> newList = new ArrayList<>();
                    newList.add(operatingHour);
                    map.put(operatingHour.getDayNumber(), newList);
                    listDays.add(operatingHour.getDayNumber());
                }
            }

            Collections.sort(listDays, (o1, o2) -> {
                try {
                    int dayA = Integer.parseInt(o1);
                    int dayB = Integer.parseInt(o2);
                    if (dayA < dayB) {
                        return -1;
                    } else {
                        return 1;
                    }
                } catch (Exception ex) {
                    return 0;
                }
            });

            for (String day : listDays) {
                View itemView = LayoutInflater.from(context).inflate(R.layout.item_dining_time, null);
                TextView dayText = (TextView) itemView.findViewById(R.id.day_number);
                LinearLayout diningTimesContainer = (LinearLayout) itemView.findViewById(R.id.dining_times_container);
                dayText.setText(context.getResources().getString(R.string.day_arg, day));

                List<LocationOperationHour> operationHoursByDay = map.get(day);

                for (LocationOperationHour operationHour : operationHoursByDay) {
                    View itemDiningView = LayoutInflater.from(context).inflate(R.layout.item_dining_text_dash_text, null);
                    TextView text = (TextView) itemDiningView.findViewById(R.id.text);
                    TextView subtext = (TextView) itemDiningView.findViewById(R.id.subtext);
                    LinearLayout showMenu = (LinearLayout) itemDiningView.findViewById(R.id.show_menu_container);
                    text.setText(operationHour.getTimeOfDay());
                    subtext.setText(operationHour.getStartTime());

                    showMenu.setVisibility(View.GONE);
                    List<ProductAdvisement> advisementsDiningType = item.getProduct()
                            .getProductAdvisementsByType(ProductAdvisement.DINING_TYPE);
                    if (!CollectionUtils.isEmpty(advisementsDiningType)) {
                        ProductAdvisement diningType = advisementsDiningType.get(0);
                        if (diningType != null && diningType.getAdvisementTitle().equals(DINING_TYPE_SPECIALTY)) {
                            showMenu.setVisibility(View.VISIBLE);
                            showMenu.setOnClickListener(v -> ActivityUtils.startActivity(baseActivity,
                                    DiningMenuActivity.getStartIntent(baseActivity, "CHOP")));
                        }
                    }
                    diningTimesContainer.addView(itemDiningView);
                }
                holder.timesContainer.addView(itemView);
            }

            holder.showMoreContainer.setVisibility(listDays.size() > 1 ? View.VISIBLE : View.INVISIBLE);
            holder.collapseOrExpandContent(true);
        }
    }

    public static class DiningTimesViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.text_module_title) TextView textTitle;
        @BindView(R.id.times_container) LinearLayout timesContainer;
        @BindView(R.id.show_more_container) LinearLayout showMoreContainer;
        @BindView(R.id.show_more_arrow) ImageView showMoreArrow;

        private boolean collapsed = true;

        public DiningTimesViewHolder(ViewGroup parent) {
            super(LayoutInflater.from(parent.getContext()).inflate(R.layout.module_item_detail_times, parent, false));
            ButterKnife.bind(this, itemView);
        }

        @OnClick(R.id.show_more_container)
        public void showMoreClick() {
            collapsed = !collapsed;
            collapseOrExpandContent(collapsed);
        }

        public void collapseOrExpandContent(boolean collapse) {
            for (int i = 0; i < timesContainer.getChildCount(); i++) {
                View v = timesContainer.getChildAt(i);
                if (i > 0) {
                    v.setVisibility(collapse ? View.GONE : View.VISIBLE);
                }
            }
            showMoreArrow.setImageResource(collapsed ? R.drawable.ic_chevron_down : R.drawable.ic_chevron_up);
        }
    }
}


