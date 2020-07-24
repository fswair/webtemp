package com.webdemo.call;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import es.dmoral.toasty.Toasty;

public class NoFrost extends Application {

    @SuppressLint("StaticFieldLeak")
    private static Context context;
    private final AppCompatActivity activity;

    public NoFrost(AppCompatActivity activity) {
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

    public static void giveLinksToHim(ArrayList<String> strr) {

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

    public static void showMessage(Context getAppContext, String _s) {
        Toasty.info(getAppContext, _s, Toast.LENGTH_SHORT, true).show();
        Toast.makeText(getAppContext, _s, Toast.LENGTH_SHORT).show();
    }


    public static void showError(Context getAppContext, String _s) {
        Toasty.error(getAppContext, _s, Toast.LENGTH_SHORT, true).show();
        Toast.makeText(getAppContext, _s, Toast.LENGTH_SHORT).show();
    }

    public static void showSuccess(Context getAppContext, String _s) {
        Toasty.success(getAppContext, _s, Toasty.LENGTH_SHORT, true).show();
        Toast.makeText(getAppContext, _s, Toast.LENGTH_SHORT).show();
    }
}
