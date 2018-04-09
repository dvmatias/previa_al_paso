package fragments;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.previaalpaso.matiasdelvecchio.previaalpaso.R;
import com.previaalpaso.matiasdelvecchio.previaalpaso.activities.SplashActivity;

import utils.view.ISplashView;

/**
 * Created by cesar.delvecchio on 21/03/2018
 */

public class FragmentLogIn extends Fragment implements View.OnClickListener{
    /**
     * TAG.
     */
    @SuppressWarnings("unused")
    private static final String TAG = FragmentLogIn.class.getSimpleName();

    /**
     * Activity.
     */
    private Activity activity;

    /**
     * Login Facebook Button.
     */
    private Button buttonLoginFacebook;

    /**
     * Login Google account Button.
     */
    private SignInButton buttonSignInGoogle;

    /**
     *
     */
    private GoogleSignInClient mGoogleSignInClient;

    private GoogleSignInAccount account;

    /**
     * Checkbox to remember account.
     */
    private CheckBox checkBoxRememberAccount;

    /**
     * Next button.
     */
    private Button buttonNext;

    /**
     * User signing status.
     */
    private boolean isUserSigned = false;

    /**
     * Remember account.
     */
    private boolean rememberAccount;

    /**
     * New instance for {@link FragmentAgreement}
     *
     * @return [FragmentAgreement] Instance.
     */
    public static FragmentLogIn newInstance() {
        FragmentLogIn fragment = new FragmentLogIn();

        Bundle args = new Bundle();
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activity = getActivity();

        // Configure sign-in to request the user's ID, email address, and basic
        // profile. ID and basic profile are included in DEFAULT_SIGN_IN.
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestProfile()
                .requestEmail()
                .build();

        // Build a GoogleSignInClient with the options specified by gso.
        mGoogleSignInClient = GoogleSignIn.getClient(getActivity(), gso);

        if (activity != null && activity instanceof SplashActivity) {
            rememberAccount = ((SplashActivity) activity).getRememberAccount();
        }
    }

    @Override
    public void onStart() {
        super.onStart();

        // Check for existing Google Sign In account, if the user is already signed in
        // the GoogleSignInAccount will be non-null.
        account = GoogleSignIn.getLastSignedInAccount(getActivity());

        // If the choice of remember account is false, sign out.
        if (!rememberAccount) {
            mGoogleSignInClient.signOut()
                    .addOnCompleteListener(getActivity(), new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            updateUI(null);
                        }
                    });
        }

        updateUI(account);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.fragment_login, container, false);

        buttonLoginFacebook = rootView.findViewById(R.id.button_login_facebook);
        buttonSignInGoogle = rootView.findViewById(R.id.sign_in_google_button);
        buttonNext = rootView.findViewById(R.id.button_login_next);
        checkBoxRememberAccount = rootView.findViewById(R.id.checkbox_remember_account);

        setupActionBar(rootView);
        setupLoginFacebookButton();
        setupSignInGoogleButton();
        setupCheckbox();
        setupButtonNext();

        return rootView;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == 123) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
    }

    /**
     *
     * @param completedTask
     */
    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            account = completedTask.getResult(ApiException.class);
            updateUI(account);
        } catch (ApiException e) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Log.w(TAG, "*** mabel signInResult:failed code=" + e.getStatusCode());
            updateUI(null);
        }
    }

    /**
     * Sign in user with Facebook account.
     */
    private void setupLoginFacebookButton() {
       buttonLoginFacebook.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               // TODO. Login user with his Facebook account.
           }
       });
    }

    /**
     * Setup Sign In Google Button.
     */
    private void setupSignInGoogleButton() {
        buttonSignInGoogle.setOnClickListener(this);

        for (int i = 0; i < buttonSignInGoogle.getChildCount(); i++) {
            View v = buttonSignInGoogle.getChildAt(i);

            // Setup text.
            if (v instanceof TextView) {
                TextView tv = (TextView) v;
                tv.setText(R.string.login_google_button);
                tv.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_START);
                return;
            }
        }
    }

    /**
     * Setup check box initial status.
     */
    private void setupCheckbox() {
        setCheckboxEnable();

        if (activity != null && activity instanceof SplashActivity) {
            rememberAccount = ((SplashActivity) activity).getRememberAccount();
        }

        // Set checked according previous agreement saved if any.
        checkBoxRememberAccount.setChecked(rememberAccount);

        // On checked change listener.
        checkBoxRememberAccount.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                rememberAccount = isChecked;

                if (activity != null && activity instanceof SplashActivity) {
                    ((SplashActivity) activity).saveRememberAccount(rememberAccount);
                }
            }
        });
    }

    /**
     * TODO desc
     */
    private void setCheckboxEnable() {
        checkBoxRememberAccount.setEnabled(isUserSigned);
    }

    /**
     * TODO desc
     */
    private void setupButtonNext() {
        // Button status.
        setButtonNextEnabled();
        // Click listener.
        buttonNext.setOnClickListener(this);
    }

    /**
     * TODO desc
     */
    private void setButtonNextEnabled() {
        buttonNext.setEnabled(isUserSigned);
        buttonNext.setClickable(isUserSigned);
    }

    /**
     * Set Action Bar.
     */
    private void setupActionBar(View rootView) {
        Toolbar toolbar = rootView.findViewById(R.id.toolbar);

        TextView toolbarTitle = toolbar.findViewById(R.id.toolbar_title);
        ImageView toolbarBackButton = toolbar.findViewById(R.id.toolbar_button_back);

        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        ActionBar actionBar = ((AppCompatActivity)getActivity()).getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayShowTitleEnabled(false);
            actionBar.setHomeButtonEnabled(false);
        }

        toolbarTitle.setText(getResources().getString(R.string.login_actionbar_title));
        toolbarBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (activity != null && activity instanceof SplashActivity) {
                    ((SplashActivity) activity).showPreviousFragment();
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.sign_in_google_button:
                Intent signInIntent = mGoogleSignInClient.getSignInIntent();
                startActivityForResult(signInIntent, 123);
                break;
            case R.id.button_login_next:
                if (activity != null && activity instanceof SplashActivity) {
                    ((SplashActivity) activity).showNextFragment();
                }
                break;
        }
    }

    private void updateUI(GoogleSignInAccount account) {
        isUserSigned = (account != null);
        setButtonNextEnabled();
        setCheckboxEnable();
        checkBoxRememberAccount.setChecked(isUserSigned);
    }
}
