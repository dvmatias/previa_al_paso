package utils.presenter;

import utils.view.ISplashView;

/**
 * Created by cesar.delvecchio on 26/03/2018.
 */

public class SplashPresenter {
    /**
     * TAG.
     */
    @SuppressWarnings("unused")
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

    /**
     *
     * @return [boolean] Return <b>true</b> if the view is attached, <b>false</b> otherwise.
     */
    private boolean isViewAttached() {
        return view != null;
    }

    /**
     * Show the next splash fragment.
     */
    public void showNextFragment() {
        if (isViewAttached()) {
            view.showNextFragment();
        }
    }
}
