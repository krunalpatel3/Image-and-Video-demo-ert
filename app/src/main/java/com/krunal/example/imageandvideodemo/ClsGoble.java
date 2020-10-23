package com.krunal.example.imageandvideodemo;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;

import androidx.core.app.ActivityCompat;

import static androidx.core.app.ActivityCompat.requestPermissions;

public class ClsGoble {

    public static final int REQUEST_PERMISSION_ACTION = 5400;

    public static final String[] STORAGE_PERMISSIONS = {
            android.Manifest.permission.WRITE_EXTERNAL_STORAGE,
            android.Manifest.permission.READ_EXTERNAL_STORAGE,
    };

    /**
     * Check for the permission from backgorund.
     *
     * @param context
     * @param permissions
     * @return
     */
    public static boolean hasPermissions(Context context, String... permissions) {
        if (context != null && permissions != null) {
            for (String permission : permissions) {
                if (ActivityCompat.checkSelfPermission(context, permission)
                        != PackageManager.PERMISSION_GRANTED) {
                    return false;
                }
            }
        }
        return true;
    }


    public static void askPermission(Context context,int requestCode, String... permissions) {

        if (!hasPermissions(context, permissions)) {
            requestPermissions((Activity) context, permissions
                    , requestCode);
        }

    }
}
