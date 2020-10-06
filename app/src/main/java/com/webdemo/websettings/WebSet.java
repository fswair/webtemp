package com.webdemo.websettings;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;
import android.os.Build;
import android.webkit.WebSettings;
import android.webkit.WebView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

public class WebSet extends Application {
    @SuppressLint("StaticFieldLeak")
    private static Context context;
    private final AppCompatActivity activity;

    public WebSet(AppCompatActivity activity) {
        this.activity = activity;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        WebSet.context = getApplicationContext();
    }

    public static Context getAppContext() {
        return WebSet.context;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static void additionalSettings(WebView webView, Boolean bool) {
        webView.getSettings().setSupportMultipleWindows(bool);
        //noinspection deprecation
        webView.getSettings().setPluginState(WebSettings.PluginState.ON);
        webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(bool);
        webView.getSettings().setDefaultTextEncodingName("utf-8");
        webView.getSettings().setLoadWithOverviewMode(bool);
        webView.getSettings().setLoadsImagesAutomatically(bool);
        webView.getSettings().setSafeBrowsingEnabled(bool);
    }

}
