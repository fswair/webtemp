package com.webdemo.router;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;

import androidx.appcompat.app.AppCompatActivity;


public class Catcher extends Application {
    @SuppressLint("StaticFieldLeak")
    private static Context context;
    private final AppCompatActivity activity;

    public Catcher(AppCompatActivity activity) {
        this.activity = activity;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Catcher.context = getApplicationContext();
    }

    public static Context getAppContext() {
        return Catcher.context;
    }

    public static void catchLink(String url) {
        if (isLinkCatched(url)) {
            catchedOut();
        }
    }


    public static boolean isLinkCatched(String url) {

        return true;
    }


    public static void catchedOut() {

    }
}
