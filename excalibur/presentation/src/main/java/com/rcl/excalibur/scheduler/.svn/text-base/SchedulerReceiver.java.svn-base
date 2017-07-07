package com.rcl.excalibur.scheduler;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.rcl.excalibur.data.BuildConfig;
import com.rcl.excalibur.data.repository.ShipTimeDataRepository;
import com.rcl.excalibur.data.service.DownloadProductsService;
import com.rcl.excalibur.data.service.ShipTimeServicesImpl;
import com.rcl.excalibur.domain.interactor.GetShipTimeUseCase;
import com.rcl.excalibur.scheduler.task.GetShipTimeTask;
import com.rcl.excalibur.scheduler.task.UpdateTimeTask;

import java.util.Date;

import timber.log.Timber;

import static com.rcl.excalibur.data.service.DiscoverServicesImpl.SAILING_ID;


public class SchedulerReceiver extends BroadcastReceiver {
    public static final int REQUEST_CODE = 1234;
    public static final String EXTRA_TASK = "EXTRA_TASK";

    public static final int TASK_UNDEFINED = 0;
    public static final int TASK_GET_SHIP_TIME = 1;
    public static final int TASK_UPDATE_TIME = 2;
    public static final int TASK_DOWNLOAD_PRODUCTS = 3;


    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent == null || !intent.hasExtra(EXTRA_TASK)) {
            return;
        }
        switch (intent.getIntExtra(EXTRA_TASK, TASK_UNDEFINED)) {
            case TASK_GET_SHIP_TIME:
                if (BuildConfig.DEBUG) {
                    Timber.d("SchedulerReceiver", "onReceive=GetShipTimeTask: " + new Date().toString());
                }
                new GetShipTimeTask(new GetShipTimeUseCase(new ShipTimeServicesImpl(new ShipTimeDataRepository()))).execute();
                return;
            case TASK_UPDATE_TIME:
                if (BuildConfig.DEBUG) {
                    Timber.d("SchedulerReceiver", "onReceive=UpdateTimeTask: " + new Date().toString());
                }
                new UpdateTimeTask().execute();
                return;
            case TASK_DOWNLOAD_PRODUCTS:
                if (BuildConfig.DEBUG) {
                    Timber.d("SchedulerReceiver", "onReceive=DownloadProductsService: " + new Date().toString());
                }
                DownloadProductsService.start(context, SAILING_ID);
                return;
            default:
                return;
        }
    }
}
