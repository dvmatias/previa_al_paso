package fragments;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
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
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.previaalpaso.matiasdelvecchio.previaalpaso.R;
import com.previaalpaso.matiasdelvecchio.previaalpaso.activities.SplashActivity;

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

        // Configure sign-in to request the user's ID, email address, and basic
        // profile. ID and basic profile are included in DEFAULT_SIGN_IN.
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        // Build a GoogleSignInClient with the options specified by gso.
        mGoogleSignInClient = GoogleSignIn.getClient(getActivity(), gso);
    }

    @Override
    public void onStart() {
        super.onStart();

        // Check for existing Google Sign In account, if the user is already signed in
        // the GoogleSignInAccount will be non-null.
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(getActivity());
        // TODO updateUI(account);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.fragment_login, container, false);

        activity = getActivity();
        buttonLoginFacebook = rootView.findViewById(R.id.button_login_facebook);
        buttonSignInGoogle = rootView.findViewById(R.id.sign_in_google_button);

        setupActionBar(rootView);
        setupLoginFacebookButton();
        setupSignInGoogleButton();

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

    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);

            // Signed in successfully, show authenticated UI.
            if (account!=null) {
                Log.d(TAG, "*** mabel " + account.getEmail());
                Log.d(TAG, "*** mabel " + account.getDisplayName());
                Log.d(TAG, "*** mabel " + account.getGivenName());
            } else {
                Log.d(TAG, "*** mabel ACCOUNT NULL");
            }
            //updateUI(account);
        } catch (ApiException e) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Log.w(TAG, "*** mabel signInResult:failed code=" + e.getStatusCode());
            //updateUI(null);
        }
    }

    /**
     * TODO desc
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
     * TODO desc
     */
    private void setupSignInGoogleButton() {
        buttonSignInGoogle.setOnClickListener(this);

        for (int i = 0; i < buttonSignInGoogle.getChildCount(); i++) {
            View v = buttonSignInGoogle.getChildAt(i);

            if (v instanceof TextView) {
                TextView tv = (TextView) v;
                tv.setText(R.string.login_google_button);
                tv.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_START);
                return;
            }
        }
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
        }
    }
}
