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

/*
GREEN TRACK
Membres du groupe : Antoine DESMARTIN,Radhi ALOULOU,Jean Paul ASSIMPAH, Logan LUCAS

---Liste de toutes les dépendances :
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("androidx.activity:activity:1.8.0")
    implementation("com.google.android.gms:play-services-maps:18.1.0")
    implementation("com.google.android.gms:play-services-location:21.2.0")
    implementation ("com.fasterxml.jackson.core:jackson-core:2.12.3")
    implementation ("com.fasterxml.jackson.core:jackson-databind:2.12.3")
    implementation ("com.fasterxml.jackson.core:jackson-annotations:2.12.3")
    implementation("com.squareup.retrofit2:retrofit:2.11.0")
    implementation("com.squareup.retrofit2:converter-jackson:2.11.0")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

---Implementation des Patterns:

   --Le pattern Factory implémenté est Factory Method: (package: models)
   -Fabrique générique:SignalementFacotry
   -Fabrique de signalement:NormalSignalementFactory,UrgentSignalementFactory

   --Le pattern MVC est implementé dans le package agent_equipements_view:
   L'activité est responsable de gérer les équipments de l'agent à transporter
   pour résoudre un incident

   --Les differents types de signalements ainsi que que le user implemente Parcelable:
   Le User est une varaible unique
   La liste des signalements dans notre choix d'implementation (futur) n'est pas identique dans tout le programme
   et diffère selon l'onglet de l'utilisation ( signalements généraux ou mes signalements => differents requêtes)

   --Le pattern Sigleton a été implmenté dans le WebService (package: web_service) : qui sera unique et utilisé dans toute l'application
   (Mais aussi pour réaliser notre démo avant la réalisation de notre produit final
   nous avons utilisé une classe de Mocks singleton pour synchroniser les différents informations dans notre programme)

   --Le pattern Observer/Observable a été implementé dans un filtre de niveau minimal dans l'affichage
   de la liste des signalements pour l'agent qui filtrera les incident selon le niveau d'importance
   (package: agent_signalement_view)
   Observable : NiveauMinimalSensor
   Observer:NiveauFilter

   --La sauvegarde d'une donnée est implementé dans le LoginActivity(package:main_activites), cette sauvegarde permet
   de stocker un token utilisé dans la connexion au WebService permettant ainsi à l'utilisatuer de se connecter
   sans re-saisir son identifiant et mot de passe à chaque ouverture de l'application


 */
public class LoginActivity extends AppCompatActivity {
    Button connexion;
    SharedPreferences sharedPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AgentActivity.isLogged=false;
        setContentView(R.layout.activity_login);

        sharedPref = this.getSharedPreferences(getString(R.string.preference_file_key), Context.MODE_PRIVATE);
        String token = this.sharedPref.getString("token", null);
        if (token != null)
            this.getMe();

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
                    SharedPreferences.Editor editor = sharedPref.edit();
                    editor.putString("token", response.body().getToken());
                    editor.commit();
                    LoginActivity.this.getMe();
                } else {
                    Toast.makeText(LoginActivity.this, "Nom de compte ou mot de passe incorrect !", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<Token> call, @NonNull Throwable throwable) {
                Toast.makeText(LoginActivity.this, "Echec connection !", Toast.LENGTH_LONG).show();
                throwable.printStackTrace();
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
                    Toast.makeText(LoginActivity.this, "Token stocké invalide", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<User> call, @NonNull Throwable throwable) {
                Toast.makeText(LoginActivity.this, "Echec connection !", Toast.LENGTH_LONG).show();
                throwable.printStackTrace();
            }
        });
    }
}