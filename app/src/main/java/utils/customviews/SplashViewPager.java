package utils.customviews;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Interpolator;
import android.widget.Scroller;

import com.previaalpaso.matiasdelvecchio.previaalpaso.activities.SplashActivity;

import java.lang.reflect.Field;

/**
 * Created by cesar.delvecchio on 26/03/2018.
 */

public class SplashViewPager extends ViewPager {
    /**
     * TAG.
     */
    @SuppressWarnings("unused")
    private static final String TAG = SplashViewPager.class.getSimpleName();

    /**
     * Pager scroller.
     */
    private ScrollerCustomDuration scroller;

    /**
     * Pager scroll duration in MS.
     */
    private static final int SELECTED_LIGHT_VIEW_PAGER_SCROLL_DURATION = 500;

    /**
     * Is swipe enabled?
     */
    private boolean swipeEnabled;

    public SplashViewPager(Context context) {
        super(context);
        setupViewPager(context);
    }

    public SplashViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        setupViewPager(context);
    }

    private void setupViewPager(Context context) {
        // Scroller.
        try {
            Field mScroller;
            mScroller = ViewPager.class.getDeclaredField("mScroller");
            mScroller.setAccessible(true);
            scroller = new ScrollerCustomDuration(
                    context, new AccelerateInterpolator());
            scroller.setScrollDuration(SELECTED_LIGHT_VIEW_PAGER_SCROLL_DURATION);
            mScroller.set(this, scroller);
        } catch (NoSuchFieldException | IllegalArgumentException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return swipeEnabled && super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return swipeEnabled && super.onTouchEvent(ev);
    }

    @Override
    public boolean canScrollHorizontally(int direction) {
        return swipeEnabled && super.canScrollHorizontally(direction);
    }

    public void setSwipeEnabled(boolean enabled) {
        swipeEnabled = enabled;
    }

    public boolean isSwipeEnabled() {
        return swipeEnabled;
    }



    /**
     * Custom Scroller. Allows to modify the scroll velocity of the selectedLightViewPager.
     * To use properly we have to set the `duration` attr with selectedLightViewPagerContainer value (in ms) that is going
     * to be used selectedLightViewPagerContainer scroll duration.
     */
    @SuppressWarnings("unused")
    public class ScrollerCustomDuration extends Scroller {

        private int duration = 1;

        public ScrollerCustomDuration(Context context) {
            super(context);
        }

        ScrollerCustomDuration(Context context, Interpolator interpolator) {
            super(context, interpolator);
        }

        public ScrollerCustomDuration(Context context, Interpolator interpolator, boolean flywheel) {
            super(context, interpolator, flywheel);
        }

        /**
         * Set the factor by which the duration will change
         */
        @SuppressWarnings("WeakerAccess")
        public void setScrollDuration(int fixedDuration) {
            this.duration = fixedDuration;
        }

        @Override
        public void startScroll(int startX, int startY, int dx, int dy, int duration) {
            super.startScroll(startX, startY, dx, dy, this.duration);
        }
    }
}
