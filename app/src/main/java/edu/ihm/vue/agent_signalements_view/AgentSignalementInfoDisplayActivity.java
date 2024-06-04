package edu.ihm.vue.agent_signalements_view;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;



import java.util.Date;
import java.util.logging.Logger;

import edu.ihm.vue.R;
import edu.ihm.vue.agent_equipements_view.AgentEquipementsActivity;
import edu.ihm.vue.main_activities.AgentActivity;
import edu.ihm.vue.mocks.Signalements;
import edu.ihm.vue.models.Signalement;
import edu.ihm.vue.agent_signalements_view.AgentSignalementAgendaActivity;
public class AgentSignalementInfoDisplayActivity extends AppCompatActivity {
    Signalement signalement;
    Button btnAjouterAagenda;
    Button btnMassigner;
    Button btnEquipements;
    Button btnRetour;

    TextView intervention;

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
        intervention=findViewById(R.id.intervention_signalement);


        btnAjouterAagenda=findViewById(R.id.ajouter_agenda);
        btnMassigner=findViewById(R.id.massigner);
        btnEquipements=findViewById(R.id.equipements);
        btnRetour=findViewById(R.id.retour);


        description.setText(signalement.getDescription());
        type.setText(signalement.getTypeSignalement().toString());
        titre.setText(signalement.getTitle());
        image.setImageBitmap(signalement.getPhoto());
        date.setText(signalement.getDateIncident());
        adresse.setText(signalement.getAddress()+", "+signalement.getCity()+", "+signalement.getZipCode());
        niveau.setText(Integer.toString(signalement.getNiveau()));
        intervenant.setText(signalement.getIntervenant());
        equipements.setText(String.join(", ", signalement.getEquipements()));
        intervention.setText(signalement.getIntervention());
        signalementTitre= signalement.getTitle().trim();
        setBtnMassigner();

        btnRetour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), AgentActivity.class);
                intent.putExtra("user", AgentActivity.user);
                startActivity(intent);
            }
        });

        btnMassigner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), AgentSignalementAgendaActivity.class);
                intent.putExtra("signalement",(Parcelable) signalement);
                startActivity(intent);
                setBtnMassigner();
            }
        });

        btnAjouterAagenda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addToCalendar(signalement);
            }
        });
        btnEquipements.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Radhi",signalement.getEquipements().toString());
                Intent intent=new Intent(getApplicationContext(), AgentEquipementsActivity.class);
                intent.putExtra("signalement",(Parcelable) signalement);
                startActivity(intent);
            }
        });
    }

    public void addToCalendar(Signalement s){

        if(signalement.getInterventioninFull()==null || intervention.getText().toString().isEmpty()){
            Toast.makeText(this, "Ce signalement n'est pas encore attribué!", Toast.LENGTH_SHORT).show();
        }else{
            Date start_time = s.getInterventioninFull();


            // Lors du clic sur le bouton, l'Intent est démarré -> pour créer un événement dans l'agenda avec l'heure donnée
            Date mStartTime = null;
            Date mEndTime = null;
            int delay = 1800000;

            mStartTime = start_time;
            mEndTime = new Date(mStartTime.getTime() + delay);

            Date finalMStartTime = mStartTime;
            Date finalMEndTime = mEndTime;

            Intent intent = new Intent(Intent.ACTION_EDIT);
            intent.setType("vnd.android.cursor.item/event");
            intent.putExtra("beginTime", finalMStartTime.getTime());
            intent.putExtra("time", true);
            intent.putExtra("rule", "FREQ=YEARLY");
            intent.putExtra("endTime", finalMEndTime.getTime());
            intent.putExtra("title", "GreenTrack : "+signalement.getTitle());
            startActivity(intent);
        }




    }

    public void setBtnMassigner(){
        logger.info("signalement in screen ? : "+ intervention.getText().toString());

        if(signalement.getInterventioninFull()==null || !intervention.getText().toString().isEmpty()){
            //logger.info("signalement in screen ? : "+ intervention.getText().toString());
            btnMassigner.setBackgroundColor(getResources().getColor(R.color.s_gray));
            btnMassigner.setEnabled(false);


            //btnMassigner.setVisibility(View.GONE);
        }else {
            btnMassigner.setBackgroundColor(getResources().getColor(R.color.green));
            btnMassigner.setEnabled(true);
        }
    }



}