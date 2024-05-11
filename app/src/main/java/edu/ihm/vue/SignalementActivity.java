package edu.ihm.vue;

import static android.app.PendingIntent.*;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import edu.ihm.vue.main_activities.MainActivity;
import edu.ihm.vue.models.Signalement;
import edu.ihm.vue.signalemet_fragments.AdresseSignalement;
import edu.ihm.vue.signalemet_fragments.CameraSignalement;
import edu.ihm.vue.signalemet_fragments.CommentaireSignalement;
import edu.ihm.vue.signalemet_fragments.DateSignalement;
import edu.ihm.vue.signalemet_fragments.TitreSignalement;
import edu.ihm.vue.signalemet_fragments.TypeSignalement;

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
    public void backToAdresseSignalementFragment(String comm) {
        nouveauSignalement.setCommentaire(comm);
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
    public void finishSignalement(String comm) {
        nouveauSignalement.setCommentaire(comm);
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
        showNotification();
    }

    private void showNotification() {
        int notificationId = new Random().nextInt(100);
        String channelId = "notifications_channel_1";
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        Intent intent = new Intent(getApplicationContext(), UserSignalementInfoDisplayActivity.class);
        intent.putExtra("signalement", nouveauSignalement);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        PendingIntent pendingIntent = getActivity(getApplicationContext(), 0, intent, FLAG_UPDATE_CURRENT);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(
                getApplicationContext(), channelId
        );
        builder.setSmallIcon(R.drawable.eco);
        builder.setDefaults(NotificationCompat.DEFAULT_ALL);
        builder.setContentTitle("Votre signalement a été pris en compte");
        builder.setContentText("Votre signalement :" + nouveauSignalement.getTitreSignalement() + " réalisé à la date de " + nouveauSignalement.getDateSignalement()
                + " a été enregistré et sera traité prochainement");
        builder.setStyle(new NotificationCompat.BigPictureStyle().bigPicture(nouveauSignalement.getPhoto()));
        builder.setContentIntent(pendingIntent);
        builder.setAutoCancel(true);
        builder.setPriority(NotificationCompat.PRIORITY_MAX);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            if (notificationManager != null && notificationManager.getNotificationChannel(channelId) == null){
                NotificationChannel notificationChannel = new NotificationChannel(channelId,"Notification_channel_1",NotificationManager.IMPORTANCE_HIGH);
                notificationChannel.setDescription("Notifier l'utilisateur de l'enregistrement du signalement");
                notificationChannel.enableVibration(true);
                notificationChannel.enableLights(true);
                notificationManager.createNotificationChannel(notificationChannel);
            }
        }
        Notification notification = builder.build();
        if(notificationManager !=null){
            notificationManager.notify(notificationId,notification);
        }
    }
}