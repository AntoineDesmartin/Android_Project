package edu.ihm.vue.agent_signalements_view;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import edu.ihm.vue.R;
import edu.ihm.vue.agent_equipements_view.AgentEquipementsActivity;
import edu.ihm.vue.models.Signalement;

public class AgentSignalementInfoDisplayActivity extends AppCompatActivity {
    Signalement signalement;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_agent_signalement_info_display);
        signalement=getIntent().getParcelableExtra("signalement");
        TextView titre=findViewById(R.id.titre_signalement);
        TextView type=findViewById(R.id.type_signalement);
        ImageView image=findViewById(R.id.image_signalement);
        TextView description=findViewById(R.id.description_signalement);
        TextView date=findViewById(R.id.date_signalement);
        TextView adresse=findViewById(R.id.adresse_signalement);
        TextView niveau=findViewById(R.id.niveau_signalement);
        TextView equipements=findViewById(R.id.equipements_signalement);
        TextView intervenant=findViewById(R.id.intervenant_signalement);
        TextView intervention=findViewById(R.id.intervention_signalement);
        description.setText(signalement.getCommentaire());
        type.setText(signalement.getTypeSignalement().toString());
        titre.setText(signalement.getTitreSignalement());
        image.setImageBitmap(signalement.getPhoto());
        date.setText(signalement.getDateIncident());
        adresse.setText(signalement.getAdresse()+", "+signalement.getVille()+", "+signalement.getCodePostal());
        niveau.setText(Integer.toString(signalement.getNiveau()));
        equipements.setText(String.join(", ", signalement.getEquipements()));
        intervenant.setText(signalement.getIntervenant());
        intervention.setText(signalement.getIntervention());
        findViewById(R.id.equipements).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Radhi",signalement.getEquipements().toString());
                Intent intent=new Intent(getApplicationContext(), AgentEquipementsActivity.class);
                intent.putExtra("signalement",(Parcelable) signalement);
                startActivity(intent);
            }
        });
    }
}