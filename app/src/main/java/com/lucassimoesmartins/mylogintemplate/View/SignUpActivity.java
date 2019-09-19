package com.lucassimoesmartins.mylogintemplate.View;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.lucassimoesmartins.mylogintemplate.R;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView txtLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        getSupportActionBar().hide();

        setUi();
        showInitialFragment();

    }

    private void setUi(){
        txtLogin = findViewById(R.id.txtLogin);

        txtLogin.setOnClickListener(this);
    }

    private void showInitialFragment() {
        UserInfoFragment userInfoFragment = UserInfoFragment.newInstance();

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        fragmentTransaction.add(R.id.fragment_container, userInfoFragment).commit();
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.txtLogin:
                finish();
                break;
        }
    }
}
