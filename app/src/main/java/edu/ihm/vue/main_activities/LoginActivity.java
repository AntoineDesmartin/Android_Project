package edu.ihm.vue.main_activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import edu.ihm.vue.R;
import edu.ihm.vue.models.User;

public class LoginActivity extends AppCompatActivity {
    Button connexion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        connexion = findViewById(R.id.connecter);
        connexion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView username = (TextView) findViewById(R.id.email);
                TextView password = (TextView) findViewById(R.id.password);
                connect(username.getText().toString(), password.getText().toString());
            }
        });
    }

    private void connect(String username, String password) {
        if (username.equals("user") && password.equals("user")) {
            Intent intent = new Intent(getApplicationContext(),MainActivity.class);
            intent.putExtra("user",(Parcelable) new User("1","Nougaret","Adrien", User.role.PARTICULIER));
            startActivity(intent);
        } else if (username.equals("admin") && password.equals("admin")) {
            Intent intent = new Intent(getApplicationContext(),AgentActivity.class);
            intent.putExtra("user",(Parcelable) new User("2","Hauchart","Lucas", User.role.FONCTIONNAIRE));
            startActivity(intent);
        } else {
            Toast.makeText(LoginActivity.this, "Nom de compte ou mot de passe incorrect!", Toast.LENGTH_SHORT).show();
        }
    }

}