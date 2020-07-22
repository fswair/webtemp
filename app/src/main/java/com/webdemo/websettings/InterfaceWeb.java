package com.webdemo.websettings;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;

import androidx.appcompat.app.AppCompatActivity;

public class InterfaceWeb extends Application {
    @SuppressLint("StaticFieldLeak")
    private static Context context;
    private final AppCompatActivity activity;

    public InterfaceWeb(AppCompatActivity activity) {
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
