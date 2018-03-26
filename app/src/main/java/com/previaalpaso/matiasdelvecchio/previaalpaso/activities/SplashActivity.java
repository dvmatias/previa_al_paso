package com.previaalpaso.matiasdelvecchio.previaalpaso.activities;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.previaalpaso.matiasdelvecchio.previaalpaso.R;

import adapters.AdapterSplashPager;
import utils.customviews.PagerIndicator;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class SplashActivity extends AppCompatActivity {
    /**
     * TAG.
     */
    @SuppressWarnings("unused")
    private static final String TAG = SplashActivity.class.getSimpleName();

    /**
     * Main view pager to show splash screens.
     */
    private ViewPager pager;

    /**
     * Main view pager adapter.
     */
    AdapterSplashPager adapterPager;

    /**
     * Page indicator.
     */
    private PagerIndicator pagerIndicator;

    /**
     * Only last page indicator is active (full painted).
     */
    private static final boolean IN_ONLY_LAST_ACTIVE = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        pager = findViewById(R.id.pager);
        pagerIndicator = findViewById(R.id.page_indicator);

        // Hide UI first
        setupPager();
        setupPageIndicator();
    }

    /**
     *
     */
    private void setupPager() {
        // Set pager adapter.
        adapterPager = new AdapterSplashPager(getSupportFragmentManager());
        pager.setAdapter(adapterPager);

        // Set pager change listener.
        pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                // Update indicator.
                pagerIndicator.setIndicatorsActive(position, IN_ONLY_LAST_ACTIVE);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    /**
     *
     */
    private void setupPageIndicator() {
        pagerIndicator.setIndicatorCount(pager.getAdapter().getCount());
        pagerIndicator.setIndicatorsActive(0, false);
    }
}
