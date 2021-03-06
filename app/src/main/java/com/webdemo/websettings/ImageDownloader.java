package com.webdemo.websettings;

import android.annotation.SuppressLint;
import android.app.Application;
import android.app.DownloadManager;
import android.content.Context;
import android.net.Uri;
import android.view.ContextMenu;
import android.view.View;
import android.webkit.URLUtil;
import android.webkit.WebView;

import androidx.appcompat.app.AppCompatActivity;

import com.webdemo.R;

public class ImageDownloader extends Application {
    @SuppressLint("StaticFieldLeak")
    private static Context context;
    private final AppCompatActivity activity;

    public ImageDownloader(AppCompatActivity activity) {
        this.activity = activity;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        ImageDownloader.context = getApplicationContext();
    }

    public static Context getAppContext() {
        return ImageDownloader.context;
    }


    public static void down(WebView webView, ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo, Context context) {
        final WebView.HitTestResult webViewHitTestResult = webView.getHitTestResult();

        if (webViewHitTestResult.getType() == WebView.HitTestResult.IMAGE_TYPE ||
                webViewHitTestResult.getType() == WebView.HitTestResult.SRC_IMAGE_ANCHOR_TYPE) {

            contextMenu.setHeaderTitle(R.string.downimg);

            contextMenu.add(0, 1, 0, R.string.saveimg)
                    .setOnMenuItemClickListener(menuItem -> {

                        String DownloadImageURL = webViewHitTestResult.getExtra();

                        if (URLUtil.isValidUrl(DownloadImageURL)) {

                            DownloadManager.Request request = new DownloadManager.Request(Uri.parse(DownloadImageURL));
                            request.allowScanningByMediaScanner();


                            request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
                            DownloadManager downloadManager = (DownloadManager) context.getSystemService(DOWNLOAD_SERVICE);
                            downloadManager.enqueue(request);


                        }
                        return false;
                    });
        }

    }


}
