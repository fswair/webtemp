package com.induiduel.webview;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import es.dmoral.toasty.Toasty;

public class WebTemp extends Application {


    @SuppressLint("StaticFieldLeak")
    private static Context context;
    private static ArrayList<String> strr = new ArrayList<>();
    private final Activity activity;

    public WebTemp(Activity activity) {
        this.activity = activity;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        WebTemp.context = getApplicationContext();
    }

    public static Context getAppContext() {
        return WebTemp.context;
    }




}
