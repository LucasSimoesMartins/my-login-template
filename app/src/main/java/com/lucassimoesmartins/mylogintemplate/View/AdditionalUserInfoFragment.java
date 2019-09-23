package com.lucassimoesmartins.mylogintemplate.View;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.lucassimoesmartins.mylogintemplate.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class AdditionalUserInfoFragment extends Fragment implements View.OnClickListener {

    private Button btnback, btnFinish;

    public AdditionalUserInfoFragment() { }

    public static AdditionalUserInfoFragment newInstance() {
        return new AdditionalUserInfoFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_additional_user_info, container, false);
    }

    @Override
    public void onViewCreated(final View view, @Nullable final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setUi(view);
    }

    private void setUi(View view){

        btnback = view.findViewById(R.id.btnBack);
        btnFinish = view.findViewById(R.id.btnFinish);

        btnback.setOnClickListener(this);
        btnFinish.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.btnBack:

                getFragmentManager().popBackStack();
                break;
            case R.id.btnFinish:
                break;
        }
    }
}
