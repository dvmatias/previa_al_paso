package utils.view;

/**
 * Created by cesar.delvecchio on 26/03/2018.
 */

public interface ISplashView {
    String SP_NAME_AGREEMENT = "sp_name_agreement";
    String SP_KEY_AGREEMENT = "sp_key_agreement";
    boolean SP_DEF_AGREEMENT = false;

    void showPreviousFragment();
    void showNextFragment();
    void saveAgreement(boolean isAgree);
    boolean getAgreement();
    void saveCreditCard();
    void saveFacebookInfo();
    void saveAccountInfo();
    void launchForgetPassword();
}
