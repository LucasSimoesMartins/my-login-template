package com.lucassimoesmartins.mylogintemplate.View;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.lucassimoesmartins.mylogintemplate.R;

public class SignInActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText edtEmail, edtPassword;
    private Button btnLogin;
    private ImageButton btnGoogle, btnApple, btnFacebook, btnTwitter;
    private TextView txtForgotPassword, txtSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        setUi();

        getSupportActionBar().hide();
    }

    private void setUi(){
        edtEmail = findViewById(R.id.edtEmail);
        edtPassword = findViewById(R.id.edtPassword);
        btnLogin = findViewById(R.id.btnLogin);

        txtForgotPassword = findViewById(R.id.txtForgotPassword);
        txtSignUp = findViewById(R.id.txtSignUp);

        btnGoogle = findViewById(R.id.btnGoogle);
        btnApple = findViewById(R.id.btnApple);
        btnFacebook = findViewById(R.id.btnFacebook);
        btnTwitter = findViewById(R.id.btnTwitter);

        btnLogin.setOnClickListener(this);
        btnGoogle.setOnClickListener(this);
        btnApple.setOnClickListener(this);
        btnFacebook.setOnClickListener(this);
        btnTwitter.setOnClickListener(this);
        txtSignUp.setOnClickListener(this);
        txtForgotPassword.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.btnLogin:
                //Call Login method here
                break;
            case R.id.btnGoogle:
                //Call Google login method here
                break ;
            case R.id.btnApple:
                //Call Google login method here
                break ;
            case R.id.btnFacebook:
                //Call Facebook login method here
                break ;
            case R.id.btnTwitter:
                //Call Facebook login method here
                break ;
            case R.id.txtSignUp:

                Intent intent = new Intent(this, SignUpActivity.class);
                startActivity(intent);

                break;
            case R.id.txtForgotPassword:
                //Call Forgot password method here
                break ;
        }
    }
}