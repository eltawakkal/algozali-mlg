package com.example.thebestone.sismaalghozali;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

/**
 * Created by thebestone on 20/02/18.
 */

public class ProfileActivity extends AppCompatActivity {

    FloatingActionButton fabCall, fabEmail;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_profile);

        setTitle("");

        init();

        fabCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(ProfileActivity.this, "Comming Soon!", Toast.LENGTH_SHORT).show();
            }
        });

        fabEmail.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("MissingPermission")
            @Override
            public void onClick(View view) {
                Toast.makeText(ProfileActivity.this, "Comming Soon!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void init() {
        fabCall = findViewById(R.id.fabCall);
        fabEmail = findViewById(R.id.fabEmail);
    }
}
