package com.keepy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignUp extends AppCompatActivity {
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        mAuth = FirebaseAuth.getInstance();


    }
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            currentUser.reload();
        }
    }
    private void registerUser() {
//        String userEmail = email.getText().toString().trim();
//        String userPassword = password.getText().toString().trim();
//
//        if (TextUtils.isEmpty(userEmail)) {
//            showToast("Enter email address!");
//            return;
//        }
//
//        if(TextUtils.isEmpty(userPassword)){
//            showToast("Enter Password!");
//            return;
//        }
//
//        if(userPassword.length() < 6){
//            showToast("Password too short, enter minimum 6 characters");
//            return;x
//        }
//
//        progressBar.setVisibility(View.VISIBLE);
        String userEmail = "dwedwedwd@gmail.com";
        String userPassword = "dewdeder343434";

        //register user
        mAuth.createUserWithEmailAndPassword(userEmail,userPassword)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d("TAG", "New user registration: " + task.isSuccessful());

                        if (!task.isSuccessful()) {
                            Log.d("TAG", "failed");
                        } else {
                            Log.d("TAG", "onComplete: here");
                            Intent Intent =new Intent(SignUp.this,SignUp.class);
                            startActivity(Intent);
                        }
                    }
                });
    }

    public void sign_up(View view) {
    }
}