package com.rcl.excalibur.scheduler.task;


import android.os.AsyncTask;

import com.rcl.excalibur.domain.interactor.GetShipTimeUseCase;

import static com.rcl.excalibur.BuildConfig.SHIP_TIME;
import static java.util.concurrent.TimeUnit.MINUTES;

public class GetShipTimeTask extends AsyncTask<Void, Void, Void> {

    private GetShipTimeUseCase getShipTimeUseCase;

    public GetShipTimeTask(GetShipTimeUseCase getShipTimeUseCase) {
        this.getShipTimeUseCase = getShipTimeUseCase;
    }

    public static long getTime() {
        return MINUTES.toMillis(SHIP_TIME);
    }

    @Override
    protected Void doInBackground(Void... voids) {
        getShipTimeUseCase.execute(null);

        return null;
    }
}
