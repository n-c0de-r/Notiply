package com.imi_gma.notiply.Views;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.imi_gma.notiply.Controls.Persistence;
import com.imi_gma.notiply.R;

public class MainActivity extends AppCompatActivity {
    Persistence pers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnPageHost = findViewById(R.id.btnPageHost);
        Button btnPageJoin = findViewById(R.id.btnPageJoin);
        Button btnSaveUsername = findViewById(R.id.btnSaveUsername);

        pers = Persistence.getInstance();
        SharedPreferences prefs = getSharedPreferences("myNotiplySettings", MODE_PRIVATE);

        EditText nameInput = findViewById(R.id.editUserName);

        nameInput.setText(pers.getUserData(prefs));

        btnPageHost.setOnClickListener(view -> {
            Intent intent = new Intent(this, DrawingPage.class);
            startActivity(intent);
        });

        btnPageJoin.setOnClickListener(view -> {
            Intent intent = new Intent(this, FindPage.class);
            startActivity(intent);
        });

        btnSaveUsername.setOnClickListener(view -> {
            String name = nameInput.getText().toString();
            pers.saveUserData(prefs, name);
            Toast.makeText(this, "Saved new username: \"" + name + "\"",
                    Toast.LENGTH_SHORT).show();
        });
    }
}