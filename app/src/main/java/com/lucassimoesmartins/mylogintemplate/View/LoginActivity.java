package com.lucassimoesmartins.mylogintemplate.View;

import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.GoogleAuthProvider;
import com.lucassimoesmartins.mylogintemplate.R;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText edtEmail, edtPassword;
    private Button btnLogin;
    private TextView txtForgotPassword, txtSignUp;
    private LoginButton btnFacebook;
    private final String TAG = "LoginActivity debug";
    private CallbackManager callbackManager;
    private FirebaseAuth firebaseAuth;
    private ImageButton btnGoogle, btnCustomFacebook;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        setUI();
        firebaseAuth = FirebaseAuth.getInstance();

        getSupportActionBar().hide();
    }

    private void setUI(){
        edtEmail = findViewById(R.id.edtEmail);
        edtPassword = findViewById(R.id.edtPassword);
        btnLogin = findViewById(R.id.btnLogin);

        txtForgotPassword = findViewById(R.id.txtForgotPassword);
        txtSignUp = findViewById(R.id.txtSignUp);

        btnGoogle = findViewById(R.id.btnGoogle);
        btnCustomFacebook = findViewById(R.id.btnCustomFacebook);

        btnLogin.setOnClickListener(this);
        btnGoogle.setOnClickListener(this);
        btnCustomFacebook.setOnClickListener(this);
        txtSignUp.setOnClickListener(this);
        txtForgotPassword.setOnClickListener(this);

        configureFacebookLogin();
    }

    private void configureFacebookLogin() {
        callbackManager = CallbackManager.Factory.create();
        btnFacebook = findViewById(R.id.btnFacebook);
        btnFacebook.setPermissions("email", "public_profile");

        btnFacebook.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                handleFacebookAccessToken(loginResult.getAccessToken());
            }

            @Override
            public void onCancel() {
                Log.d(TAG, "facebook:onCancel");
            }

            @Override
            public void onError(FacebookException error) {
                Log.d(TAG, "facebook:onError", error);
            }
        });
    }

    private void handleFacebookAccessToken(AccessToken token) {

        AuthCredential credential = FacebookAuthProvider.getCredential(token.getToken());
        firebaseAuth.signInWithCredential(credential).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
            if (task.isSuccessful()) {
                navigateToMain();
            } else {
                LoginManager.getInstance().logOut();
                Log.w(TAG, "signInWithCredential:failure", task.getException());
                Toast.makeText(LoginActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
            }
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        if (requestCode == 1) {
            //Google login
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);

            try {

                GoogleSignInAccount account = task.getResult(ApiException.class);
                firebaseAuthWithGoogle(account);
            } catch (ApiException e) {
                Log.w(TAG, "Google signInResult:failed code=" + e.getStatusCode());
            }
        } else {
            //Facebook login
            callbackManager.onActivityResult(requestCode, resultCode, data);
        }
    }

    private void firebaseAuthWithGoogle(GoogleSignInAccount acct) {

        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);

        firebaseAuth.signInWithCredential(credential).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful()) {

                    navigateToMain();
                } else {
                    Log.w(TAG, "Google signInWithCredential:failure", task.getException());
                    Toast.makeText(LoginActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void firebaseAuthWithEmail() {
        if (edtEmail.getText().toString() != null && !edtEmail.getText().toString().equals("") && edtPassword.getText().toString() != null && !edtPassword.getText().toString().equals("")) {
            firebaseAuth.signInWithEmailAndPassword(edtEmail.getText().toString(), edtPassword.getText().toString()).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {

                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        navigateToMain();
                    } else {
                        Log.w(TAG, "signInWithEmail:failure", task.getException());
                        Toast.makeText(LoginActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        } else {
            Toast.makeText(LoginActivity.this, getResources().getString(R.string.fill_in_all_fields_to_continue), Toast.LENGTH_SHORT).show();
        }
    }

    private void navigateToMain(){
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {

        Intent intent;

        switch (v.getId()){
            case R.id.btnLogin:

                firebaseAuthWithEmail();
                break;
            case R.id.btnGoogle:

                GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestIdToken(getString(R.string.default_web_client_id)).requestEmail().build();
                GoogleSignInClient gsc = GoogleSignIn.getClient(this, gso);

                intent = gsc.getSignInIntent();
                startActivityForResult(intent, 1);

                break;
            case R.id.btnCustomFacebook:

                btnFacebook.performClick();

                break;
            case R.id.txtSignUp:

                intent = new Intent(this, SignUpActivity.class);
                startActivity(intent);
                break;
            case R.id.txtForgotPassword:

                intent = new Intent(this, ResetPasswordActivity.class);
                startActivity(intent);
                break;
        }
    }
}
