package com.example.thebestone.sismaalghozali;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by thebestone on 21/02/18.
 */

public class SelectStatus extends AppCompatActivity {

    Button btnDonatur, btnJamaah;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_selectstatus);

        init();

        btnJamaah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SelectStatus.this, AktifitasUtama.class));
                finish();
            }
        });

        btnDonatur.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SelectStatus.this, LoginActivity.class));
                finish();
            }
        });
    }

    private void init() {
        btnJamaah = findViewById(R.id.btnJamaah);
        btnDonatur = findViewById(R.id.btnDonatur);
    }
}
