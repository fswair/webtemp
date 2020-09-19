package com.webdemo.activity;


import android.Manifest;
import android.annotation.SuppressLint;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
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
import com.webdemo.R;
import com.webdemo.call.NoFrost;
import com.webdemo.recycler.Person;
import com.webdemo.recycler.SimpleRecyclerAdapter;
import com.webdemo.request.RequestNetwork;
import com.webdemo.websettings.DarkMode;
import com.webdemo.websettings.ImageDownloader;
import com.webdemo.websettings.WebSet;

import net.yslibrary.android.keyboardvisibilityevent.KeyboardVisibilityEvent;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

import static android.view.View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
import static com.google.android.material.bottomnavigation.BottomNavigationView.OnNavigationItemSelectedListener;


/**
 * induiduel WebView projesidir.
 */

public class MainActivity extends MainManager implements SharedPreferences.OnSharedPreferenceChangeListener, RewardedVideoAdListener {
    public static final int REQUEST_SELECT_FILE = 100;
    private final static int FILECHOOSER_RESULTCODE = 1;
    public ValueCallback<Uri[]> uploadMessage;
    protected FrameLayout mFullscreenContainer;
    LottieAnimationView errorbind;
    private DrawerLayout _drawer;
    private FileChooserParams fileChooserParams;
    private ValueCallback mUploadMessage;
    private ArrayList<String> location = new ArrayList<>();
    private ArrayList<HashMap<String, Object>> getThis = new ArrayList<>();
    private LinearLayout linear1;
    public static WebView webview1;
    private ProgressBar progressbar;
    private Intent intent = new Intent();
    private Intent i = new Intent();
    private View customview1;
    private int mOriginalSystemUiVisibility;
    public static Context context;
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


    public static boolean js = true;
    public static boolean down = true;
    public static boolean up = true;
    public static boolean form = true;
    public static boolean cook = true;
    public static boolean aca = true;
    public static boolean afa = true;
    public static boolean cache = true;
    public static boolean zoom = true;
    public static boolean darkM = false;

    public static String getUr = "";
    private boolean acs = false;
    private int als = 0;
    //TODO ADMOB APP and AD ID's
    //app
    public static String APP_ID = "ca-app-pub-3039242376817399~8547681739";
    //ads
    public static String AD_UNIT_ID = "ca-app-pub-3039242376817399/4392620783";
    public static String INT_UNIT_ID = "ca-app-pub-3039242376817399/4420540945";

    public static RewardedVideoAd mRewardedVideoAd;
    public static InterstitialAd mInterstitialAd;

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

    public static void setDarkModeOnWeb(Boolean bool) {

        if (bool) {
            DarkMode.featureDark(webview1);
        } else {
            DarkMode.featureLight(webview1);
        }

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


        SharedPreferences s = getSharedPreferences("s", AppCompatActivity.MODE_PRIVATE);

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

    @RequiresApi(api = VERSION_CODES.M)
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


        sharedPreferencesWebSettings();
        setDarkModeOn(darkM);
        setContentView(R.layout.main);
        bottomNavigation = findViewById(R.id.bottom_navigation);
        errorbind = findViewById(R.id.errorbind);
        errorbind.setVisibility(View.GONE);

        initialize(_savedInstanceState);
        //Asked for allow permissions
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED || ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1000);
        } else {
            if (Build.VERSION.SDK_INT >= VERSION_CODES.M) {
                initializeLogic();
                if (Build.VERSION.SDK_INT >= VERSION_CODES.O) {
                    WebSet.additionalSettings(webview1, true);
                }
            } else {
                NoFrost.showError(getApplicationContext(), getString(R.string.lowapi));
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

        //noinspection deprecation
        MobileAds.initialize(this, APP_ID);
        mRewardedVideoAd = MobileAds.getRewardedVideoAdInstance(this);
        mRewardedVideoAd.setRewardedVideoAdListener(this);
        loadRewardedVideoAd();

        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId(INT_UNIT_ID);
        mInterstitialAd.loadAd(new AdRequest.Builder().build());

        setupSharedPreferences();


        mInterstitialAd.setAdListener(new AdListener() {
            @SuppressWarnings("deprecation")
            @Override
            public void onAdFailedToLoad(int errorCode) {
                // Hata Bildirimi
                //NoFrost.showError(getApplicationContext(),"Hata: " + errorCode);
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

    private void errorResponse(boolean check) {
        if (check) {
            if (isNetworkAvailable()) {
                errorbind.setVisibility(View.VISIBLE);
                errorbind.setProgress(0);
                errorbind.playAnimation();
                linear1.setVisibility(View.GONE);
                NoFrost.showError(getApplicationContext(), "Bağlantı Hatası");
            } else if (errorbind.isAnimating()) {
                linear1.setVisibility(View.VISIBLE);
                errorbind.setProgress(0);
                errorbind.pauseAnimation();
                errorbind.setVisibility(View.GONE);
                NoFrost.showSuccess(getApplicationContext(), "Bağlantı Kuruldu!");
            }
        } else {
            if (isNetworkAvailable()) {
                errorbind.setVisibility(View.VISIBLE);
                errorbind.setProgress(0);
                errorbind.playAnimation();
                linear1.setVisibility(View.GONE);

            } else if (errorbind.isAnimating()) {
                linear1.setVisibility(View.VISIBLE);
                errorbind.setProgress(0);
                errorbind.pauseAnimation();
                errorbind.setVisibility(View.GONE);

            }
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

    private void onLogic() {
/*
        try {
            SecretKey secret = generateKey();
            byte[] a = Ciphers.encryptMsg("String toEncrypt", secret);

        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | InvalidParameterSpecException | IllegalBlockSizeException | BadPaddingException | UnsupportedEncodingException | InvalidKeySpecException e) {
            e.printStackTrace();
        }

 */
        sharedPreferencesWebSettings();
        KeyboardVisibilityEvent.setEventListener(
                MainActivity.this,
                isOpen -> {
                    // some code depending on keyboard visiblity status


                    if (isOpen) {

                        bottomNavigation.setVisibility(View.GONE);

                    } else {

                        bottomNavigation.setVisibility(View.GONE);

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
                    NoFrost.showError(getApplicationContext(), getString(R.string.dosyaerror));
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

                    NoFrost.showMessage(getApplicationContext(), e.toString());

                }
            }

        });


        registerForContextMenu(webview1);
        webview1.loadUrl(getString(R.string.url1));
        _webSettings(webview1);
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

                    errorResponse(true);
                } else {
                    if (!String.valueOf((long) (errorCode)).equals("0")) {
                        NoFrost.showError(getApplicationContext(), getString(R.string.errorcode).concat(String.valueOf((long) (errorCode))).concat("\n".concat(getString(R.string.description).concat(description).concat("\n".concat("Url: ".concat(failingUrl))))));
                    }
                }
            }

        });

        errorbind.setOnClickListener(v -> {

            webview1.reload();
            errorResponse(false);

        });

    }


    private void setDarkModeOn(Boolean bool) {

        if (bool) {
            setTheme(R.style.AppThemeD);
        } else {
            setTheme(R.style.AppTheme);
        }

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
        _webSettings(webview1);
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

        _webSettings(webview1);

    }

    @Override
    public void onRewardedVideoAdLeftApplication() {
        //Reklamı terk eder..

    }

    @Override
    public void onRewardedVideoAdFailedToLoad(int i) {
        //Reklam Yüklenmez hata kodu: i
        // NoFrost.showError(getApplicationContext(),"Hata: " + i);
    }

    @Override
    public void onRewardedVideoCompleted() {
        //Reklam biter..
        loadRewardedVideoAd();
    }

}
