package utils.presenter;

import utils.view.ISplashView;

/**
 * Created by cesar.delvecchio on 26/03/2018.
 */

public class SplashPresenter {
    /**
     * TAG.
     */
    private static final String TAG = SplashPresenter.class.getSimpleName();

    /**
     * View.
     */
    private ISplashView view;

    /**
     * Attach the view.
     *
     * @param view [ISplashView] View.
     */
    public void attachView(ISplashView view) {
        this.view = view;
    }
}
