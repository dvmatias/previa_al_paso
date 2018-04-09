package fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.previaalpaso.matiasdelvecchio.previaalpaso.R;
import com.previaalpaso.matiasdelvecchio.previaalpaso.activities.SplashActivity;

/**
 * Created by cesar.delvecchio on 21/03/2018
 */

public class FragmentAgreement extends Fragment {
    /**
     * TAG.
     */
    @SuppressWarnings("unused")
    private static final String TAG = FragmentAgreement.class.getSimpleName();

    /**
     * Activity.
     */
    private Activity activity;

    /**
     * Check box.
     */
    private CheckBox checkBoxAgreement;

    /**
     * Button.
     */
    private Button buttonAccept;

    /**
     * User choice agreement.
     */
    private boolean isAgree = false;

    /**
     * New instance for {@link FragmentAgreement}
     *
     * @return [FragmentAgreement] Instance.
     */
    public static FragmentAgreement newInstance() {
        FragmentAgreement fragment = new FragmentAgreement();

        Bundle args = new Bundle();
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activity = getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.fragment_agreement, container, false);

        checkBoxAgreement = rootView.findViewById(R.id.checkbox_agreement);
        buttonAccept = rootView.findViewById(R.id.button_accept);

        setIsAgree();
        setupCheckbox();
        setupButton();
        setupActionBar(rootView);

        return rootView;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        // If the fragment is destroyed, the agreement must set to the value selected by user.
        ((SplashActivity) activity).saveAgreement(isAgree);
    }

    /**
     * Get agreement from a previous session.
     */
    private void setIsAgree() {
        if (activity != null && activity instanceof SplashActivity) {
            isAgree = ((SplashActivity) activity).getAgreement();
        }
    }

    /**
     * Setup check box.
     */
    private void setupCheckbox() {
        // Set checked according previous agreement saved if any.
        checkBoxAgreement.setChecked(isAgree);

        // On checked change listener.
        checkBoxAgreement.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                setButtonEnabled(isChecked);
                isAgree = isChecked;
            }
        });
    }

    /**
     * Setup button to display next fragment.
     */
    private void setupButton() {
        // Set button status.
        setButtonEnabled(isAgree);

        // Click listener.
        buttonAccept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (activity != null && activity instanceof SplashActivity) {
                    ((SplashActivity) activity).saveAgreement(isAgree);
                    ((SplashActivity) activity).showNextFragment();
                }
            }
        });
    }

    /**
     * Set button status (enabled/disabled) according to the check box status.
     *
     * @param isButtonEnabled [boolean] Button status according, <b>true</b> if checkbox is checked
     *                        <b>false</b> if not.
     */
    private void setButtonEnabled(boolean isButtonEnabled) {
        buttonAccept.setEnabled(isButtonEnabled);
        buttonAccept.setClickable(isButtonEnabled);
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

        toolbarTitle.setText(getResources().getString(R.string.agreement_actionbar_title));
        toolbarBackButton.setVisibility(View.GONE);
    }
}
