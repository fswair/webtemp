package com.webdemo.activity;


import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.DownloadManager;
import android.content.ActivityNotFoundException;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Build.VERSION_CODES;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.CookieManager;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebChromeClient.FileChooserParams;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RatingBar;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.preference.PreferenceManager;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.viewpager.widget.ViewPager;

import com.airbnb.lottie.LottieAnimationView;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.reward.RewardItem;
import com.google.android.gms.ads.reward.RewardedVideoAd;
import com.google.android.gms.ads.reward.RewardedVideoAdListener;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.snackbar.Snackbar;
import com.webdemo.R;
import com.webdemo.call.NoFrost;
import com.webdemo.listeners.CustomItemClickListener;
import com.webdemo.recycler.Person;
import com.webdemo.recycler.SimpleRecyclerAdapter;
import com.webdemo.request.RequestNetwork;
import com.webdemo.websettings.DarkMode;
import com.webdemo.websettings.ImageDownloader;

import net.yslibrary.android.keyboardvisibilityevent.KeyboardVisibilityEvent;
import net.yslibrary.android.keyboardvisibilityevent.KeyboardVisibilityEventListener;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

import es.dmoral.toasty.Toasty;

import static android.content.Context.CONNECTIVITY_SERVICE;
import static android.view.View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
import static com.google.android.material.bottomnavigation.BottomNavigationView.OnClickListener;
import static com.google.android.material.bottomnavigation.BottomNavigationView.OnNavigationItemSelectedListener;
import static com.webdemo.services.downloadContent.downl;


/**
 * induiduel webview projesidir.
 */

public class MainActivity extends AppCompatActivity implements SharedPreferences.OnSharedPreferenceChangeListener, RewardedVideoAdListener {
    public static final int REQUEST_SELECT_FILE = 100;
    private final static int FILECHOOSER_RESULTCODE = 1;
    public ValueCallback<Uri[]> uploadMessage;
    protected FrameLayout mFullscreenContainer;
    LottieAnimationView errorbind;
    private DrawerLayout _drawer;
    private FileChooserParams fileChooserParams;
    private ValueCallback mUploadMessage;
    private ArrayList<String> strr = new ArrayList<>();
    private ArrayList<String> location = new ArrayList<>();
    private ArrayList<HashMap<String, Object>> getThis = new ArrayList<>();
    private LinearLayout linear1;
    private WebView webview1;
    private ProgressBar progressbar;
    private Intent intent = new Intent();
    private Intent i = new Intent();
    private View customview1;
    private int mOriginalSystemUiVisibility;

    private DrawerLayout drawer;
    private ViewPager viewPager;

    private int mOriginalOrientation;
    private WebChromeClient.CustomViewCallback mCustomViewCallBack;
    private WebChromeClient.CustomViewCallback mCustomViewCallback;
    private BottomNavigationView bottomNavigation;

    private LinearLayout _drawer_linear1;


    private RecyclerView recycler_view;
    private List<Person> person_list;
    private RatingBar ratingBar;


    private boolean js = true;
    private boolean down = true;
    private boolean up = true;
    private boolean form = true;
    private boolean cook = true;
    private boolean aca = true;
    private boolean afa = true;
    private boolean cache = true;
    private boolean zoom = true;
    private boolean darkM = false;

    private String getUr = "";
    private boolean acs = false;
    private int als = 0;

    private static final String AD_UNIT_ID = "ca-app-pub-3039242376817399/4392620783";
    private static final String APP_ID = "ca-app-pub-3039242376817399~8547681739";

    private RewardedVideoAd mRewardedVideoAd;
    private InterstitialAd mInterstitialAd;

    private OnNavigationItemSelectedListener navigationItemSelectedListener =
            new OnNavigationItemSelectedListener() {
                //BottomNavigationBar Hareketlerini dinler
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    switch (item.getItemId()) {
                        case R.id.navigation_home:

                            webview1.loadUrl(getString(R.string.url1));

                            return true;
                        case R.id.navigation_sms:

                            if (mInterstitialAd.isLoaded()) {
                                mInterstitialAd.show();
                                getUr = getString(R.string.url2);

                            } else {
                                webview1.loadUrl(getString(R.string.url2));
                            }

                            return true;
                        case R.id.navigation_third:

                            if (mInterstitialAd.isLoaded()) {
                                getUr = getString(R.string.url3);

                                mInterstitialAd.show();

                            } else {
                                webview1.loadUrl(getString(R.string.url3));
                            }

                            return true;

                        case R.id.navigation_four:
                            //istek bolumu
                            if (mRewardedVideoAd.isLoaded()) {
                                getUr = getString(R.string.url4);

                                mRewardedVideoAd.show();
                            } else {
                                webview1.loadUrl(getString(R.string.url4));
                            }
                            return true;

                        case R.id.navigation_five:

                            intent.setClass(getApplicationContext(), SettingsActivity.class);
                            startActivity(intent);
                            return false;
                    }
                    return false;
                }
            };



    @Override
    protected void onCreate(Bundle _savedInstanceState) {

        super.onCreate(_savedInstanceState);
        /*
        Intent in = getIntent();
        Uri data = in.getData();

        used for deeplinking
         */


        Intent in = getIntent();
        Uri data = in.getData();


        ssss();
        setDarkModeOn(darkM);
        setContentView(R.layout.main);
        bottomNavigation = findViewById(R.id.bottom_navigation);
        errorbind = findViewById(R.id.errorbind);
        errorbind.setVisibility(View.GONE);

        initialize(_savedInstanceState);
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED || ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1000);
        } else {
            if (Build.VERSION.SDK_INT >= VERSION_CODES.M) {
                initializeLogic();
            } else {
                NoFrost.showError(getString(R.string.lowapi));
            }
        }

        if (darkM) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                Window window = getWindow();
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                window.setStatusBarColor(Color.parseColor("#202124"));
                getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            }
            View decorView = getWindow().getDecorView();
            decorView.setSystemUiVisibility(decorView.getSystemUiVisibility() & ~View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        } else {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.parseColor("#ffffff"));
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }

        MobileAds.initialize(this, APP_ID);
        mRewardedVideoAd = MobileAds.getRewardedVideoAdInstance(this);
        mRewardedVideoAd.setRewardedVideoAdListener(this);
        loadRewardedVideoAd();

        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId("ca-app-pub-3039242376817399/4420540945");
        mInterstitialAd.loadAd(new AdRequest.Builder().build());

        setupSharedPreferences();



        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdFailedToLoad(int errorCode) {
                // Code to be executed when an ad request fails.
                NoFrost.showError("Hata: " + errorCode);
            }

            @Override
            public void onAdOpened() {


            }

            @Override
            public void onAdLoaded() {
                als++;
            }


            @Override
            public void onAdClosed() {
                // Code to be executed when the interstitial ad is closed.
                webview1.loadUrl(getUr);
                if (als <= 1) {
                    mInterstitialAd.loadAd(new AdRequest.Builder().build());
                }
            }
        });

    }


    @RequiresApi(api = VERSION_CODES.M)
    @Override
    public void onRequestPermissionsResult(int requestCode, @NotNull String[] permissions, @NotNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1000) {
            initializeLogic();
        }
    }

    @SuppressLint("SetJavaScriptEnabled")
    private void initialize(Bundle _savedInstanceState) {
        Toolbar _toolbar = findViewById(R.id._toolbar);
        setSupportActionBar(_toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        //Toolbar Geri Tuşu
        _toolbar.setNavigationOnClickListener(_v -> onBackPressed());

        linear1 = findViewById(R.id.linear1);
        webview1 = findViewById(R.id.webview1);


        SharedPreferences s = getSharedPreferences("s", Activity.MODE_PRIVATE);

        RequestNetwork rqey = new RequestNetwork(this);

        //drawer işlemleri
        _drawer = findViewById(R.id._drawer);

        ratingBar = findViewById(R.id.ratingbar);


        _drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
        ActionBarDrawerToggle _toggle = new ActionBarDrawerToggle(MainActivity.this, _drawer, _toolbar, R.string.app_name, R.string.app_name);
        _drawer.addDrawerListener(_toggle);
        _toggle.syncState();

        RequestNetwork.RequestListener _rqey_request_listener = new RequestNetwork.RequestListener() {
            @Override
            public void onResponse(String _param1, String _param2) {


            }

            @Override
            public void onErrorResponse(String _param1, String _param2) {

            }
        };

        //drawer recyclerview
        recycler_view = findViewById(R.id.recycler_view);


        LinearLayoutManager layoutManager = new LinearLayoutManager(this);

        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        layoutManager.scrollToPosition(0);

        recycler_view.setLayoutManager(layoutManager);
        //drawer menü ekleme
        person_list = new ArrayList<>();

        person_list.add(new Person("Anasayfa", R.drawable.ic_out_home));
        person_list.add(new Person("Ayarlar", R.drawable.ic_out_settings));
        person_list.add(new Person("Hakkında", R.drawable.ic_about));


        SimpleRecyclerAdapter adapter_items = new SimpleRecyclerAdapter(person_list, (v, position) -> {

            if (position == 0) {
                bottomNavigation.setSelectedItemId(R.id.navigation_home);
            }

            if (position == 1) {
                //link to settings
                intent.setClass(getApplicationContext(), SettingsActivity.class);
                startActivity(intent);

            }


        });
        recycler_view.setHasFixedSize(true);

        recycler_view.setAdapter(adapter_items);

        recycler_view.setItemAnimator(new DefaultItemAnimator());


    }


    @RequiresApi(api = VERSION_CODES.M)
    private void initializeLogic() {

        _onCreate1();
    }


    @Override
    public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {
        super.onCreateContextMenu(contextMenu, view, contextMenuInfo);

        ImageDownloader.down(webview1, contextMenu, view, contextMenuInfo, getApplicationContext());


    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);

        if (requestCode == REQUEST_SELECT_FILE) {
            if (uploadMessage == null) return;
            uploadMessage.onReceiveValue(WebChromeClient.FileChooserParams.parseResult(resultCode, intent));
            uploadMessage = null;
        }
    }

    @Override
    public void onBackPressed() {
        if (webview1.canGoBack()) {
            webview1.goBack();
        } else {
            finishAffinity();
        }
    }

    @Override
    public void onStart() {

        super.onStart();


    }

    @RequiresApi(api = VERSION_CODES.M)
    private void _ui() {
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(Color.parseColor(getString(R.string.beyaz)));
        getWindow().getDecorView().setSystemUiVisibility(SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);


    }

    @RequiresApi(api = VERSION_CODES.M)
    private void _onCreate1() {
        _ui();
        _swipeToRefreshWeb(webview1, linear1);
        NoFrost.giveLinksToHim(strr);
        onLogic();

        bottomNavigation.setOnNavigationItemSelectedListener(navigationItemSelectedListener);

    }

    private void onLogic() {

        ssss();
        KeyboardVisibilityEvent.setEventListener(
                MainActivity.this,
                isOpen -> {
                    // some code depending on keyboard visiblity status


                    if (isOpen) {

                        bottomNavigation.setVisibility(View.GONE);

                    } else {

                        bottomNavigation.setVisibility(View.VISIBLE);

                    }

                });


        webview1.setWebChromeClient(new WebChromeClient() {
            // For 3.0+ Devices


            protected void openFileChooser(ValueCallback uploadMsg, String acceptType) {
                mUploadMessage = uploadMsg;
                Intent i = new Intent(Intent.ACTION_GET_CONTENT);
                i.addCategory(Intent.CATEGORY_OPENABLE);
                i.setType("*/*");
                startActivityForResult(Intent.createChooser(i, getString(R.string.dosyagezgini)), FILECHOOSER_RESULTCODE);
            }

            /* For Lollipop 5.0+ Devices */
            public boolean onShowFileChooser(WebView mWebView, ValueCallback<Uri[]> filePathCallback, WebChromeClient.FileChooserParams fileChooserParams) {
                if (uploadMessage != null) {
                    uploadMessage.onReceiveValue(null);
                    uploadMessage = null;
                }
                uploadMessage = filePathCallback;
                Intent intent = fileChooserParams.createIntent();
                try {
                    startActivityForResult(intent, REQUEST_SELECT_FILE);
                } catch (ActivityNotFoundException e) {
                    uploadMessage = null;
                    NoFrost.showError(getString(R.string.dosyaerror));
                    return false;
                }
                return true;
            }

            //For Android 4.1 only
            protected void openFileChooser(ValueCallback<Uri> uploadMsg, String acceptType, String capture) {
                mUploadMessage = uploadMsg;
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.addCategory(Intent.CATEGORY_OPENABLE);
                intent.setType("*/*");
                startActivityForResult(Intent.createChooser(intent, getString(R.string.dosyagezgini)), FILECHOOSER_RESULTCODE);
            }

            protected void openFileChooser(ValueCallback<Uri> uploadMsg) {
                mUploadMessage = uploadMsg;
                Intent i = new Intent(Intent.ACTION_GET_CONTENT);
                i.addCategory(Intent.CATEGORY_OPENABLE);
                i.setType("*/*");
                startActivityForResult(Intent.createChooser(i, getString(R.string.dosyasecici)), FILECHOOSER_RESULTCODE);
            }


            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                ProgressBar pb = findViewById(R.id.progressbar);
                if (100 == newProgress) {

                    pb.setVisibility(View.GONE);
                } else {
                    pb.setVisibility(View.VISIBLE);
                }
                pb.setProgress(newProgress);


            }


            @Override
            public void onShowCustomView(View view, WebChromeClient.CustomViewCallback callback) {
                if (customview1 != null) {

                    onHideCustomView();
                    return;
                }

                customview1 = view;
                mOriginalSystemUiVisibility = getWindow().getDecorView().getSystemUiVisibility();
                mOriginalOrientation = getRequestedOrientation();
                mCustomViewCallBack = callback;

                FrameLayout decor = (FrameLayout) getWindow().getDecorView();
                decor.addView(customview1, new FrameLayout.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT));


                getWindow().getDecorView().setSystemUiVisibility(
                        View.SYSTEM_UI_FLAG_LAYOUT_STABLE |
                                View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION |
                                View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN |
                                View.SYSTEM_UI_FLAG_HIDE_NAVIGATION |
                                View.SYSTEM_UI_FLAG_FULLSCREEN |
                                View.SYSTEM_UI_FLAG_IMMERSIVE);
                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);


            }

            @Override
            public void onHideCustomView() {
                // 1. Remove the custom view
                try {
                    FrameLayout decor = (FrameLayout) getWindow().getDecorView();
                    decor.removeView(customview1);
                    customview1 = null;

                    // 2. Restore the state to it's original form
                    getWindow().getDecorView()
                            .setSystemUiVisibility(mOriginalSystemUiVisibility);
                    setRequestedOrientation(mOriginalOrientation);
					/*
					mCustomViewCallback.onCustomViewHidden();
					mCustomViewCallback = null;*/

                } catch (Exception e) {

                    NoFrost.showMessage(e.toString());

                }
            }

        });


        registerForContextMenu(webview1);
        webview1.loadUrl(getString(R.string.url1));
        _webSettings();
        ohDearDownloadMe();


        webview1.setWebViewClient(new WebViewClient() {

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                _soLinkMyPage(url);
                return super.shouldOverrideUrlLoading(view, url);
            }


            public void onReceivedError(WebView webview1, int errorCode, String description, String failingUrl) {

                if (String.valueOf((long) (errorCode)).equals("-2")) {

                    if (isNetworkAvailable()) {

                        errorbind.setVisibility(View.VISIBLE);
                        errorbind.setProgress(0);
                        errorbind.playAnimation();
                        linear1.setVisibility(View.GONE);
                        NoFrost.showError("Bağlantı Hatası");
                    } else if (errorbind.isAnimating()) {

                        linear1.setVisibility(View.VISIBLE);
                        errorbind.setProgress(0);
                        errorbind.pauseAnimation();
                        errorbind.setVisibility(View.GONE);
                        NoFrost.showSuccess("Bağlantı Kuruldu!");

                    }
                } else {
                    NoFrost.showError(getString(R.string.errorcode).concat(String.valueOf((long) (errorCode))).concat("\n".concat(getString(R.string.description).concat(description).concat("\n".concat("Url: ".concat(failingUrl))))));
                }
            }


        });


        errorbind.setOnClickListener(v -> {

            webview1.reload();

            if (isNetworkAvailable()) {

                errorbind.setVisibility(View.VISIBLE);
                errorbind.setProgress(0);
                errorbind.playAnimation();
                linear1.setVisibility(View.GONE);
                NoFrost.showError("Bağlantı Hatası");
            } else if (errorbind.isAnimating()) {

                linear1.setVisibility(View.VISIBLE);
                errorbind.setProgress(0);
                errorbind.pauseAnimation();
                errorbind.setVisibility(View.GONE);
                NoFrost.showSuccess("Bağlantı Kuruldu!");

            }
        });


        if (isNetworkAvailable()) {

            errorbind.setVisibility(View.VISIBLE);
            errorbind.setProgress(0);
            errorbind.playAnimation();
            linear1.setVisibility(View.GONE);
            NoFrost.showError("Bağlantı Hatası");
        } else if (errorbind.isAnimating()) {

            linear1.setVisibility(View.VISIBLE);
            errorbind.setProgress(0);
            errorbind.pauseAnimation();
            errorbind.setVisibility(View.GONE);
            NoFrost.showSuccess("Bağlantı Kuruldu!");

        }


    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);

        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();

        return activeNetworkInfo == null || !activeNetworkInfo.isConnected();
    }

    private void setupSharedPreferences() {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        sharedPreferences.registerOnSharedPreferenceChangeListener(this);

    }


    private void ssss() {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getBaseContext());

        js = prefs.getBoolean("jsdata", true);
        down = prefs.getBoolean("downloaddata", false);
        up = prefs.getBoolean("uploaddata", false);
        form = prefs.getBoolean("formdata", true);
        cook = prefs.getBoolean("cook", true);
        aca = prefs.getBoolean("aca", true);
        afa = prefs.getBoolean("afa", true);
        cache = prefs.getBoolean("cache", true);
        zoom = prefs.getBoolean("zoom", false);
        darkM = prefs.getBoolean("darkM", false);

    }


    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {

        if (key.equals("jsdata")) {
            js = sharedPreferences.getBoolean(key, true);

        }
        if (key.equals("downloaddata")) {
            down = sharedPreferences.getBoolean(key, false);
        }

        if (key.equals("uploaddata")) {
            up = sharedPreferences.getBoolean(key, false);
        }

        if (key.equals("formdata")) {
            form = sharedPreferences.getBoolean(key, true);

        }

        if (key.equals("cook")) {
            cook = sharedPreferences.getBoolean(key, true);

        }

        if (key.equals("aca")) {
            aca = sharedPreferences.getBoolean(key, true);

        }

        if (key.equals("afa")) {
            afa = sharedPreferences.getBoolean(key, true);

        }

        if (key.equals("cache")) {
            cache = sharedPreferences.getBoolean(key, true);

        }

        if (key.equals("zoom")) {
            zoom = sharedPreferences.getBoolean(key, false);

        }
        if (key.equals("darkM")) {
            darkM = sharedPreferences.getBoolean(key, false);
            recreate();
        }
        webview1.reload();
        _webSettings();
    }


    private void setDarkModeOn(Boolean bool) {

        if (bool) {
            setTheme(R.style.AppThemeD);
        } else {
            setTheme(R.style.AppTheme);
        }

    }

    private void setDarkModeOnWeb(Boolean bool) {

        if (bool) {
            DarkMode.featureDark(webview1);
        } else {
            DarkMode.featureLight(webview1);
        }

    }



    @SuppressLint("SetJavaScriptEnabled")
    private void _webSettings() {

        setDarkModeOnWeb(darkM);

        webview1.getSettings().setJavaScriptEnabled(js);

        webview1.getSettings().setJavaScriptCanOpenWindowsAutomatically(js);

        CookieManager.getInstance().setAcceptCookie(cook);

        webview1.getSettings().setAllowContentAccess(aca);

        webview1.getSettings().setAllowFileAccess(afa);

        webview1.getSettings().setAppCacheEnabled(cache);

        webview1.getSettings().setLoadWithOverviewMode(true);

        webview1.getSettings().setUseWideViewPort(true);

        webview1.getSettings().setDomStorageEnabled(true);

        webview1.getSettings().setSaveFormData(form);

        webview1.getSettings().setSupportZoom(zoom);

        webview1.getSettings().setBuiltInZoomControls(zoom);

        webview1.getSettings().setDisplayZoomControls(zoom);
        /*
        webview1.loadUrl("javascript:(function(){ document.querySelector(\"#mobile-menu > ul > li.menu-item.menu-item-type-post_type.menu-item-object-page.menu-item-4279 > a\").style.display=\"none\";})();");
        */
    }


    @SuppressLint("SetJavaScriptEnabled")
    private void _swipeToRefreshWeb(final WebView _webview, final View _view) {


        final SwipeRefreshLayout sr = new SwipeRefreshLayout(this);
        sr.setLayoutParams(new LinearLayout.LayoutParams(android.widget.LinearLayout.LayoutParams.MATCH_PARENT, android.widget.LinearLayout.LayoutParams.MATCH_PARENT));
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


    private void _soLinkMyPage(final String _urlSt) {
        double iii;
        for (iii = 0; iii < strr.size(); iii++) {

            if (_urlSt.contains(strr.get((int) (iii)))) {
                webview1.stopLoading();
                Intent intentt = new Intent();
                intentt.setAction(Intent.ACTION_VIEW);
                intentt.setData(Uri.parse(_urlSt));
                startActivity(intentt);
            }
        }
    }


    private void ohDearDownloadMe() {

        ssss();

        downl(webview1, down);

    }




    //Video Reklam yükle
    private void loadRewardedVideoAd() {
        //yüklenirse gösterilir..
        if (!mRewardedVideoAd.isLoaded()) {
            mRewardedVideoAd.loadAd(AD_UNIT_ID, new AdRequest.Builder().build());
        } else {
            webview1.loadUrl(getString(R.string.url4));

        }
    }

    @Override
    public void onRewardedVideoAdLoaded() {
        //video yüklenir..

    }

    @Override
    public void onRewardedVideoAdOpened() {
        //Video açılır..

    }

    @Override
    public void onRewardedVideoStarted() {
        //Video başlar..

    }

    @Override
    public void onRewardedVideoAdClosed() {
        //Video Kapatılır..

    }

    @Override
    public void onRewarded(RewardItem rewardItem) {
        //Eğer reklam izlenip bittiyse belirlediğiniz puan bu method'a gelecektir.
        //rewardItem.getAmount() ile puanı alabilirsiniz.
        webview1.loadUrl(getUr);

        _webSettings();

    }

    @Override
    public void onRewardedVideoAdLeftApplication() {
        //Reklamı terk eder..

    }

    @Override
    public void onRewardedVideoAdFailedToLoad(int i) {
        //Reklam Yüklenmez hata kodu: i
        NoFrost.showError("Hata: " + i);
    }

    @Override
    public void onRewardedVideoCompleted() {
        //Reklam biter..
        loadRewardedVideoAd();
    }

}
