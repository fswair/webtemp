package com.webdemo.router;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.net.Uri;

import androidx.appcompat.app.AppCompatActivity;

public class Route extends Application {

    @SuppressLint("StaticFieldLeak")
    private static Context context;
    private final AppCompatActivity activity;

    public Route(AppCompatActivity activity) {
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

    public static void linkRoute(String url, String sheme) {
        if (Uri.parse(url).getScheme().equals(sheme)) {
            try {
                // Google Play will start
            } catch (ActivityNotFoundException e) {
                // Google Play app is not installed, you may want to open the app store link

            }

        }
    }
}
