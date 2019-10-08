package com.lucassimoesmartins.mylogintemplate.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.lucassimoesmartins.mylogintemplate.R;

public class ResetPasswordActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText edtEmail;
    private Button btnSend;
    private TextView txtLogin;
    private final String TAG = "ResetPassword debug";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);

        getSupportActionBar().hide();

        setUI();
    }

    private void setUI() {
        edtEmail = findViewById(R.id.edtEmail);
        btnSend = findViewById(R.id.btnSend);
        txtLogin = findViewById(R.id.txtLogin);

        btnSend.setOnClickListener(this);
        txtLogin.setOnClickListener(this);
    }

    private void firebaseAuthForgotPassword() {
        if (edtEmail.getText().toString() != null && !edtEmail.getText().toString().equals("")) {
            FirebaseAuth.getInstance().sendPasswordResetEmail(edtEmail.getText().toString()).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()) {
                        finish();
                    } else {
                        Log.w(TAG, "sendPasswordResetEmail:failure", task.getException());
                        Toast.makeText(ResetPasswordActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        } else {
            Toast.makeText(ResetPasswordActivity.this, "Fill in the email field to continue", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnSend:

                firebaseAuthForgotPassword();
                break;
            case R.id.txtLogin:

                finish();
                break;
        }
    }
}
