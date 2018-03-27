package fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.previaalpaso.matiasdelvecchio.previaalpaso.R;

/**
 * Created by cesar.delvecchio on 21/03/2018
 */

public class FragmentLogIn extends Fragment {
    /**
     * TAG.
     */
    private static final String TAG = FragmentLogIn.class.getSimpleName();

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
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.fragment_login, container, false);

        setupActionBar(rootView);

        return rootView;
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
                // TODO Back to previous fragment
            }
        });
    }
}
