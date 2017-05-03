package com.rcl.excalibur.utils;


import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;

import com.rcl.excalibur.activity.BaseActivity;

public class AlertDialogUtils {

    private AlertDialogUtils() {
    }

    public static void showDialog(BaseActivity activity, DialogInterface.OnClickListener listener,
                              String message, String buttonMessage) {
        AlertDialog alertDialog = new AlertDialog.Builder(activity).create();
        alertDialog.setMessage(message);
        alertDialog.setCancelable(false);
        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, buttonMessage, listener);
        alertDialog.show();
    }
}
