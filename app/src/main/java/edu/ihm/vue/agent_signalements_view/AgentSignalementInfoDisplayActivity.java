package edu.ihm.vue.agent_signalements_view;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import edu.ihm.vue.R;
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
        description.setText(signalement.getDescription());
        type.setText(signalement.getTypeSignalement().toString());
        titre.setText(signalement.getTitle());
        image.setImageBitmap(signalement.getPhoto());
        date.setText(signalement.getDateIncident());
        adresse.setText(signalement.getAddress()+", "+signalement.getCity()+", "+signalement.getZipCode());
        niveau.setText(Integer.toString(signalement.getNiveau()));
        equipements.setText(signalement.getEquipements());
        intervenant.setText(signalement.getIntervenant());
        intervention.setText(signalement.getIntervention());
    }
}