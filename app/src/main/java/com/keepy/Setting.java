package com.keepy;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

public class Setting extends AppCompatActivity {

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);


        findViewById(R.id.changePsBtn).setOnClickListener(view ->
                opensignuppage());


    }


    public void opensignuppage() {
        Intent Intent = new Intent(Setting.this,ResetPassword.class);
        startActivity(Intent);
    }
}