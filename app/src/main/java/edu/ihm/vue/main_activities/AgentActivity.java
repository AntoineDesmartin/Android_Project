package edu.ihm.vue.main_activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;


import com.google.android.material.bottomnavigation.BottomNavigationView;

import edu.ihm.vue.AccountFragment;
import edu.ihm.vue.HomeFragment;
import edu.ihm.vue.MapsFragment;
import edu.ihm.vue.NotificationsFragment;
import edu.ihm.vue.R;

public class AgentActivity extends AppCompatActivity implements OnMapReadyCallback{


    private BottomNavigationView bottomNavigationView;
    private SupportMapFragment mapFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agent);

        OnMapReadyCallback onMapReadyCallback = this;

        bottomNavigationView = findViewById(R.id.bottom_nav);
        bottomNavigationView.setOnItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                int itemId = item.getItemId();
                if (itemId == R.id.nav_home) {
                    moveToFragment(new HomeFragment());
                } else if (itemId == R.id.nav_notifications) {

                    moveToFragment(new NotificationsFragment());
                } else if (itemId == R.id.nav_map) {

                    moveToFragment(new MapsFragment());
                } else if (itemId == R.id.nav_compte) {
                    moveToFragment(new AccountFragment());
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