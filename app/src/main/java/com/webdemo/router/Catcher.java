package com.webdemo.router;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;


public class Catcher extends Application {
    @SuppressLint("StaticFieldLeak")
    private static Context context;
    private final Activity activity;

    public Catcher(Activity activity) {
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

    public void catchLink(String url) {
        if (isLinkCatched(url)) {
            catchedOut();
        }
    }


    public boolean isLinkCatched(String url) {

        return true;
    }


    public void catchedOut() {

    }
}
