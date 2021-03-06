package fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.previaalpaso.matiasdelvecchio.previaalpaso.R;

/**
 * Created by cesar.delvecchio on 21/03/2018.
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

        // TODO declare and set views inside fragment.

        return rootView;
    }
}
