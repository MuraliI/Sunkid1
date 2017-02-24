package com.rcl.excalibur.data.executor;

import android.support.annotation.NonNull;

import com.rcl.excalibur.data.BuildConfig;
import com.rcl.excalibur.domain.executor.ThreadExecutor;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Decorated {@link java.util.concurrent.ThreadPoolExecutor}.
 */
@Singleton
public class JobExecutor implements ThreadExecutor {
    private static final String PREFIX = "android_";
    private final ThreadPoolExecutor threadPoolExecutor;

    @Inject
    JobExecutor() {
        this.threadPoolExecutor = new ThreadPoolExecutor(BuildConfig.CORE_POOL_SIZE, BuildConfig.MAXIMUM_POOL_SIZE,
                BuildConfig.KEEP_ALIVE_TIME, TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(), new JobThreadFactory());
    }

    @Override
    public void execute(@NonNull Runnable runnable) {
        this.threadPoolExecutor.execute(runnable);
    }

    private static class JobThreadFactory implements ThreadFactory {
        private int counter = 0;

        @Override
        public Thread newThread(@NonNull Runnable runnable) {
            return new Thread(runnable, PREFIX + counter++);
        }
    }
}
