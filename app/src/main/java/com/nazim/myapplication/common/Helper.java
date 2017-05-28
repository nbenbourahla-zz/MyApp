package com.nazim.myapplication.common;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class Helper {
    /**
     * Check if the network connection is available or not
     *
     * @param context a non null context
     * @return true if the connection is available, false otherwise
     */
    public static boolean isNetworkAvailable(final Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }
}
