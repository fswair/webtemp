package com.webdemo.websettings;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.webkit.WebView;

public class InterfaceWeb extends Application {
    @SuppressLint("StaticFieldLeak")
    private static Context context;
    private final Activity activity;

    public InterfaceWeb(Activity activity) {
        this.activity = activity;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        InterfaceWeb.context = getApplicationContext();
    }

    public static Context getAppContext() {
        return InterfaceWeb.context;
    }


}
