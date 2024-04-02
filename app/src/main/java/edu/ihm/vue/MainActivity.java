package edu.ihm.vue;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.bottom_nav);
        bottomNavigationView.setOnItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                int itemId = item.getItemId();
                System.out.println(item.getItemId());
                if (itemId == R.id.nav_home) {
                    moveToFragment(new HomeFragment());
                } else if (itemId == R.id.nav_notifications) {
                    moveToFragment(new NotificationsFragment());
                } else if (itemId == R.id.nav_create) {
                    moveToFragment(new CreateFragment());
                } else if (itemId == R.id.nav_mesSignalement) {
                    moveToFragment(new MesSignalementsFragment());
                } else if (itemId == R.id.nav_compte) {
                    moveToFragment(new AccountFragment());
                }
                return true;
            }
        });
    }

    private void moveToFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().replace(R.id.container_View,fragment).commit();
    }
}