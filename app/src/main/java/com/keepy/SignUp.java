package com.keepy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignUp extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private Button mSignUp;
    private EditText mEmail;
    private EditText mPassword;
    private EditText mConfirmPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        mAuth = FirebaseAuth.getInstance();
        mSignUp = findViewById(R.id.confirmSignUp);
        mEmail = findViewById(R.id.email);
        mPassword = findViewById(R.id.password);
        mConfirmPassword = findViewById(R.id.confirm_password);
        mSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(mEmail.getText().toString()) || !Patterns.EMAIL_ADDRESS.matcher(mEmail.getText().toString()).matches()){
                    Toast.makeText(v.getContext(),"Invalid Email",Toast.LENGTH_LONG).show();
                    return;

                }
                if (!mPassword.getText().toString().equals(mConfirmPassword.getText().toString())){
                    Toast.makeText(v.getContext(),"Passwords doesn't match",Toast.LENGTH_LONG).show();
                    return;
                }
                mAuth.createUserWithEmailAndPassword(mEmail.getText().toString(), mPassword.getText().toString())
                        .addOnCompleteListener( new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information
                                    Log.d("signup", "createUserWithEmail:success");
                                    FirebaseUser user = mAuth.getCurrentUser();
                                    //updateUI(user);
                                } else {
                                    // If sign in fails, display a message to the user.
                                    Log.w("signup", "createUserWithEmail:failure", task.getException());
                                    //Toast.makeText(, "Authentication failed.",
//                                            Toast.LENGTH_SHORT).show();
//                                    updateUI(null);
                                }
                            }
                        });
            }
        });

    }
    private void onSignupClick() {
        if (!TextUtils.isEmpty(mEmail.getText()) && Patterns.EMAIL_ADDRESS.matcher(mEmail.getText()).matches()){
            Toast.makeText(SignUp.this,"Invalid Email",Toast.LENGTH_LONG).show();
            return;
        }
    }

//    public void onStart() {
//        super.onStart();
//        // Check if user is signed in (non-null) and update UI accordingly.
//        FirebaseUser currentUser = mAuth.getCurrentUser();
//        if(currentUser != null){
//            currentUser.reload();
//        }
//    }
//    private void registerUser() {
////        String userEmail = email.getText().toString().trim();
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
////        progressBar.setVisibility(View.VISIBLE);
//        String userEmail = "dwedwedwd@gmail.com";
//        String userPassword = "dewdeder343434";
//
//        //register user
//        mAuth.createUserWithEmailAndPassword(userEmail,userPassword)
//                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
//                    @Override
//                    public void onComplete(@NonNull Task<AuthResult> task) {
//                        Log.d("TAG", "New user registration: " + task.isSuccessful());
//
//                        if (!task.isSuccessful()) {
//                            Log.d("TAG", "failed");
//                        } else {
//                            Log.d("TAG", "onComplete: here");
//                            Intent Intent =new Intent(SignUp.this,SignUp.class);
//                            startActivity(Intent);
//                        }
//                    }
//                });
//    }
//
//    public void sign_up(View view) {
    }
