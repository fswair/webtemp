package com.webdemo.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.android.gms.ads.AdRequest;
import com.webdemo.R;

import java.util.ArrayList;

import static com.webdemo.services.downloadContent.downl;

public abstract class MainManager extends AppCompatActivity {
    protected ArrayList<String> strr = new ArrayList<>();

    @SuppressLint("SetJavaScriptEnabled")
    public static void _webSettings(WebView webview1) {

        MainActivity.setDarkModeOnWeb(MainActivity.darkM);

        webview1.getSettings().setJavaScriptEnabled(MainActivity.js);

        //webview1.getSettings().setJavaScriptCanOpenWindowsAutomatically(js);

        CookieManager.getInstance().setAcceptCookie(MainActivity.cook);

        webview1.getSettings().setAllowContentAccess(MainActivity.aca);

        webview1.getSettings().setAllowFileAccess(MainActivity.afa);

        webview1.getSettings().setAppCacheEnabled(MainActivity.cache);

        webview1.getSettings().setLoadWithOverviewMode(true);

        webview1.getSettings().setUseWideViewPort(true);

        webview1.getSettings().setDomStorageEnabled(true);

        webview1.getSettings().setSaveFormData(MainActivity.form);

        webview1.getSettings().setSupportZoom(MainActivity.zoom);

        webview1.getSettings().setBuiltInZoomControls(MainActivity.zoom);

        webview1.getSettings().setDisplayZoomControls(MainActivity.zoom);

    }

    protected void sharedPreferencesWebSettings() {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getBaseContext());

        MainActivity.js = prefs.getBoolean("jsdata", true);
        MainActivity.down = prefs.getBoolean("downloaddata", false);
        MainActivity.up = prefs.getBoolean("uploaddata", false);
        MainActivity.form = prefs.getBoolean("formdata", true);
        MainActivity.cook = prefs.getBoolean("cook", true);
        MainActivity.aca = prefs.getBoolean("aca", true);
        MainActivity.afa = prefs.getBoolean("afa", true);
        MainActivity.cache = prefs.getBoolean("cache", true);
        MainActivity.zoom = prefs.getBoolean("zoom", false);
        MainActivity.darkM = prefs.getBoolean("darkM", false);

    }

    @SuppressLint("SetJavaScriptEnabled")
    protected void _swipeToRefreshWeb(final WebView _webview, final View _view) {


        final SwipeRefreshLayout sr = new SwipeRefreshLayout(this);
        sr.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
        ((LinearLayout) _view).addView(sr);
        ((LinearLayout) _view).removeView(_webview);
        (_view).post(() -> sr.addView(_webview));

        _webview.canScrollVertically(View.FOCUS_DOWN);

        _webview.setWebViewClient(new WebViewClient() {
            public void onPageFinished(WebView view, String url) {
                sr.setRefreshing(false);
            }
        });
        sr.setOnRefreshListener(() -> {
            sr.setRefreshing(false);
            _webview.reload();
        });


    }

    protected void _soLinkMyPage(final String _urlSt) {
        double iii;
        for (iii = 0; iii < strr.size(); iii++) {

            if (_urlSt.contains(strr.get((int) (iii)))) {
                MainActivity.webview1.stopLoading();
                Intent intentt = new Intent();
                intentt.setAction(Intent.ACTION_VIEW);
                intentt.setData(Uri.parse(_urlSt));
                startActivity(intentt);
            }
        }
    }

    protected void ohDearDownloadMe() {
        sharedPreferencesWebSettings();
        downl(MainActivity.webview1, MainActivity.down);
    }

    //Video Reklam yükle
    protected void loadRewardedVideoAd() {
        //yüklenirse gösterilir..
        if (!MainActivity.mRewardedVideoAd.isLoaded()) {
            MainActivity.mRewardedVideoAd.loadAd(MainActivity.rewardedAdId, new AdRequest.Builder().build());
        } else {
            MainActivity.webview1.loadUrl(getString(R.string.url4));

        }
    }
}
