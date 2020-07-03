package com.notalarim.activity;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.notalarim.R;


/**
 * induiduel webview projesidir.
 */

public class SettingsActivity extends AppCompatActivity {

    private AdView mAdView;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_activity);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.settings, new SettingsFragment())
                .commit();
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(Color.parseColor(getString(R.string.beyaz)));
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);


        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });
        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finishActivity(0);
    }

    @Override
    protected void onPause() {
        super.onPause();
        finishActivity(0);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        finishActivity(0);
    }


    public static class SettingsFragment extends PreferenceFragmentCompat {
        Preference mp = findPreference("geri");

        @Override
        public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
            setPreferencesFromResource(R.xml.root_preferences, rootKey);

        }

        @Override
        public boolean onPreferenceTreeClick(Preference preference) {
            try {

                String key = preference.getKey();
                if (key.equals("geri")) {

                    Intent i = new Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto", getString(R.string.email), null));
                    i.putExtra(Intent.EXTRA_SUBJECT, "Subject");
                    i.putExtra(Intent.EXTRA_TEXT, "Body");
                    startActivity(Intent.createChooser(i, getString(R.string.chooseemail)));

                    return true;
                }
                if (key.equals("gizlilik")) {

                    String urr = getString(R.string.url5);

                    Intent a = new Intent(Intent.ACTION_VIEW);
                    a.setData(Uri.parse(urr));
                    startActivity(a);

                    return true;
                }
            } catch (Exception ignored) {

            }

            return false;
        }
    }
}