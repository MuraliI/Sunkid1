package com.rcl.excalibur.adapters.delegate;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.rcl.excalibur.R;
import com.rcl.excalibur.adapters.base.DelegateAdapter;
import com.rcl.excalibur.adapters.viewtype.DinnerTimesViewType;

import butterknife.Bind;
import butterknife.ButterKnife;
import timber.log.Timber;

public class DinnerTimesDelegateAdapter implements DelegateAdapter<DinnerTimesDelegateAdapter.TimesViewHolder, DinnerTimesViewType> {
    private static final String INTENT_DATA_TYPE = "application/pdf";

    @Override
    public TimesViewHolder onCreateViewHolder(ViewGroup parent) {
        return new TimesViewHolder(parent);
    }

    @Override
    public void onBindViewHolder(TimesViewHolder holder, DinnerTimesViewType item) {
        holder.lunchTime.setText(item.getLunchTime());
        holder.lunchMenu.setOnClickListener((view) -> buildAndLunchPdfIntent(holder.itemView.getContext(), item.getLunchMenu()));
        holder.dinnerTime.setText(item.getDinnerTime());
        holder.dinnerMenu.setOnClickListener((view) -> buildAndLunchPdfIntent(holder.itemView.getContext(), item.getDinnerMenu()));
    }

    private void buildAndLunchPdfIntent(@NonNull Context context, @NonNull String pdfUrl) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setDataAndType(Uri.parse(pdfUrl), INTENT_DATA_TYPE);
        intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        try {
            context.startActivity(intent);
        } catch (ActivityNotFoundException e) {
            Timber.d("RCL Timber is: %s", "ON");
            Timber.d(e);
        }
    }

    static class TimesViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.text_module_lunch_time) TextView lunchTime;
        @Bind(R.id.discover_detail_lunch_menu) View lunchMenu;
        @Bind(R.id.text_module_dinner_time) TextView dinnerTime;
        @Bind(R.id.discover_detail_dinner_menu) View dinnerMenu;

        TimesViewHolder(ViewGroup parent) {
            super(LayoutInflater.from(parent.getContext()).inflate(R.layout.module_item_detail_dinner_times, parent, false));
            ButterKnife.bind(this, itemView);
        }
    }
}
