package com.previaalpaso.matiasdelvecchio.previaalpaso.activities;

import android.annotation.SuppressLint;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.FrameLayout;

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

    /**
     * Some older devices needs a small delay between UI widget updates
     * and a change of the status and navigation bar.
     */
    private static final int UI_ANIMATION_DELAY = 300;

    /**
     * Handler.
     */
    private final Handler mHideHandler = new Handler();

    /**
     * Content view.
     */
    private FrameLayout mContentView;

    /**
     * Task to hide.
     */
    private final Runnable mHidePart2Runnable = new Runnable() {
        @SuppressLint("InlinedApi")
        @Override
        public void run() {
            // Delayed removal of status and navigation bar

            // Note that some of these constants are new as of API 16 (Jelly Bean)
            // and API 19 (KitKat). It is safe to use them, as they are inlined
            // at compile-time and do nothing on earlier devices.
            mContentView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE
                    | View.SYSTEM_UI_FLAG_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
        }
    };

    /**
     * TODO desc.
     */
    private final Runnable mShowPart2Runnable = new Runnable() {
        @Override
        public void run() {
            // Delayed display of UI elements
            ActionBar actionBar = getSupportActionBar();
            if (actionBar != null) {
                actionBar.show();
            }

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_splash);

        mContentView = findViewById(R.id.fullscreen_content);
        pager = findViewById(R.id.pager);


        // Hide UI first
        hide();

        setupPager();
    }

    /**
     *
     */
    private void setupPager() {
        adapterPager = new AdapterSplashPager(getSupportFragmentManager());
        pager.setAdapter(adapterPager);
    }

    /**
     * TODO desc.
     */
    private void hide() {
        // Hide UI first
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }

        // Schedule a runnable to remove the status and navigation bar after a delay
        mHideHandler.removeCallbacks(mShowPart2Runnable);
        mHideHandler.postDelayed(mHidePart2Runnable, UI_ANIMATION_DELAY);
    }

}
