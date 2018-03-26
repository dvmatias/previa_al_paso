package fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.previaalpaso.matiasdelvecchio.previaalpaso.R;

import adapters.AdapterSplashPager;

/**
 * Created by cesar.delvecchio on 21/03/2018
 */

public class FragmentAgreement extends Fragment {
    /**
     * TAG.
     */
    private static final String TAG = FragmentAgreement.class.getSimpleName();

    /**
     * Check box.
     */
    private CheckBox checkBoxAgreement;

    /**
     * Button.
     */
    private Button buttonAccept;

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
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.fragment_agreement, container, false);

        checkBoxAgreement = rootView.findViewById(R.id.checkbox_agreement);
        buttonAccept = rootView.findViewById(R.id.button_accept);

        setupCheckbox();
        setupButton();
        setupActionBar(rootView);

        return rootView;
    }

    /**
     * Setuo check box agreement.
     */
    private void setupCheckbox() {
        checkBoxAgreement.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                setButtonEnabled(isChecked);
            }
        });
    }

    /**
     * Setup button to display next fragment.
     */
    private void setupButton() {
        buttonAccept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO show FragmentLogIn
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
     *
     */
    private void setupActionBar(View rootView) {
        Toolbar toolbar = rootView.findViewById(R.id.toolbar);
        TextView toolbarTitle = toolbar.findViewById(R.id.toolbar_title);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        ActionBar actionBar = ((AppCompatActivity)getActivity()).getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayShowTitleEnabled(false);
            actionBar.setHomeButtonEnabled(false);
        }
        toolbarTitle.setText(getResources().getString(R.string.agreement_actionbar_title));
    }
}
