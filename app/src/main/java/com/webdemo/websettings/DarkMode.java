package com.webdemo.websettings;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.webkit.WebView;

import androidx.webkit.WebSettingsCompat;
import androidx.webkit.WebViewFeature;

import com.webdemo.R;
import com.webdemo.activity.MainActivity;
import com.webdemo.call.NoFrost;

public class DarkMode extends Application {

    @SuppressLint("StaticFieldLeak")
    private static Context context;
    private final Activity activity;

    public DarkMode(Activity activity) {
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
