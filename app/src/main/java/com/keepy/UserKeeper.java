package com.keepy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class UserKeeper extends AppCompatActivity {
private User mCurrentUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("Users").whereEqualTo("mEmail",FirebaseAuth.getInstance().getCurrentUser().getEmail()).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for(QueryDocumentSnapshot documentSnapshot:task.getResult()){
                        Log.d("user keeper", documentSnapshot.getId()+ "->"+documentSnapshot.getData());
                        mCurrentUser=documentSnapshot.toObject(User.class);
                        updateWelcomeText();
                    }

                } else {
                    Log.e("user keeper","error getting user details" ,task.getException());
                }
            }

        });

        setContentView(R.layout.activity_user_keeper);

        findViewById(R.id.logoutContainer).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                openMainpage();
            }

        });
        findViewById(R.id.profilekeeperContainer).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openEditProfileKeeper();
            }

        });

//        findViewById(R.id.paymentContainer).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                openPaymentPage();
//            }
//        });
    }
    public void updateWelcomeText(){
        TextView welcome = findViewById(R.id.WelcomeUserKeeper);
        String welcomeKeeper = welcome.getText()+mCurrentUser.getmFullName();
        welcome.setText(welcomeKeeper);
        welcome.invalidate();
    }
    public void openMainpage(){

        Intent intent =new Intent(UserKeeper.this,MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
    public void openEditProfileKeeper(){

        Intent intent =new Intent(UserKeeper.this,EditProfileKeepy.class);
        intent.putExtra("CURR_USER",mCurrentUser);
        startActivity(intent);
    }

//    public void openPaymentPage(){
//
//        Intent intent =new Intent(UserKeeper.this,Payment.class);
//        startActivity(intent);
//    }

}


