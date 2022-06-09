package com.imi_gma.notiply.Views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.imi_gma.notiply.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FloatingActionButton btnPaintPage = findViewById(R.id.btnPaintPage);
        Button btnPageJoin = findViewById(R.id.btnPageJoin);

        btnPaintPage.setOnClickListener(view -> {
            Intent intent = new Intent(this, DrawingPage.class);
            startActivity(intent);
        });

        btnPageJoin.setOnClickListener(view -> {
            Intent intent = new Intent(this, FindPage.class);
            startActivity(intent);
        });
    }
}