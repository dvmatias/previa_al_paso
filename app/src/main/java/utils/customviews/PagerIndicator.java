package utils.customviews;

import android.content.Context;
import android.support.annotation.DrawableRes;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.previaalpaso.matiasdelvecchio.previaalpaso.R;

/**
 * Created by mt on 5/25/17.
 */

@ViewPager.DecorView
public class PagerIndicator extends LinearLayout {
    private static final String TAG = "TAG_SIGN_UP_PAGER_CIRCLE_INDICATOR(no real view should have this as a tag I hope)";
    private int currentPage = 0;

    private @DrawableRes
    int activeRes   = R.drawable.viewpager_indicator_solid_yellow;
    private @DrawableRes
    int inactiveRes = R.drawable.viewpager_indicator_hollow_yellow;

    public PagerIndicator(Context ctx, AttributeSet attrs) {
        super(ctx, attrs);
    }

    public void setActiveRes(@DrawableRes int activeRes) {
        this.activeRes = activeRes;
    }

    public void setInactiveRes(@DrawableRes int inactiveRes) {
        this.inactiveRes = inactiveRes;
    }

    public void setIndicatorCount(int pageCount) {
        int padEnd = (int) getResources().getDimension(R.dimen.margin_widget);

        // Remove all existing indicator views only
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = getChildAt(0);
            if (child != null && child.getTag().equals(TAG)) {
                removeView(child);
            }
        }

        // Refresh all indicator views (none will be active)
        for (int i = 0; i < pageCount; i++) {
            // Configure params for new indicator
            ImageView indicator = new ImageView(getContext());
            indicator.setTag(TAG);
            indicator.setLayoutParams(new LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            indicator.setImageResource(inactiveRes);

            // Add end padding to every indicator but the last
            if (i != pageCount - 1) {
                indicator.setPadding(0, 0, padEnd, 0);
            }

            addView(indicator);
        }
    }

    public void setIndicatorsActive(int currPage, boolean isOnlyLastActive) {
        // Set as the current page.
        this.currentPage = currPage;

        for (int i = 0; i < getChildCount(); i++) {
            // Check that this was was internally added (only look at indicator circles)
            if (getChildAt(i).getTag().equals(TAG)) {
                ImageView indicator = (ImageView) getChildAt(i);

                if (i < currPage && !isOnlyLastActive) {
                    indicator.setImageResource(activeRes);
                } else if (i == currPage) {
                    indicator.setImageResource(activeRes);
                } else {
                    indicator.setImageResource(inactiveRes);
                }
            }
        }
    }

    /**
     * Returns the current page selected (0 for the first).
     *
     * @return [int] Current page.
     */
    public int getIndicatorCurrentPage() {
        return this.currentPage;
    }
}
