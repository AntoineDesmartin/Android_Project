package edu.ihm.vue.signalements_view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import java.util.logging.Logger;

import edu.ihm.vue.R;
import edu.ihm.vue.models.Signalement;

public class AgentSignalementInfoDisplayActivity extends AppCompatActivity {
    Signalement signalement;
    Button btnAjouterAagenda;

    public static String signalementTitre;
    Logger logger = Logger.getLogger("MyLog");


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


        btnAjouterAagenda=findViewById(R.id.ajouter_agenda);


        description.setText(signalement.getCommentaire());
        type.setText(signalement.getTypeSignalement().toString());
        titre.setText(signalement.getTitreSignalement());
        image.setImageBitmap(signalement.getPhoto());
        date.setText(signalement.getDateSignalement());
        adresse.setText(signalement.getAdresse()+", "+signalement.getVille()+", "+signalement.getCodePostal());
        niveau.setText(Integer.toString(signalement.getNiveau()));
        equipements.setText(signalement.getEquipements());
        intervenant.setText(signalement.getIntervenant());
        intervention.setText(signalement.getIntervention());


        signalementTitre= signalement.getTitreSignalement().trim();


        btnAjouterAagenda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //logger.info("titre du signalement in from : "+signalementTitre);
                Intent intent = new Intent(getApplicationContext(), AgentSignalementAgendaActivity.class);
                signalementTitre = signalement.getTitreSignalement();
                intent.putExtra(signalementTitre, String.valueOf(signalement.getTitreSignalement()));
                startActivity(intent);
            }
        });
    }
}