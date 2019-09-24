package com.lucassimoesmartins.mylogintemplate.View;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.lucassimoesmartins.mylogintemplate.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnSignOut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setUi();
    }

    private void setUi(){
        btnSignOut = findViewById(R.id.btnSignOut);

        btnSignOut.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnSignOut:
                //Sign out method here
                break;
        }
    }
}
