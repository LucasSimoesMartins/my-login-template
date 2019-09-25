package com.lucassimoesmartins.mylogintemplate.View;

import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.GoogleAuthProvider;
import com.lucassimoesmartins.mylogintemplate.R;

public class SignInActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText edtEmail, edtPassword;
    private Button btnLogin;
    private ImageButton btnGoogle, btnFacebook, btnTwitter;
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
        btnFacebook = findViewById(R.id.btnFacebook);
        btnTwitter = findViewById(R.id.btnTwitter);

        btnLogin.setOnClickListener(this);
        btnGoogle.setOnClickListener(this);
        btnFacebook.setOnClickListener(this);
        btnTwitter.setOnClickListener(this);
        txtSignUp.setOnClickListener(this);
        txtForgotPassword.setOnClickListener(this);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        if (requestCode == 1) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);

            try {

                GoogleSignInAccount account = task.getResult(ApiException.class);
                firebaseAuthWithGoogle(account);
            } catch (ApiException e) {
                // The ApiException status code indicates the detailed failure reason.
                //Log.w(TAG, "signInResult:failed code=" + e.getStatusCode());
            }
        }
    }

    private void firebaseAuthWithGoogle(GoogleSignInAccount acct) {

        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);

        FirebaseAuth.getInstance().signInWithCredential(credential).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful()) {

                    navigateToMain();
                } else {
                    // If sign in fails, display a message to the user.
                    // Log.w(TAG, "signInWithCredential:failure", task.getException());
                }
            }
        });
    }

    private void navigateToMain(){
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.btnLogin:
                //Call Login method here

                break;
            case R.id.btnGoogle:

                GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestIdToken(getString(R.string.default_web_client_id)).requestEmail().build();
                GoogleSignInClient gsc = GoogleSignIn.getClient(this, gso);

                Intent signInIntent = gsc.getSignInIntent();
                startActivityForResult(signInIntent, 1);

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
