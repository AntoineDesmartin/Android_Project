package edu.ihm.vue;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;



import edu.ihm.vue.models.Signalement;
import edu.ihm.vue.signalemet_fragment.AdresseSignalement;
import edu.ihm.vue.signalemet_fragment.CameraSignalement;
import edu.ihm.vue.signalemet_fragment.CommentaireSignalement;
import edu.ihm.vue.signalemet_fragment.DateSignalement;
import edu.ihm.vue.signalemet_fragment.TitreSignalement;
import edu.ihm.vue.signalemet_fragment.TypeSignalement;
import edu.ihm.vue.web_service.GreenTrackAPI;
import edu.ihm.vue.web_service.WebService;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

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
        // This is to avoid thread error when performing HTTP request
        // TODO: launch new thread for request
        int SDK_INT = android.os.Build.VERSION.SDK_INT;
        if (SDK_INT > 8) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                    .permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

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
                .replace(R.id.fragment_container, new TitreSignalement(nouveauSignalement.getTitle()))
                .commit();
    }

    //TYPE SIGNALEMENT
    @Override
    public void backToTypeSignalementFragment(String date) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        try {
            Date parsedDate = sdf.parse(date);
            nouveauSignalement.setDate(parsedDate);
        } catch (ParseException e) {
            Log.d(TAG, "Can not parse date", e);
        }
        getSupportFragmentManager().beginTransaction()
                .setCustomAnimations(enterAnimationBack, exitAnimationBack)
                .replace(R.id.fragment_container, new TypeSignalement(nouveauSignalement.getTitle()))
                .commit();
    }

    @Override
    public void goToTypeSignalementFragment(String titre) {
        nouveauSignalement.setTitle(titre);
        getSupportFragmentManager().beginTransaction()
                .setCustomAnimations(enterAnimation, exitAnimation)
                .replace(R.id.fragment_container, new TypeSignalement(nouveauSignalement.getTitle()))
                .commit();
    }

    //DATE SIGNALEMENT
    @Override
    public void goToDateSignalementFragment(Signalement.TypeSignalement type) {
        nouveauSignalement.setTypeSignalement(type);
        getSupportFragmentManager().beginTransaction()
                .setCustomAnimations(enterAnimation, exitAnimation)
                .replace(R.id.fragment_container, new DateSignalement(nouveauSignalement.getDate()))
                .commit();
    }

    @Override
    public void backToDateSignalementFragment() {
        getSupportFragmentManager().beginTransaction()
                .setCustomAnimations(enterAnimationBack, exitAnimationBack)
                .replace(R.id.fragment_container, new DateSignalement(nouveauSignalement.getDate()))
                .commit();
    }

    //CAMERA SIGNALEMENT
    @Override
    public void goToCameraSignalementFragment(String date) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        try {
            Date parsedDate = sdf.parse(date);
            nouveauSignalement.setDate(parsedDate);
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
        nouveauSignalement.setAddress(adr);
        nouveauSignalement.setCity(vil);
        if (code.length() > 0)
            nouveauSignalement.setZipCode(code);
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
                .replace(R.id.fragment_container, new AdresseSignalement(nouveauSignalement.getAddress(), nouveauSignalement.getCity(), nouveauSignalement.getZipCode()))
                .commit();
    }

    @Override
    public void backToAdresseSignalementFragment(String comm) {
        nouveauSignalement.setDescription(comm);
        getSupportFragmentManager().beginTransaction()
                .setCustomAnimations(enterAnimationBack, exitAnimationBack)
                .replace(R.id.fragment_container, new AdresseSignalement(nouveauSignalement.getAddress(), nouveauSignalement.getCity(), nouveauSignalement.getZipCode()))
                .commit();
    }


    //COMMENTAIRE SIGNALEMENT
    @Override
    public void goToCommentaireSignalementFragment(String adr, String vil, String code) {
        nouveauSignalement.setAddress(adr);
        nouveauSignalement.setCity(vil);
        nouveauSignalement.setZipCode(code);
        getSupportFragmentManager().beginTransaction()
                .setCustomAnimations(enterAnimation, exitAnimation)
                .replace(R.id.fragment_container, new CommentaireSignalement(nouveauSignalement.getDescription()))
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
    public void finishSignalement(String comm) throws IOException {
        nouveauSignalement.setDescription(comm);
        nouveauSignalement.setBlockage();

        Call<Signalement> call = WebService.getInstance().getService().createReport(nouveauSignalement, "Bearer oat_MTI.bHZ5dldndnA0c1dtQjB6SG1CYmIxV2NHYVdnT3FZbE9MbWFBRWR4VTM0MjI1NzUwOTY");
        call.execute();

        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }
}