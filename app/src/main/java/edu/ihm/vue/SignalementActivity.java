package edu.ihm.vue;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import edu.ihm.vue.models.Signalement;
import edu.ihm.vue.signalemet_fragment.DateSignalement;
import edu.ihm.vue.signalemet_fragment.TitreSignalement;
import edu.ihm.vue.signalemet_fragment.TypeSignalement;

public class SignalementActivity extends AppCompatActivity implements SignalementListener{

    int enterAnimation = R.anim.slide_in_right;
    int exitAnimation = R.anim.slide_out_left;
    int enterAnimationBack = R.anim.slide_in_left;
    int exitAnimationBack = R.anim.slide_out_right;

    private Signalement nouveauSignalement;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //init values
        //onCreate
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_signalement);
        getSupportFragmentManager().beginTransaction().
                add(R.id.fragment_container, TitreSignalement.newInstance("")).commit();
        nouveauSignalement=new Signalement();

    }

    @Override
    public void annulerSignalement(){
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }

    @Override
    public void BackToTypeSignalementFragment() {
        getSupportFragmentManager().beginTransaction()
                .setCustomAnimations(enterAnimationBack,exitAnimationBack)
                .replace(R.id.fragment_container, new TypeSignalement())
                .commit();
    }

    @Override
    public void goToTypeSignalementFragment(String titre) {
        nouveauSignalement.setTitreSignalement(titre);
        getSupportFragmentManager().beginTransaction()
                .setCustomAnimations(enterAnimation, exitAnimation)
                .replace(R.id.fragment_container, TypeSignalement.newInstance(titre))
                .commit();
    }

    @Override
    public void BackToTitreSignalementFragment() {
        getSupportFragmentManager().beginTransaction()
                .setCustomAnimations(enterAnimationBack,exitAnimationBack)
                .replace(R.id.fragment_container, TitreSignalement.newInstance(nouveauSignalement.getTitreSignalement()))
                .commit();
    }

    @Override
    public void goToDateFragement(Signalement.TypeSignalement type) {
        nouveauSignalement.setTypeSignalement(type);
        getSupportFragmentManager().beginTransaction()
                .setCustomAnimations(enterAnimation,exitAnimation)
                .replace(R.id.fragment_container, new DateSignalement())
                .commit();
    }

}