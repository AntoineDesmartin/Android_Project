package edu.ihm.vue;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.icu.util.Calendar;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import edu.ihm.vue.main_activities.MainActivity;
import edu.ihm.vue.models.NormalSignalementFactory;
import edu.ihm.vue.models.Signalement;
import edu.ihm.vue.models.SignalementFactory;
import edu.ihm.vue.models.UrgentSignalementFactory;
import edu.ihm.vue.create_signalemet_fragments.AdresseSignalement;
import edu.ihm.vue.create_signalemet_fragments.CameraSignalement;
import edu.ihm.vue.create_signalemet_fragments.CommentaireSignalement;
import edu.ihm.vue.create_signalemet_fragments.DateSignalement;
import edu.ihm.vue.create_signalemet_fragments.TitreSignalement;
import edu.ihm.vue.create_signalemet_fragments.TypeSignalement;

public class SignalementActivity extends AppCompatActivity implements SignalementListener, IPictureActivity {

    private final String TAG = "Green Track " + getClass().getSimpleName();
    ;
    private int enterAnimation = R.anim.slide_in_right;
    private int exitAnimation = R.anim.slide_out_left;
    private int enterAnimationBack = R.anim.slide_in_left;
    private int exitAnimationBack = R.anim.slide_out_right;
    private Signalement nouveauSignalement;
    private CameraSignalement cameraSignalement;

    private String titre_signalement="";
    private int type_signalement;
    private String date_incident ="";
    private Bitmap photo=null;
    private String adresse="";
    private String ville="";
    private int code;
    private String commentaire="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_signalement);
        getSupportFragmentManager().beginTransaction().
                add(R.id.fragment_container, new TitreSignalement()).commit();
    }

    @Override
    public void annulerSignalement() {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        intent.putExtra("user",MainActivity.user);
        startActivity(intent);
    }

    //TITRE SIGNALEMENT
    @Override
    public void backToTitreSignalementFragment() {
        getSupportFragmentManager().beginTransaction()
                .setCustomAnimations(enterAnimationBack, exitAnimationBack)
                .replace(R.id.fragment_container, new TitreSignalement(this.titre_signalement))
                .commit();
    }

    //TYPE SIGNALEMENT
    @Override
    public void backToTypeSignalementFragment(String date) {
        /*SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        try {
            Date parsedDate = sdf.parse(date);
            nouveauSignalement.setDateSignalement(parsedDate);
        } catch (ParseException e) {
            Log.d(TAG, "Can not parse date", e);
        }*/
        getSupportFragmentManager().beginTransaction()
                .setCustomAnimations(enterAnimationBack, exitAnimationBack)
                .replace(R.id.fragment_container, new TypeSignalement(this.titre_signalement))
                .commit();
    }

    @Override
    public void goToTypeSignalementFragment(String titre) {
        this.titre_signalement=titre;
        getSupportFragmentManager().beginTransaction()
                .setCustomAnimations(enterAnimation, exitAnimation)
                .replace(R.id.fragment_container, new TypeSignalement(this.titre_signalement))
                .commit();
    }

    //DATE SIGNALEMENT
    @Override
    public void goToDateSignalementFragment(int type) {
        this.type_signalement=type;
        getSupportFragmentManager().beginTransaction()
                .setCustomAnimations(enterAnimation, exitAnimation)
                .replace(R.id.fragment_container, new DateSignalement(this.date_incident))
                .commit();
    }

    @Override
    public void backToDateSignalementFragment() {
        getSupportFragmentManager().beginTransaction()
                .setCustomAnimations(enterAnimationBack, exitAnimationBack)
                .replace(R.id.fragment_container, new DateSignalement(this.date_incident))
                .commit();
    }

    //CAMERA SIGNALEMENT
    @Override
    public void goToCameraSignalementFragment(String date) {
        /*SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        try {
            Date parsedDate = sdf.parse(date);
            nouveauSignalement.setDateSignalement(parsedDate);
        } catch (ParseException e) {
            Log.d(TAG, "Can not parse date", e);
        }*/
        this.date_incident =date;
        cameraSignalement = new CameraSignalement(this.photo);
        getSupportFragmentManager().beginTransaction()
                .setCustomAnimations(enterAnimation, exitAnimation)
                .replace(R.id.fragment_container, cameraSignalement)
                .commit();
    }

    @Override
    public void backToCameraSignalementFragment(String adr, String vil, String code) {
        this.adresse=adr;
        this.ville=vil;
        if (code.length() > 0)
            this.code=Integer.parseInt(code);
        cameraSignalement = new CameraSignalement(this.photo);
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
                .replace(R.id.fragment_container, new AdresseSignalement(this.adresse, this.ville, this.code))
                .commit();
    }

    @Override
    public void backToAdresseSignalementFragment(String comm) {
        nouveauSignalement.setCommentaire(comm);
        getSupportFragmentManager().beginTransaction()
                .setCustomAnimations(enterAnimationBack, exitAnimationBack)
                .replace(R.id.fragment_container, new AdresseSignalement(this.adresse, this.ville, this.code))
                .commit();
    }


    //COMMENTAIRE SIGNALEMENT
    @Override
    public void goToCommentaireSignalementFragment(String adr, String vil, String code) {
        this.adresse=adr;
        this.ville=vil;
        this.code=Integer.parseInt(code);
        getSupportFragmentManager().beginTransaction()
                .setCustomAnimations(enterAnimation, exitAnimation)
                .replace(R.id.fragment_container, new CommentaireSignalement(this.commentaire))
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
                this.photo=((Bitmap) data.getExtras().get("data"));
                cameraSignalement.setImage(this.photo);
            }
        }
    }

    @Override
    public void finishSignalement(String comm) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date parsedDate = null;
        try {
            parsedDate = sdf.parse(this.date_incident);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(parsedDate);
            calendar.add(Calendar.WEEK_OF_YEAR, 1);
            Date oneWeekAfter = calendar.getTime();
            Date currentDate = new Date();

            if (currentDate.after(oneWeekAfter)) {
                SignalementFactory factory=new UrgentSignalementFactory();
                nouveauSignalement=factory.build(this.type_signalement);
            } else {
                SignalementFactory factory=new NormalSignalementFactory();
                nouveauSignalement=factory.build(this.type_signalement);
            }
        } catch (ParseException e) {
            Log.d(TAG, "Can not parse date", e);
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
        nouveauSignalement.setTitreSignalement(this.titre_signalement);
        nouveauSignalement.setDateIncident(parsedDate);
        nouveauSignalement.setPhoto(this.photo);
        nouveauSignalement.setAdresse(this.adresse);
        nouveauSignalement.setVille(this.ville);
        nouveauSignalement.setCodePostal(this.code);


        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        intent.putExtra("user",MainActivity.user);
        startActivity(intent);
        showNotification();
    }

    private void showNotification() {
        int notificationId = new Random().nextInt(100);
        String channelId = "notifications_channel_1";
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        Intent intent = new Intent(getApplicationContext(), UserSignalementInfoDisplayActivity.class);
        intent.putExtra("signalement", (Parcelable) nouveauSignalement);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(), 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext(), channelId);
        builder.setSmallIcon(R.drawable.eco);
        builder.setLargeIcon(nouveauSignalement.getPhoto());
        builder.setDefaults(NotificationCompat.DEFAULT_ALL);
        builder.setContentTitle("Votre signalement a été pris en compte");
        builder.setContentText("Votre signalement : " + nouveauSignalement.getTitreSignalement() + " réalisé à la date de " + nouveauSignalement.getDateIncident()
                + " a été enregistré et sera traité prochainement");
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
        if (notificationManager != null) {
            notificationManager.notify(notificationId, notification);
        }
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        intent.putExtra("user",MainActivity.user);
        startActivity(intent);
        finish();
    }
}