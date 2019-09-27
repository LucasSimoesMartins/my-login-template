package com.lucassimoesmartins.mylogintemplate.View;

import android.content.Intent;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.lucassimoesmartins.mylogintemplate.R;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        getSupportActionBar().hide();

        openApp();
    }

    private void openApp() {

        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        final Class cls = currentUser != null ? MainActivity.class : LoginActivity.class;

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this, cls);
                startActivity(intent);
                finish();
            }
        },2000);
    }
}
