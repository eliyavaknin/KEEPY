package com.keepy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FirebaseUser User = FirebaseAuth.getInstance().getCurrentUser();
        if(User != null){
            openHomepage();
        }
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
    public void openHomepage(){
        Intent Intent =new Intent(MainActivity.this,UserKeeper.class);
        startActivity(Intent);
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