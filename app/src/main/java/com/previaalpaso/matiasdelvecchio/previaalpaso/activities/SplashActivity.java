package com.previaalpaso.matiasdelvecchio.previaalpaso.activities;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.previaalpaso.matiasdelvecchio.previaalpaso.R;

import adapters.AdapterSplashPager;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class SplashActivity extends AppCompatActivity {
    /**
     * TAG.
     */
    private static final String TAG = SplashActivity.class.getSimpleName();

    /**
     * Main view pager to show splash screens.
     */
    private ViewPager pager;

    /**
     * Main view pager adapter.
     */
    private AdapterSplashPager adapterPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        pager = findViewById(R.id.pager);


        // Hide UI first
        setupActionBar();
        setupPager();
    }

    /**
     *
     */
    private void setupActionBar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        TextView toolbarTitle = toolbar.findViewById(R.id.toolbar_title);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbarTitle.setText(getResources().getString(R.string.agreement_actionbar_title));
    }

    /**
     *
     */
    private void setupPager() {
        adapterPager = new AdapterSplashPager(getSupportFragmentManager());
        pager.setAdapter(adapterPager);
    }

}
