package com.rcl.excalibur.scheduler;


import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.rcl.excalibur.data.service.DownloadProductsService;
import com.rcl.excalibur.mvp.presenter.DefaultPresentObserver;
import com.rcl.excalibur.scheduler.task.GetShipTimeTask;
import com.rcl.excalibur.scheduler.task.UpdateTimeTask;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.rcl.excalibur.scheduler.SchedulerReceiver.EXTRA_TASK;
import static com.rcl.excalibur.scheduler.SchedulerReceiver.TASK_DOWNLOAD_PRODUCTS;
import static com.rcl.excalibur.scheduler.SchedulerReceiver.TASK_GET_SHIP_TIME;
import static com.rcl.excalibur.scheduler.SchedulerReceiver.TASK_UPDATE_TIME;

public final class SchedulerManager {
    private static SchedulerManager instance;
    private WeakReference<Context> contextRef;
    private List<PendingIntent> pendingIntents;
    private DefaultPresentObserver<Long, ?> updateTimeObserver;

    private SchedulerManager(final Context context) {
        contextRef = new WeakReference<>(context);
        pendingIntents = new ArrayList<>();
    }

    public static SchedulerManager get() {
        return instance;
    }

    public static void init(final Context context) {
        if (instance == null) {
            instance = new SchedulerManager(context);
        }
        instance.startJobs();

    }

    public boolean hasUpdateTimeObserver() {
        return updateTimeObserver != null;
    }

    public DefaultPresentObserver<Long, ?> getUpdateTimeObserver() {
        return updateTimeObserver;
    }

    public void addUpdateTimeObserver(DefaultPresentObserver updateTimeObserver) {
        this.updateTimeObserver = updateTimeObserver;
    }

    public void removeUpdateTimeObserver() {
        updateTimeObserver = null;
    }

    private void startJobs() {
        final Context context = contextRef.get();
        if (context == null) {
            return;
        }
        Log.d("SchedulerReceiver", "init: " + new Date().toString());
        final AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        final long currentTimeMillis = System.currentTimeMillis();

//        GET ShipTime
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP
                , currentTimeMillis + GetShipTimeTask.getTime()
                , GetShipTimeTask.getTime()
                , createPendingIntent(context, TASK_GET_SHIP_TIME));

//        Update Time
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP
                , currentTimeMillis + UpdateTimeTask.getTime()
                , UpdateTimeTask.getTime()
                , createPendingIntent(context, TASK_UPDATE_TIME));

//     Download Products
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP
                , currentTimeMillis
                , DownloadProductsService.getTime()
                , createPendingIntent(context, TASK_DOWNLOAD_PRODUCTS));

    }

    private PendingIntent createPendingIntent(final Context context, int task) {
        final Intent intent = new Intent(context, SchedulerReceiver.class);
        intent.putExtra(EXTRA_TASK, task);
        final PendingIntent pendingIntent = PendingIntent.getBroadcast(context, (int) System.currentTimeMillis(), intent, 0);
        pendingIntents.add(pendingIntent);
        return pendingIntent;

    }


    public void stop() {
        Log.d("SchedulerReceiver", "stop: " + new Date().toString());

        final Context context = contextRef.get();
        if (context == null) {
            return;
        }
        final AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        for (PendingIntent pendingIntent : pendingIntents) {
            alarmManager.cancel(pendingIntent);
        }
    }
}
