package com.lucassimoesmartins.mylogintemplate.View;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.lucassimoesmartins.mylogintemplate.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class UserInfoFragment extends Fragment implements View.OnClickListener {

    private Button btnNext;
    FragmentTransaction fragmentTransaction;

    public UserInfoFragment() { }

    public static UserInfoFragment newInstance() {
        return new UserInfoFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_user_info, container, false);
    }

    @Override
    public void onViewCreated(final View view, @Nullable final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setUi(view);
    }

    private void setUi(View view){

        fragmentTransaction = getFragmentManager().beginTransaction();

        btnNext = view.findViewById(R.id.btnNext);

        btnNext.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnNext:

                fragmentTransaction.replace(R.id.fragment_container, AdditionalUserInfoFragment.newInstance()).addToBackStack(null).commit();
                break;
        }
    }
}
