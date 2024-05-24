package edu.ihm.vue.main_activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

import edu.ihm.vue.R;
import edu.ihm.vue.models.User;
import edu.ihm.vue.models.web_service_models.Credentials;
import edu.ihm.vue.models.web_service_models.Token;
import edu.ihm.vue.web_service.WebService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    Button connexion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        connexion = findViewById(R.id.connecter);
        connexion.setOnClickListener(v -> {
            TextView username = findViewById(R.id.email);
            TextView password = findViewById(R.id.password);
            connect(username.getText().toString(), password.getText().toString());
        });
    }

    private void connect(String username, String password) {
        WebService.getInstance().getService().login(new Credentials(username, password)).enqueue(new Callback<Token>() {
            @Override
            public void onResponse(@NonNull Call<Token> call, @NonNull Response<Token> response) {
                if (response.isSuccessful()) {
                    assert response.body() != null;
                    System.out.println(response.body().getToken());
                } else {
                    Toast.makeText(LoginActivity.this, "Nom de compte ou mot de passe incorrect!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<Token> call, @NonNull Throwable throwable) {
                Toast.makeText(LoginActivity.this, "Failed !", Toast.LENGTH_LONG).show();
            }
        });

        if (username.equals("user") && password.equals("user")) {
            Intent intent = new Intent(getApplicationContext(),MainActivity.class);
            intent.putExtra("user",(Parcelable) new User("1","Nougaret","Adrien", User.Role.PARTICULIER));
            startActivity(intent);
        } else if (username.equals("admin") && password.equals("admin")) {
            Intent intent = new Intent(getApplicationContext(),AgentActivity.class);
            intent.putExtra("user",(Parcelable) new User("2","Hauchart","Lucas", User.Role.FONCTIONNAIRE));
            startActivity(intent);
        } else {
            Toast.makeText(LoginActivity.this, "Nom de compte ou mot de passe incorrect!", Toast.LENGTH_SHORT).show();
        }
    }

    private void getMe(String token) {

    }
}