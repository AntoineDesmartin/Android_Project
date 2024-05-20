package edu.ihm.vue.main_activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
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

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;

    public static User user;
    public static List<Signalement> Signalements;
    public static List<Signalement> mesSignalements;
    private Signalements mock;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        user=getIntent().getParcelableExtra("user");
        mock=new Signalements(getApplicationContext());
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



}