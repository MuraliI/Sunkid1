package com.rcl.excalibur.utils;


import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;

import com.rcl.excalibur.activity.BaseActivity;

public class AlertDialogUtils {

    private AlertDialogUtils() {
    }

    public static void showDialog(BaseActivity activity, DialogInterface.OnClickListener listener,
                                  String message, String buttonMessage) {

        AlertDialog.Builder builder = new AlertDialog.Builder(activity)
                .setMessage(message)
                .setCancelable(true)
                .setPositiveButton(buttonMessage, listener);

        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
