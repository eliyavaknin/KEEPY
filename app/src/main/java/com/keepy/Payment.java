package com.example.keepy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LogIn extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        View view = findViewById(R.id.editText1);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openloginpage();
            }

        });

    }

    public void openloginpage() {
        Intent Intent = new Intent(LogIn.this, ForgotPassword.class);
        startActivity(Intent);
    }
}