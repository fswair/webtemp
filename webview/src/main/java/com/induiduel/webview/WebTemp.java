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

    public static void giveLinksToHim() {

        strr.add("twitter".concat(".com").trim().toLowerCase());
        strr.add("facebook".concat(".com").trim().toLowerCase());
        strr.add("youtube".concat(".com").trim().toLowerCase());
        strr.add("instagram".concat(".com").trim().toLowerCase());
        strr.add("gmail".concat(".com").trim().toLowerCase());
        strr.add("whatsapp".concat(".com").trim().toLowerCase());
        strr.add("snapchat".concat(".com").trim().toLowerCase());
        strr.add("skype".concat(".com").trim().toLowerCase());
        strr.add("telegram".concat(".com").trim().toLowerCase());
        strr.add("play.google".concat(".com").trim().toLowerCase());
    }

    public static void showMessage(String _s) {

        Toasty.info(getAppContext(), _s, Toast.LENGTH_SHORT, true).show();
    }


    public static void showError(String _s) {
        Toasty.error(getAppContext(), _s, Toast.LENGTH_SHORT, true).show();
    }

    public static void showSuccess(String _s) {
        Toasty.success(getAppContext(), _s, Toasty.LENGTH_SHORT, true).show();
    }
}
