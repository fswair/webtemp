package com.webdemo.call;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Application;
import android.content.Context;

import java.util.ArrayList;

public class NoFrost extends Application {

    @SuppressLint("StaticFieldLeak")
    private static Context context;
    private final Activity activity;

    private static ArrayList<String> strr = new ArrayList<>();

    public NoFrost(Activity activity) {
        this.activity = activity;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        NoFrost.context = getApplicationContext();
    }

    public static Context getAppContext() {
        return NoFrost.context;
    }


}
