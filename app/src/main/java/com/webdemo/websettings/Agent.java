package com.webdemo.websettings;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.webkit.WebView;


public class Agent extends Application {
    @SuppressLint("StaticFieldLeak")
    private static Context context;
    private final Activity activity;

    public Agent(Activity activity) {
        this.activity = activity;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Agent.context = getApplicationContext();
    }

    public static Context getAppContext() {
        return Agent.context;
    }

    public void webAgent(WebView webView) {
        String userAgent = System.getProperty("http.agent");
        webView.getSettings().setUserAgentString(userAgent);
    }

}
