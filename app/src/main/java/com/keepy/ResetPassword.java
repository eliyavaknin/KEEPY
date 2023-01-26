package com.keepy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ResetPassword extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);

        Button button = findViewById(R.id.pagekeeper);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openPagekeeper();
            }

        });
        findViewById(R.id.pageclient).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {openPageclient();}

        });
    }

    public void openPagekeeper() {
        Intent Intent = new Intent(ResetPassword.this, UserKeeper.class);
        startActivity(Intent);
    }

    public void openPageclient() {
        Intent Intent = new Intent(ResetPassword.this, UserClient.class);
        startActivity(Intent);
    }

}