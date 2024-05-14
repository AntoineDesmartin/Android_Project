package edu.ihm.vue;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import edu.ihm.vue.main_activities.MainActivity;
import edu.ihm.vue.models.Signalement;

public class UserSignalementInfoDisplayActivity extends AppCompatActivity {
    Signalement signalement;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_user_signalement_info_display);
        signalement=getIntent().getParcelableExtra("signalement");
        TextView titre=findViewById(R.id.titre_signalement);
        TextView type=findViewById(R.id.type_signalement);
        ImageView image=findViewById(R.id.image_signalement);
        TextView description=findViewById(R.id.description_signalement);
        TextView date=findViewById(R.id.date_signalement);
        TextView adresse=findViewById(R.id.adresse_signalement);
        TextView intervention=findViewById(R.id.intervention_signalement);
        description.setText(signalement.getCommentaire());
        type.setText(signalement.getTypeSignalement().toString());
        titre.setText(signalement.getTitreSignalement());
        image.setImageBitmap(signalement.getPhoto());
        date.setText(signalement.getDateIncident());
        adresse.setText(signalement.getAdresse()+", "+signalement.getVille()+", "+signalement.getCodePostal());
        intervention.setText(signalement.getIntervention());
        findViewById(R.id.retour).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });
    }
}