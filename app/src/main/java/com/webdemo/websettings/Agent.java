package com.webdemo.websettings;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;
import android.webkit.WebView;

import androidx.appcompat.app.AppCompatActivity;


public class Agent extends Application {
    @SuppressLint("StaticFieldLeak")
    private static Context context;
    private final AppCompatActivity activity;

    public Agent(AppCompatActivity activity) {
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

    public static void webAgent(WebView webView) {
        String userAgent = System.getProperty("http.agent");
        webView.getSettings().setUserAgentString(userAgent);
    }

}
