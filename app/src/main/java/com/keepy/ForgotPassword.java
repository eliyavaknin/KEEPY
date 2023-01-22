package com.keepy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ForgotPassword extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        View view = findViewById(R.id.editText1);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openloginpage();
            }

        });
        Button button= findViewById(R.id.ButtonReset);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openResetpage();
            }

        });

    }

    public void openloginpage() {
        Intent Intent = new Intent(ForgotPassword.this, LogIn.class);
        startActivity(Intent);
    }

    public void openResetpage() {
        Intent Intent = new Intent(ForgotPassword.this, EditProfile.class);
        startActivity(Intent);
    }
}