package com.keepy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button= findViewById(R.id.Buttonlogin);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openloginpage();
            }

        });
        findViewById(R.id.ButtonSignUp).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {opensignuppage();}
        }); ;

    }
    public void openloginpage(){
        Intent Intent =new Intent(MainActivity.this,LogIn.class);
        startActivity(Intent);
    }
    public void opensignuppage(){
        Intent Intent =new Intent(MainActivity.this,SignUp.class);
        startActivity(Intent);
    }


}