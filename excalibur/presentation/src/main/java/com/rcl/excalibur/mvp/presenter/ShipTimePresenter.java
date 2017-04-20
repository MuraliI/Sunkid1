package com.rcl.excalibur.mvp.presenter;


import com.rcl.excalibur.data.utils.DateUtil;
import com.rcl.excalibur.domain.ShipTime;
import com.rcl.excalibur.domain.interactor.GetShipTimeDbUseCase;
import com.rcl.excalibur.domain.interactor.GetShipTimeUseCase;
import com.rcl.excalibur.mvp.view.ShipTimeView;
import com.rcl.excalibur.scheduler.SchedulerManager;

import java.util.Calendar;
import java.util.TimeZone;

public class ShipTimePresenter {
    private static final String TIME_ZONE = "GMT%s";
    protected ShipTimeView view;
    protected GetShipTimeUseCase getShipTimeUseCase;
    protected GetShipTimeDbUseCase getShipTimeDbUseCase;


    public ShipTimePresenter(ShipTimeView view, GetShipTimeUseCase getShipTimeUseCase, GetShipTimeDbUseCase getShipTimeDbUseCase) {
        this.view = view;
        this.getShipTimeUseCase = getShipTimeUseCase;
        this.getShipTimeDbUseCase = getShipTimeDbUseCase;
        init();
    }

    private void init() {
        getShipTimeUseCase.execute(null);
    }

    private void display(final long value) {
        final ShipTime shipTime = getShipTimeDbUseCase.get();
        TimeZone timeZone = shipTime != null
                ? TimeZone.getTimeZone(String.format(TIME_ZONE, shipTime.getOffset()))
                : TimeZone.getDefault();

        Calendar calendar = Calendar.getInstance(timeZone);
        calendar.setTimeInMillis(value);

        view.display(DateUtil.parseTime(calendar.getTime(), timeZone));
    }

    public void register() {
        SchedulerManager.get().addUpdateTimeObserver(new SchedulerObserver(this));
        display(System.currentTimeMillis());
    }

    public void unregister() {
        SchedulerManager.get().removeUpdateTimeObserver();
    }


    public static class SchedulerObserver extends DefaultPresentObserver<Long, ShipTimePresenter> {

        public SchedulerObserver(ShipTimePresenter presenter) {
            super(presenter);
        }

        @Override
        public void onNext(Long value) {
            if (value == null) {
                return;
            }
            getPresenter().display(value);

        }
    }
}
