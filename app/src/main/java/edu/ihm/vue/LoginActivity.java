package edu.ihm.vue;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button mairie = findViewById(R.id.mairie);
        Button particulier = findViewById(R.id.particulier);
        mairie.setOnClickListener((click)->{
            Intent intent = new Intent(getApplicationContext(), AgentActivity.class);
            startActivity(intent);
        });
        particulier.setOnClickListener((click)->{
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
        });

    }
}