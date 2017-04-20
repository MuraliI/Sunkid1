package com.rcl.excalibur.scheduler.task;


import android.os.AsyncTask;

import com.rcl.excalibur.scheduler.SchedulerManager;

import static com.rcl.excalibur.BuildConfig.UPDATE_TIME;
import static java.util.concurrent.TimeUnit.MINUTES;

public class UpdateTimeTask extends AsyncTask<Void, Void, Void> {


    public static long getTime() {
        return MINUTES.toMillis(UPDATE_TIME);
    }

    @Override
    protected Void doInBackground(Void... voids) {
        if (!SchedulerManager.get().hasUpdateTimeObserver()) {
            return null;
        }
        SchedulerManager.get().getUpdateTimeObserver().onNext(System.currentTimeMillis());
        return null;
    }
}
