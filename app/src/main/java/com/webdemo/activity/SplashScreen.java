package com.webdemo.activity;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;

import com.webdemo.R;

import java.util.Timer;
import java.util.TimerTask;

/**
 * induiduel webview projesidir.
 */

public class SplashScreen extends AppCompatActivity implements SharedPreferences.OnSharedPreferenceChangeListener {

    private Timer _timer = new Timer();

    private LinearLayout linear1;
    private ImageView image;
    private TimerTask timer;
    private Intent intent = new Intent();
    private ObjectAnimator oa = new ObjectAnimator();
    private Boolean darkM;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle _savedInstanceState) {
        super.onCreate(_savedInstanceState);

        initialize(_savedInstanceState);
        initializeLogic();
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        darkM = prefs.getBoolean("darkM", false);
        if (darkM) {
            setTheme(R.style.AppThemeD);
        } else {
            setTheme(R.style.AppTheme);
        }


        setContentView(R.layout.splashscreen);
    }

    private void setupSharedPreferences() {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        sharedPreferences.registerOnSharedPreferenceChangeListener(this);

    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {

        if (key.equals("darkM")) {
            darkM = sharedPreferences.getBoolean(key, false);
            recreate();
        }

    }

    private void initialize(Bundle _savedInstanceState) {

        linear1 = findViewById(R.id.linear1);
        image = findViewById(R.id.image);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void initializeLogic() {
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(Color.parseColor(getString(R.string.beyaz)));
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);

        oa.setTarget(image);
        oa.setPropertyName("alpha");
        oa.setFloatValues((float) (70), (float) (100));
        oa.setDuration(150);
        oa.setInterpolator(new LinearInterpolator());
        oa.start();
        timer = new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(() -> {

                    intent.setClass(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                    finish();
                });
            }
        };
        _timer.schedule(timer, 1500);
    }

    @Override
    protected void onActivityResult(int _requestCode, int _resultCode, Intent _data) {
        super.onActivityResult(_requestCode, _resultCode, _data);


    }

    @Override
    protected void onResume() {
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        super.onResume();
    }

    public void showMessage(String _s) {
        Toast.makeText(getApplicationContext(), _s, Toast.LENGTH_SHORT).show();
    }

}
