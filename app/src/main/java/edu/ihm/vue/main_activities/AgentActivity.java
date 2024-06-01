package edu.ihm.vue.main_activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.fragment.app.Fragment;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.MenuItem;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;


import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import edu.ihm.vue.AccountFragment;
import edu.ihm.vue.UserSignalementInfoDisplayActivity;
import edu.ihm.vue.agent_mes_signalements_view.AgentMesSignalementsDisplayFragment;
import edu.ihm.vue.mocks.Signalements;
import edu.ihm.vue.agent_map.MapsFragment;
import edu.ihm.vue.R;
import edu.ihm.vue.models.Signalement;
import edu.ihm.vue.models.User;
import edu.ihm.vue.agent_signalements_view.AgentSignalementsDisplayFragment;

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

public class AgentActivity extends AppCompatActivity implements OnMapReadyCallback {
    public static boolean isLogged=false;

    public static Signalements signalements;
    public static List<Signalement> mesSignalements=new ArrayList<>();
    public static User user;
    private BottomNavigationView bottomNavigationView;
    private SupportMapFragment mapFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agent);
        user=getIntent().getParcelableExtra("user");
        signalements =Signalements.getInstance(getApplicationContext());
        Log.d("radhi",signalements.signalementsMock.get(0).getDateIncident());
        moveToFragment(new AgentSignalementsDisplayFragment());
        OnMapReadyCallback onMapReadyCallback = this;

        mesSignalements=signalements.signalementsMock.stream().filter(t->t.getIntervenant().equals(AgentActivity.user.getId())).collect(Collectors.toList());
        Log.d("radhi",mesSignalements.toString());

        bottomNavigationView = findViewById(R.id.bottom_nav);
        bottomNavigationView.setOnItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                int itemId = item.getItemId();
                if (itemId == R.id.nav_home) {
                    moveToFragment(new AgentSignalementsDisplayFragment());
                } else if (itemId == R.id.nav_mes_signalements) {
                    moveToFragment(new AgentMesSignalementsDisplayFragment());
                } else if (itemId == R.id.nav_map) {
                    moveToFragment(new MapsFragment());
                } else if (itemId == R.id.nav_compte) {
                    moveToFragment(new AccountFragment(user));
                }
                return true;
            }
        });
        if(!isLogged) {
            showNotification();
            isLogged=true;
        }


    }

    private void moveToFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().replace(R.id.container_View, fragment).commit();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        LatLng paris = new LatLng(48.8566, 2.3522);
        googleMap.addMarker(new MarkerOptions().position(paris).title("Marker in Paris"));
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(paris, 12));
    }

    private void showNotification() {
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        // Créer un canal de notification pour les versions d'Android 8.0 (API niveau 26) et plus récentes
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel("channel_id", "Channel Name", NotificationManager.IMPORTANCE_DEFAULT);
            notificationManager.createNotificationChannel(channel);
        }



        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "channel_id")
                .setSmallIcon(R.drawable.calendar)
                .setContentTitle("Rappel")
                .setContentText("Bonjour, n'oublier pas de consulter les signalements en attente d'intervention.")
                .setAutoCancel(true)
                .setTimeoutAfter(12000);

        // Afficher la notification
        notificationManager.notify(1, builder.build());

    }


    @Override
    protected void onResume() {
        super.onResume();
        mesSignalements=signalements.signalementsMock.stream().filter(t->t.getIntervenant().equals(AgentActivity.user.getId())).collect(Collectors.toList());

    }
}