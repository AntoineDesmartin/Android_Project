package edu.ihm.vue.main_activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import edu.ihm.vue.AccountFragment;
import edu.ihm.vue.old_signalements_view.HomeFragment;
import edu.ihm.vue.MesSignalementsFragment;
import edu.ihm.vue.NotificationsFragment;
import edu.ihm.vue.R;
import edu.ihm.vue.SignalementActivity;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.bottom_nav);
        moveToFragment(new HomeFragment());
        bottomNavigationView.setOnItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                int itemId = item.getItemId();
                if (itemId == R.id.nav_home) {
                    moveToFragment(new HomeFragment());
                } else if (itemId == R.id.nav_create) {
                    Intent intent = new Intent(getApplicationContext(), SignalementActivity.class);
                    startActivity(intent);
                } else if (itemId == R.id.nav_mes_signalements) {
                    moveToFragment(new MesSignalementsFragment());
                } else if (itemId == R.id.nav_compte) {
                    moveToFragment(new AccountFragment());
                }
                return true;
            }
        });


    }

    @Override
    protected void onResume() {
        super.onResume();
        bottomNavigationView.setSelectedItemId(R.id.nav_home);
        moveToFragment(new HomeFragment());
    }

    private void moveToFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().replace(R.id.container_View, fragment).commit();
    }



}