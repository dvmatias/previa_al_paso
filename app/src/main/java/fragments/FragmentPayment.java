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

public class FragmentPayment extends Fragment {
    /**
     * TAG.
     */
    private static final String TAG = FragmentPayment.class.getSimpleName();

    /**
     * New instance for {@link FragmentAgreement}
     *
     * @return [FragmentAgreement] Instance.
     */
    public static FragmentPayment newInstance() {
        FragmentPayment fragment = new FragmentPayment();

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
                R.layout.fragment_payment, container, false);

        // TODO declare and set views inside fragment.

        return rootView;
    }
}
