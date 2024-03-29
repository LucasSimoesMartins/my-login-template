package com.lucassimoesmartins.mylogintemplate.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.login.LoginManager;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.lucassimoesmartins.mylogintemplate.R;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText edtEmail, edtPassword;
    private Button btnFinish;
    private TextView txtLogin;
    private FirebaseAuth firebaseAuth;
    private final String TAG = "SignUpActivity debug";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        getSupportActionBar().hide();
        firebaseAuth = FirebaseAuth.getInstance();

        setUI();

    }

    private void setUI(){
        edtEmail = findViewById(R.id.edtEmail);
        edtPassword = findViewById(R.id.edtPassword);
        btnFinish = findViewById(R.id.btnFinish);
        txtLogin = findViewById(R.id.txtLogin);

        btnFinish.setOnClickListener(this);
        txtLogin.setOnClickListener(this);
    }

    private void navigateToMain(){
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    private void createUserWithEmailAndPassword() {
        if (edtEmail.getText().toString() != null && !edtEmail.getText().toString().equals("") && edtPassword.getText().toString() != null && !edtPassword.getText().toString().equals("")) {
            firebaseAuth.createUserWithEmailAndPassword(edtEmail.getText().toString(), edtPassword.getText().toString()).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {

                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        firebaseAuth.getCurrentUser().sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    logoutUser();
                                    Toast.makeText(SignUpActivity.this, getResources().getString(R.string.check_your_email_to_activate_your_account), Toast.LENGTH_SHORT).show();
                                    finish();
                                }
                            }
                        });
                    } else {
                        Log.w(TAG, "createUserWithEmail:failure", task.getException());
                        Toast.makeText(SignUpActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        } else {
            Toast.makeText(SignUpActivity.this, getResources().getString(R.string.fill_in_all_fields_to_continue), Toast.LENGTH_SHORT).show();
        }
    }

    private void logoutUser() {
        GoogleSignInOptions.Builder gsoBuilder = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail();
        GoogleSignIn.getClient(this, gsoBuilder.build()).signOut();
        FirebaseAuth.getInstance().signOut();
        LoginManager.getInstance().logOut();
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.btnFinish:

                createUserWithEmailAndPassword();
                break;
            case R.id.txtLogin:

                finish();
                break;
        }
    }
}
