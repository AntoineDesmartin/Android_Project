package edu.ihm.vue.main_activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;


import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;
import java.util.stream.Collectors;

import edu.ihm.vue.AccountFragment;
import edu.ihm.vue.agent_mes_signalements_view.AgentMesSignalementsDisplayFragment;
import edu.ihm.vue.mocks.Signalements;
import edu.ihm.vue.agent_map.MapsFragment;
import edu.ihm.vue.R;
import edu.ihm.vue.models.Signalement;
import edu.ihm.vue.models.User;
import edu.ihm.vue.agent_signalements_view.AgentSignalementsDisplayFragment;

public class AgentActivity extends AppCompatActivity implements OnMapReadyCallback {

    public static Signalements signalements;
    public static List<Signalement> mesSignalements;
    public static User user;
    private BottomNavigationView bottomNavigationView;
    private SupportMapFragment mapFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agent);
        user=getIntent().getParcelableExtra("user");
        signalements = new Signalements(getApplicationContext());
        Log.d("radhi",signalements.signalementsMock.get(0).getDateIncident());
        moveToFragment(new AgentSignalementsDisplayFragment());
        OnMapReadyCallback onMapReadyCallback = this;
        mesSignalements=Signalements.signalementsMock.stream().filter(t->t.getIntervenant().equals(AgentActivity.user.getId())).collect(Collectors.toList());
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


}