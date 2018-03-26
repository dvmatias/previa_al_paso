package com.previaalpaso.matiasdelvecchio.previaalpaso.activities;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.previaalpaso.matiasdelvecchio.previaalpaso.R;

import adapters.AdapterSplashPager;
import utils.customviews.PagerIndicator;
import utils.customviews.SplashViewPager;
import utils.presenter.SplashPresenter;
import utils.view.ISplashView;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class SplashActivity extends AppCompatActivity implements ISplashView{
    /**
     * TAG.
     */
    @SuppressWarnings("unused")
    private static final String TAG = SplashActivity.class.getSimpleName();

    /**
     * Presenter.
     */
    public static SplashPresenter presenter;

    /**
     * Main view pager to show splash screens.
     */
    private SplashViewPager pager;

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

        presenter = new SplashPresenter();
        presenter.attachView(this);

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
        // No swipe enabled.
        pager.setSwipeEnabled(false);

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
     * Set page indicator params.
     */
    private void setupPageIndicator() {
        pagerIndicator.setIndicatorCount(pager.getAdapter().getCount());
        pagerIndicator.setIndicatorsActive(0, false);
    }

    @Override
    public void showPreviousFragment() {
        // TODO
    }

    @Override
    public void showNextFragment() {
        int nextPosition = pager.getCurrentItem() + 1;
        if (nextPosition < adapterPager.getCount()) {
            pager.setCurrentItem(nextPosition);
        }
    }

    @Override
    public void saveAgreement(boolean isAgree) {
        SharedPreferences prefs = this.getSharedPreferences(
                ISplashView.SP_NAME_AGREEMENT, Context.MODE_PRIVATE);
        prefs.edit().putBoolean(ISplashView.SP_KEY_AGREEMENT, isAgree).apply();
    }

    @Override
    public boolean getAgreement() {
        SharedPreferences prefs = this.getSharedPreferences(
                ISplashView.SP_NAME_AGREEMENT, Context.MODE_PRIVATE);
        return prefs.getBoolean(ISplashView.SP_KEY_AGREEMENT, ISplashView.SP_DEF_AGREEMENT);
    }

    @Override
    public void saveCreditCard() {
        // TODO
    }

    @Override
    public void saveFacebookInfo() {
        // TODO
    }

    @Override
    public void saveAccountInfo() {
        // TODO
    }

    @Override
    public void launchForgetPassword() {
        // TODO
    }
}
