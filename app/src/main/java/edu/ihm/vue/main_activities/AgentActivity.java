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
                .setAutoCancel(true)  // Permet à la notification de disparaître après avoir été cliquée
                .setTimeoutAfter(12000);  // Définit la durée de vie de la notification en millisecondes (5000 ms = 5 secondes)

        // Afficher la notification
        notificationManager.notify(1, builder.build());

    }


    @Override
    protected void onResume() {
        super.onResume();
        mesSignalements=signalements.signalementsMock.stream().filter(t->t.getIntervenant().equals(AgentActivity.user.getId())).collect(Collectors.toList());

    }
}