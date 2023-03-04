package com.keepy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.keepy.models.User;


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
        mIsClientBtn.setOnClickListener(v -> {
            toggleIsClient();
            if (mIsKeeper) {
                toggleIsKeeper();
            }
            if (misClientAndKeeper) {
                toggleIsClientAndKeeper();
            }

        });

        mIsKeeperBtn = findViewById(R.id.isKeeper);
        mIsKeeperBtn.setOnClickListener(v -> {
            toggleIsKeeper();
            if (mIsClient) {
                toggleIsClient();
            }
            if (misClientAndKeeper) {
                toggleIsClientAndKeeper();
            }
        });

        mIsKeeperAndClientBtn = findViewById(R.id.isClientAndKeeper);
        mIsKeeperAndClientBtn.setOnClickListener(v -> {
            toggleIsClientAndKeeper();
            if (mIsKeeper) {
                toggleIsKeeper();
            }
            if (mIsClient) {
                toggleIsClient();
            }
        });

        mSignUp.setOnClickListener(v -> {
            if (TextUtils.isEmpty(mEmail.getText().toString()) || !Patterns.EMAIL_ADDRESS.matcher(mEmail.getText().toString()).matches()) {
                Toast.makeText(v.getContext(), "Invalid Email", Toast.LENGTH_LONG).show();
                return;

            }
            if (!mPassword.getText().toString().equals(mConfirmPassword.getText().toString())) {
                Toast.makeText(v.getContext(), "Passwords doesn't match", Toast.LENGTH_LONG).show();
                return;
            }
            if (!mIsClient && !mIsKeeper && !misClientAndKeeper) {
                Toast.makeText(v.getContext(), "You need to select at least one - Client or Keeper", Toast.LENGTH_LONG).show();
                return;
            }
            mAuth.createUserWithEmailAndPassword(mEmail.getText().toString(), mPassword.getText().toString())
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("signup", "createUserWithEmail:success");
                            User newUser = new User(mEmail.getText().toString(),
                                    mIsClient || misClientAndKeeper, mIsKeeper || misClientAndKeeper
                            );
                            db.collection("Users")
                                    .add(newUser)
                                    .addOnSuccessListener(documentReference -> {
                                        Log.d("signup", "DocumentSnapshot added with ID: " + documentReference.getId());
                                        openMainpage(newUser);
                                    })
                                    .addOnFailureListener(e -> Log.w("signup", "Error adding user", e));
                        }
                    });
        });


    }

    public void openMainpage(User newUser) {
        Intent intent = new Intent(SignUp.this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("user", newUser);
        startActivity(intent);
    }

    private void toggleIsClient() {
        mIsClient = !mIsClient;
        if (mIsClient) {
            mIsClientBtn.setBackground(getDrawable(R.drawable.blue_button_background));
            mIsClientBtn.setTextColor(getResources().getColor(R.color.white));

            return;
        }
        mIsClientBtn.setBackground(getDrawable(R.drawable.grey_button_background));
        mIsClientBtn.setTextColor(getResources().getColor(R.color.black));

    }

    private void toggleIsKeeper() {
        mIsKeeper = !mIsKeeper;
        if (mIsKeeper) {
            mIsKeeperBtn.setBackground(getDrawable(R.drawable.blue_button_background));
            mIsKeeperBtn.setTextColor(getResources().getColor(R.color.white));

            return;
        }
        mIsKeeperBtn.setBackground(getDrawable(R.drawable.grey_button_background));
        mIsKeeperBtn.setTextColor(getResources().getColor(R.color.black));

    }

    private void toggleIsClientAndKeeper() {
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



