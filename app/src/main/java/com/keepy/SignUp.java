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
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;


public class SignUp extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private Button mSignUp;
    private EditText mEmail;
    private EditText mPassword;
    private EditText mConfirmPassword;
    private Button mIsClientBtn;
    private Button mIsKeeperBtn;
    private Button mIsKeeperAndClientBtn;


    private boolean mIsClient = false;
    private boolean mIsKeeper = false;
    private boolean misClientAndKeeper = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        mAuth = FirebaseAuth.getInstance();
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        mSignUp = findViewById(R.id.confirmSignUp);
        mEmail = findViewById(R.id.email);
        mPassword = findViewById(R.id.password);
        mConfirmPassword = findViewById(R.id.confirm_password);
        mIsClientBtn = findViewById(R.id.isClient);
        mIsClientBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleIsClient();
                if(mIsKeeper){
                    toggleIsKeeper();
                }
                if(misClientAndKeeper){
                    toggleIsClientAndKeeper();
                }

            }
        });

        mIsKeeperBtn = findViewById(R.id.isKeeper);
        mIsKeeperBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               toggleIsKeeper();
                if(mIsClient){
                    toggleIsClient();
                }
                if(misClientAndKeeper){
                    toggleIsClientAndKeeper();
                }
            }
        });

        mIsKeeperAndClientBtn = findViewById(R.id.isClientAndKeeper);
        mIsKeeperAndClientBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleIsClientAndKeeper();
                if(mIsKeeper){
                    toggleIsKeeper();
                }
                if(mIsClient){
                    toggleIsClient();
                }
            }
        });

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
                if (!mIsClient && !mIsKeeper && !misClientAndKeeper) {
                    Toast.makeText(v.getContext(),"You need to select at least one - Client or Keeper",Toast.LENGTH_LONG).show();
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
                                    User newUser = new User(mEmail.getText().toString(),mIsClient || misClientAndKeeper,mIsKeeper || misClientAndKeeper);
//                                    FirebaseDatabase.getInstance().getReference("Users").child(user.getUid()).setValue(newUser).addOnCompleteListener(new OnCompleteListener<Void>() {
//
//                                        @Override
//                                        public void onComplete(@NonNull Task<Void> task) {
//                                            if (task.isSuccessful()) {
//                                                Log.d("signup", "updateUses:success");
//
//                                            }
//                                            else{
//                                                Log.d("signup", "updateUses:failed");
//
//                                            }
//                                        }
//                                    });
                                    db.collection("Users")
                                            .add(newUser)
                                            .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                                @Override
                                                public void onSuccess(DocumentReference documentReference) {
                                                    Log.d("signup", "DocumentSnapshot added with ID: " + documentReference.getId());
                                                    openMainpage();
                                                }
                                            })
                                            .addOnFailureListener(new OnFailureListener() {
                                                @Override
                                                public void onFailure(@NonNull Exception e) {
                                                    Log.w("signup", "Error adding user", e);
                                                }
                                            });

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
    public void openMainpage(){

        Intent intent =new Intent(SignUp.this,MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    private void toggleIsClient(){
        mIsClient = !mIsClient;
        if (mIsClient) {
            mIsClientBtn.setBackground(getDrawable(R.drawable.blue_button_background));
            mIsClientBtn.setTextColor(getResources().getColor(R.color.white));

            return;
        }
        mIsClientBtn.setBackground(getDrawable(R.drawable.grey_button_background));
        mIsClientBtn.setTextColor(getResources().getColor(R.color.black));

    }

    private void toggleIsKeeper(){
        mIsKeeper = !mIsKeeper;
        if (mIsKeeper) {
            mIsKeeperBtn.setBackground(getDrawable(R.drawable.blue_button_background));
            mIsKeeperBtn.setTextColor(getResources().getColor(R.color.white));

            return;
        }
        mIsKeeperBtn.setBackground(getDrawable(R.drawable.grey_button_background));
        mIsKeeperBtn.setTextColor(getResources().getColor(R.color.black));

    }

    private void toggleIsClientAndKeeper(){
        misClientAndKeeper = !misClientAndKeeper;
        if (misClientAndKeeper) {
            mIsKeeperAndClientBtn.setBackground(getDrawable(R.drawable.blue_button_background));
            mIsKeeperAndClientBtn.setTextColor(getResources().getColor(R.color.white));

            return;
        }
        mIsKeeperAndClientBtn.setBackground(getDrawable(R.drawable.grey_button_background));
        mIsKeeperAndClientBtn.setTextColor(getResources().getColor(R.color.black));

    }
}



