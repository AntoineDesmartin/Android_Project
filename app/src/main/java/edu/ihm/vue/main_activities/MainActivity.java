package edu.ihm.vue.main_activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import edu.ihm.vue.AccountFragment;
import edu.ihm.vue.mocks.Signalements;
import edu.ihm.vue.models.Signalement;
import edu.ihm.vue.models.User;
import edu.ihm.vue.R;
import edu.ihm.vue.SignalementActivity;
import edu.ihm.vue.user_mes_signalements_view.UserMesSignalementsDisplayFragment;
import edu.ihm.vue.user_signalements_view.UserSignalementsDisplayFragment;


/*
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

 */
public class MainActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;

    public static User user;
    public static List<Signalement> Signalements;
    public static List<Signalement> mesSignalements;
    public static Signalements mock;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        user=getIntent().getParcelableExtra("user");
        Log.d("Radhi",user.getId());
        mock= edu.ihm.vue.mocks.Signalements.getInstance(getApplicationContext());
        Signalements=new ArrayList<>(mock.signalementsMock);
        mesSignalements = Signalements.stream().filter(t->t.getAuteur().equals(user.getId())).collect(Collectors.toList());
        setContentView(R.layout.activity_main);
        bottomNavigationView = findViewById(R.id.bottom_nav);
        moveToFragment(new UserSignalementsDisplayFragment());
        bottomNavigationView.setOnItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                int itemId = item.getItemId();
                if (itemId == R.id.nav_home) {
                    moveToFragment(new UserSignalementsDisplayFragment());
                } else if (itemId == R.id.nav_create) {
                    Intent intent = new Intent(getApplicationContext(), SignalementActivity.class);
                    startActivity(intent);
                } else if (itemId == R.id.nav_mes_signalements) {
                    moveToFragment(new UserMesSignalementsDisplayFragment());
                } else if (itemId == R.id.nav_compte) {
                    moveToFragment(new AccountFragment(user));
                }
                return true;
            }
        });


    }

    private void moveToFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().replace(R.id.container_View, fragment).commit();
    }


    @Override
    protected void onResume() {
        super.onResume();
        Signalements=new ArrayList<>(mock.signalementsMock);
        mesSignalements = Signalements.stream().filter(t->t.getAuteur().equals(user.getId())).collect(Collectors.toList());
    }
}