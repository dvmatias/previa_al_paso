package adapters;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

import fragments.FragmentAgreement;
import fragments.FragmentLogIn;
import fragments.FragmentPayment;
import fragments.FragmentWelcome;

/**
 * Adapter for the 4 fragment displayed on the
 * {@link com.previaalpaso.matiasdelvecchio.previaalpaso.activities.SplashActivity}
 *
 * Created by cesar.delvecchio on 21/03/2018
 */
public class AdapterSplashPager extends FragmentPagerAdapter {
    /**
     * TAG.
     */
    private static final String TAG = AdapterSplashPager.class.getSimpleName();

    /**
     * Context.
     */
    private Context context;

    /**
     * Number of fragments on splash pager.
     */
    private final int NUM_ITEMS = 4;

    /**
     * Constructor
     *
     * @param fm [FragmentManager]
     */
    public AdapterSplashPager(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return FragmentAgreement.newInstance();
            case 1:
                return FragmentLogIn.newInstance();
            case 2:
                return FragmentPayment.newInstance();
            case 3:
                return FragmentWelcome.newInstance();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return NUM_ITEMS;
    }

}
