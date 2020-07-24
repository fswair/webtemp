package com.webdemo.services;

import android.annotation.SuppressLint;
import android.app.Application;
import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Environment;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.DownloadListener;
import android.webkit.URLUtil;
import android.webkit.WebView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;
import com.webdemo.R;
import com.webdemo.activity.MainActivity;


@SuppressWarnings("ALL")
public class downloadContent extends Application {

    @SuppressLint("StaticFieldLeak")
    private static Context context;
    private final AppCompatActivity activity;

    public downloadContent(AppCompatActivity activity) {
        this.activity = activity;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        MainActivity down = new MainActivity();

        downloadContent.context = getApplicationContext();
    }

    public static Context getAppContext() {
        return downloadContent.context;
    }

    public static void downl(final WebView webview1, final Boolean down) {

        webview1.setDownloadListener(new DownloadListener() {
            public void onDownloadStart(String url, String userAgent, String contentDisposition, String mimetype, long contentLength) {
                try {
                    if (down) {

                        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(url));
                        String cookies = CookieManager.getInstance().getCookie(url);
                        request.addRequestHeader("cookie", cookies);
                        request.addRequestHeader("User-Agent", userAgent);
                        Snackbar.make(webview1, R.string.indstarted, Snackbar.LENGTH_SHORT).setAction("", new View.OnClickListener() {
                            @Override
                            public void onClick(View _view) {

                            }
                        }).show();
                        request.setTitle(URLUtil.guessFileName(url, contentDisposition, mimetype));
                        request.allowScanningByMediaScanner();
                        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
                        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, URLUtil.guessFileName(url, contentDisposition, mimetype));

                        DownloadManager manager = (DownloadManager) context.getSystemService(Context.DOWNLOAD_SERVICE);
                        manager.enqueue(request);
                        com.google.android.material.snackbar.Snackbar.make(webview1, R.string.indstarted, com.google.android.material.snackbar.Snackbar.LENGTH_SHORT).setAction("", new View.OnClickListener() {
                            @Override
                            public void onClick(View _view) {

                            }
                        }).show();
                        BroadcastReceiver onComplete = new BroadcastReceiver() {
                            public void onReceive(Context ctxt, Intent intent) {
                                Snackbar.make(webview1, R.string.indfinish, Snackbar.LENGTH_SHORT).setAction("", new View.OnClickListener() {
                                    @Override
                                    public void onClick(View _view) {

                                    }
                                }).show();
                                context.unregisterReceiver(this);
                            }
                        };
                        context.registerReceiver(onComplete, new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE));
                    } else {

                    }
                } catch (Exception e) {
                    //not empty
                }
            }

        });
    }

}
