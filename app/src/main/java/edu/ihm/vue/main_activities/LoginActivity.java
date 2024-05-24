package edu.ihm.vue.main_activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

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
        WebService.getInstance(this).getService().login(new Credentials(username, password)).enqueue(new Callback<Token>() {
            @Override
            public void onResponse(@NonNull Call<Token> call, @NonNull Response<Token> response) {
                if (response.isSuccessful()) {
                    assert response.body() != null;
                    Context context = LoginActivity.this;
                    SharedPreferences sharedPref = context.getSharedPreferences(getString(R.string.preference_file_key), Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPref.edit();
                    editor.putString("token", response.body().getToken());
                    editor.commit();
                    LoginActivity.this.getMe();
                } else {
                    Toast.makeText(LoginActivity.this, "Nom de compte ou mot de passe incorrect!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<Token> call, @NonNull Throwable throwable) {
                Toast.makeText(LoginActivity.this, "Echec !", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void getMe() {
        WebService.getInstance(this).getService().me().enqueue(new Callback<User>() {
            @Override
            public void onResponse(@NonNull Call<User> call, @NonNull Response<User> response) {
                if (response.isSuccessful()) {
                    User connectedUser = response.body();
                    assert connectedUser != null;
                    Toast.makeText(LoginActivity.this, "Bonjour " + connectedUser.getPrenom() + " !", Toast.LENGTH_SHORT).show();
                    Intent intent = connectedUser.getRole() == User.Role.FONCTIONNAIRE
                            ? new Intent(getApplicationContext(),AgentActivity.class)
                            : new Intent(getApplicationContext(),MainActivity.class);
                    intent.putExtra("user", connectedUser);
                    startActivity(intent);
                } else {
                    System.out.println(response);
                    Toast.makeText(LoginActivity.this, "me request failed", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<User> call, @NonNull Throwable throwable) {
                Toast.makeText(LoginActivity.this, "Echec !", Toast.LENGTH_LONG).show();
                throwable.printStackTrace();
            }
        });
    }
}