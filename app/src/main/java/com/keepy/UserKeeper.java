package com.keepy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;

public class UserKeeper extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_keeper);
        findViewById(R.id.logoutContainer).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                openMainpage();
            }
        });
//        findViewById(R.id.paymentContainer).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                openPaymentPage();
//            }
//        });
    }
    public void openMainpage(){

        Intent intent =new Intent(UserKeeper.this,MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
//    public void openPaymentPage(){
//
//        Intent intent =new Intent(UserKeeper.this,Payment.class);
//        startActivity(intent);
//    }

}