package com.example.thebestone.sismaalghozali;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by thebestone on 20/02/18.
 */

public class LoginActivity extends AppCompatActivity {

    EditText editUser, editPass;
    Button btnLogin;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        init();

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = editUser.getText().toString();
                String pass = editPass.getText().toString();

                if (user.equals("ayum") && pass.equals("ayum123")) {
                    startActivity(new Intent(LoginActivity.this, Donatur.class));
                    finish();
                } else {
                    Toast.makeText(LoginActivity.this, "Afwan, Pengguna atau sandi tidak dikenal", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void init() {
        editUser = findViewById(R.id.editUserName);
        editPass = findViewById(R.id.editPassword);

        btnLogin = findViewById(R.id.btnLogin);
    }
}
