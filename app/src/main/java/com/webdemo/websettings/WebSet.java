package com.webdemo.websettings;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Build;
import android.webkit.WebSettings;
import android.webkit.WebView;

import androidx.annotation.RequiresApi;

public class WebSet extends Application {
    @SuppressLint("StaticFieldLeak")
    private static Context context;
    private final Activity activity;

    public WebSet(Activity activity) {
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
        webView.getSettings().setPluginState(WebSettings.PluginState.ON);
        webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(bool);
        webView.getSettings().setDefaultTextEncodingName("utf-8");
        webView.getSettings().setLoadWithOverviewMode(bool);
        //webView.getSettings().setTextSize(WebSettings.TextSize.SMALLER);
        webView.getSettings().setLoadsImagesAutomatically(bool);
        webView.getSettings().setSafeBrowsingEnabled(bool);
    }

}
