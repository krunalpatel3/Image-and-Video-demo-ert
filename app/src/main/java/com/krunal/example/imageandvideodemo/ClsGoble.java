package com.krunal.example.imageandvideodemo;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.util.Log;

import androidx.core.app.ActivityCompat;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

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

      public static String convertLongToDDMMYYYY(long datetimg) {
        try {

            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
               return DateTimeFormatter.ofPattern("dd/MM/yyyy").format(
                        Instant.ofEpochMilli(datetimg*1000)
                                .atZone(ZoneId.systemDefault())
                                .toLocalDate());
            } else {
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                return dateFormat.format(datetimg);

            }
//            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        }catch (Exception e){
            Log.e("Check","Exception: " + e.getMessage());
        }

        return "";

      }


}
