package com.psi.monitor.utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

import com.psi.monitor.R;

/**
 * Created by hamdhanywijaya@gmail.com on 8/10/17.
 */

public class Utils {
    public static void infoFix(Context context, String title, String message, DialogInterface.OnClickListener listener) {
        if (context != null && context instanceof Activity && !((Activity) context).isFinishing()) {
            new AlertDialog.Builder(context)
                    .setTitle(title)
                    .setMessage(message)
                    .setIcon(R.mipmap.ic_launcher)
                    .setPositiveButton(context.getString(R.string.text_ok), listener)
                    .setNegativeButton(null, null)
                    .setCancelable(false)
                    .create()
                    .show();
        }
    }
}
