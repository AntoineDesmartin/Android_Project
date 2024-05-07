package edu.ihm.vue;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import edu.ihm.vue.main_activities.MainActivity;
import edu.ihm.vue.models.Signalement;
import edu.ihm.vue.signalemet_fragment.AdresseSignalement;
import edu.ihm.vue.signalemet_fragment.CameraSignalement;
import edu.ihm.vue.signalemet_fragment.CommentaireSignalement;
import edu.ihm.vue.signalemet_fragment.DateSignalement;
import edu.ihm.vue.signalemet_fragment.TitreSignalement;
import edu.ihm.vue.signalemet_fragment.TypeSignalement;

public class SignalementActivity extends AppCompatActivity implements SignalementListener, IPictureActivity {

    private final String TAG = "Green Track " + getClass().getSimpleName();
    ;
    int enterAnimation = R.anim.slide_in_right;
    int exitAnimation = R.anim.slide_out_left;
    int enterAnimationBack = R.anim.slide_in_left;
    int exitAnimationBack = R.anim.slide_out_right;
    private Signalement nouveauSignalement;
    private CameraSignalement cameraSignalement;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_signalement);
        getSupportFragmentManager().beginTransaction().
                add(R.id.fragment_container, new TitreSignalement()).commit();
        nouveauSignalement = new Signalement();
    }

    @Override
    public void annulerSignalement() {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }

    //TITRE SIGNALEMENT
    @Override
    public void backToTitreSignalementFragment() {
        getSupportFragmentManager().beginTransaction()
                .setCustomAnimations(enterAnimationBack, exitAnimationBack)
                .replace(R.id.fragment_container, new TitreSignalement(nouveauSignalement.getTitreSignalement()))
                .commit();
    }

    //TYPE SIGNALEMENT
    @Override
    public void backToTypeSignalementFragment(String date) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        try {
            Date parsedDate = sdf.parse(date);
            nouveauSignalement.setDateSignalement(parsedDate);
        } catch (ParseException e) {
            Log.d(TAG, "Can not parse date", e);
        }
        getSupportFragmentManager().beginTransaction()
                .setCustomAnimations(enterAnimationBack, exitAnimationBack)
                .replace(R.id.fragment_container, new TypeSignalement(nouveauSignalement.getTitreSignalement()))
                .commit();
    }

    @Override
    public void goToTypeSignalementFragment(String titre) {
        nouveauSignalement.setTitreSignalement(titre);
        getSupportFragmentManager().beginTransaction()
                .setCustomAnimations(enterAnimation, exitAnimation)
                .replace(R.id.fragment_container, new TypeSignalement(nouveauSignalement.getTitreSignalement()))
                .commit();
    }

    //DATE SIGNALEMENT
    @Override
    public void goToDateSignalementFragment(Signalement.TypeSignalement type) {
        nouveauSignalement.setTypeSignalement(type);
        getSupportFragmentManager().beginTransaction()
                .setCustomAnimations(enterAnimation, exitAnimation)
                .replace(R.id.fragment_container, new DateSignalement(nouveauSignalement.getDateSignalement()))
                .commit();
    }

    @Override
    public void backToDateSignalementFragment() {
        getSupportFragmentManager().beginTransaction()
                .setCustomAnimations(enterAnimationBack, exitAnimationBack)
                .replace(R.id.fragment_container, new DateSignalement(nouveauSignalement.getDateSignalement()))
                .commit();
    }

    //CAMERA SIGNALEMENT
    @Override
    public void goToCameraSignalementFragment(String date) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        try {
            Date parsedDate = sdf.parse(date);
            nouveauSignalement.setDateSignalement(parsedDate);
        } catch (ParseException e) {
            Log.d(TAG, "Can not parse date", e);
        }
        cameraSignalement = new CameraSignalement(nouveauSignalement.getPhoto());
        getSupportFragmentManager().beginTransaction()
                .setCustomAnimations(enterAnimation, exitAnimation)
                .replace(R.id.fragment_container, cameraSignalement)
                .commit();
    }

    @Override
    public void backToCameraSignalementFragment(String adr, String vil, String code) {
        nouveauSignalement.setAdresse(adr);
        nouveauSignalement.setVille(vil);
        if (code.length() > 0)
            nouveauSignalement.setCodePostal(Integer.parseInt(code));
        cameraSignalement = new CameraSignalement(nouveauSignalement.getPhoto());
        getSupportFragmentManager().beginTransaction()
                .setCustomAnimations(enterAnimationBack, exitAnimationBack)
                .replace(R.id.fragment_container, cameraSignalement)
                .commit();

    }

    //ADRESSE SIGNALEMENT
    @Override
    public void goToAdresseSignalementFragment() {
        getSupportFragmentManager().beginTransaction()
                .setCustomAnimations(enterAnimation, exitAnimation)
                .replace(R.id.fragment_container, new AdresseSignalement(nouveauSignalement.getAdresse(), nouveauSignalement.getVille(), nouveauSignalement.getCodePostal()))
                .commit();
    }

    @Override
    public void backToAdresseSignalementFragment() {
        getSupportFragmentManager().beginTransaction()
                .setCustomAnimations(enterAnimationBack, exitAnimationBack)
                .replace(R.id.fragment_container, new AdresseSignalement(nouveauSignalement.getAdresse(), nouveauSignalement.getVille(), nouveauSignalement.getCodePostal()))
                .commit();
    }


    //COMMENTAIRE SIGNALEMENT
    @Override
    public void goToCommentaireSignalementFragment(String adr, String vil, String code) {
        nouveauSignalement.setAdresse(adr);
        nouveauSignalement.setVille(vil);
        nouveauSignalement.setCodePostal(Integer.parseInt(code));
        getSupportFragmentManager().beginTransaction()
                .setCustomAnimations(enterAnimation, exitAnimation)
                .replace(R.id.fragment_container, new CommentaireSignalement(nouveauSignalement.getCommentaire()))
                .commit();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case REQUEST_CAMERA: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    cameraSignalement.takePicture();
                }
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CAMERA) {
            if (resultCode == RESULT_OK) {
                nouveauSignalement.setPhoto((Bitmap) data.getExtras().get("data"));
                cameraSignalement.setImage(nouveauSignalement.getPhoto());
            }
        }
    }

    @Override
    public void finishSignalement() {
        //REQUETTE HTTP A AJOUTER
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }
}