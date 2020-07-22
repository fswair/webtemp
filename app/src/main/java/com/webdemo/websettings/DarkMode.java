package com.webdemo.websettings;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;
import android.webkit.WebView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.webkit.WebSettingsCompat;
import androidx.webkit.WebViewFeature;

public class DarkMode extends Application {

    @SuppressLint("StaticFieldLeak")
    private static Context context;
    private final AppCompatActivity activity;

    public DarkMode(AppCompatActivity activity) {
        this.activity = activity;

    }

    @Override
    public void onCreate() {
        super.onCreate();
        DarkMode.context = getApplicationContext();
    }

    public static Context getAppContext() {
        return DarkMode.context;
    }

    public static void featureDark(WebView myWeb) {
        if (WebViewFeature.isFeatureSupported(WebViewFeature.FORCE_DARK)) {
            WebSettingsCompat.setForceDark(myWeb.getSettings(), WebSettingsCompat.FORCE_DARK_ON);
        }
    }

    public static void featureLight(WebView myWeb) {
        if (WebViewFeature.isFeatureSupported(WebViewFeature.FORCE_DARK)) {
            WebSettingsCompat.setForceDark(myWeb.getSettings(), WebSettingsCompat.FORCE_DARK_OFF);
        }
    }

}
