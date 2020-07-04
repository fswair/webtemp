package com.webdemo.router;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Application;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;


import java.util.ArrayList;

public class Route extends Application {

    @SuppressLint("StaticFieldLeak")
    private static Context context;
    private final Activity activity;

    public Route(Activity activity) {
        this.activity = activity;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Route.context = getApplicationContext();
    }

    public static Context getAppContext() {
        return Route.context;
    }

    public void linkRoute(String url, String sheme) {
        if (Uri.parse(url).getScheme().equals(sheme)) {
            try {
                // Google Play will start
            } catch (ActivityNotFoundException e) {
                // Google Play app is not installed, you may want to open the app store link

            }

        }
    }
}
