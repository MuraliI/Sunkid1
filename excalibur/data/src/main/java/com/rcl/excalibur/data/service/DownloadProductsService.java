package com.rcl.excalibur.data.service;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;

import com.rcl.excalibur.data.repository.ProductDataRepository;
import com.rcl.excalibur.data.utils.DownloadProductsManager;

import static com.rcl.excalibur.data.BuildConfig.DOWNLOAD_PRODUCTS_TIME;
import static java.util.concurrent.TimeUnit.HOURS;


public class DownloadProductsService extends IntentService {
    private static final String ACTION_DOWNLOAD_PRODUCTS = "ACTION_DOWNLOAD_PRODUCTS";
    private static final String EXTRA_SAILING_ID = "SAILING_ID";
    private static final String TAG = "DownloadProductsService";

    public DownloadProductsService() {
        super(TAG);
    }

    public static long getTime() {
        return HOURS.toMillis(DOWNLOAD_PRODUCTS_TIME);
    }

    public static void start(Context context, @NonNull String sailingId) {
        Intent intent = new Intent(context, DownloadProductsService.class);
        intent.setAction(ACTION_DOWNLOAD_PRODUCTS);
        intent.putExtra(EXTRA_SAILING_ID, sailingId);
        context.startService(intent);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent == null || !ACTION_DOWNLOAD_PRODUCTS.equals(intent.getAction())) {
            return;
        }
        final String sailingId = intent.getStringExtra(EXTRA_SAILING_ID);
        handleDownloadProducts(sailingId);
    }

    private void handleDownloadProducts(String sailingId) {
        new DownloadProductsManager(sailingId, new DiscoverServicesImpl(), new ProductDataRepository())
                .process();
    }
}
