package com.lucassimoesmartins.mylogintemplate;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText edtEmail, edtPassword;
    Button btnLogin, btnFacebook, btnGoogle;
    TextView txtForgotPassword, txtSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtEmail = findViewById(R.id.edtEmail);
        edtPassword = findViewById(R.id.edtPassword);
        btnLogin = findViewById(R.id.btnLogin);
        btnFacebook = findViewById(R.id.btn_facebook);
        btnGoogle = findViewById(R.id.btn_google);
        txtForgotPassword = findViewById(R.id.txtForgotPassword);
        txtSignUp = findViewById(R.id.txtSignUp);

        btnLogin.setOnClickListener(this);
        btnFacebook.setOnClickListener(this);
        btnGoogle.setOnClickListener(this);
        txtForgotPassword.setOnClickListener(this);
        txtSignUp.setOnClickListener(this);

        getSupportActionBar().hide();
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.btnLogin:
                //Call Login method here
                break;
            case R.id.btn_facebook:
                //Call Facebook login method here
                break ;
            case R.id.btn_google:
                //Call Google login method here
                break ;
            case R.id.txtForgotPassword:
                //Call Forgot password method here
                break ;
            case R.id.txtSignUp:

                Intent intent = new Intent(this, SignUpActivity.class);
                startActivity(intent);

                break;
        }
    }
}
