package edu.ihm.vue.agent_equipements_view;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import edu.ihm.vue.R;
import edu.ihm.vue.agent_equipements_view.mvc.Controller_Equipements;
import edu.ihm.vue.agent_equipements_view.mvc.Model_Equipements;
import edu.ihm.vue.agent_equipements_view.mvc.View_Equipements;
import edu.ihm.vue.agent_signalements_view.AgentSignalementInfoDisplayActivity;
import edu.ihm.vue.main_activities.AgentActivity;
import edu.ihm.vue.mocks.Signalements;
import edu.ihm.vue.models.Signalement;

public class AgentEquipementsActivity extends AppCompatActivity {
    private Signalement signalement;

    private View_Equipements view ;
    private Model_Equipements model;
    Controller_Equipements controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_agent_equipements);
        onViewCreated(findViewById(R.id.view_equipements));

        findViewById(R.id.sauvegarder).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                post();
            }
        });

    }


    public <T extends ViewGroup> void onViewCreated(T layout) {
        signalement = getIntent().getParcelableExtra("signalement");
        TextView T = (TextView) findViewById(R.id.titre);
        T.setText("Equipements pour : " + signalement.getTitle());
        Log.d("RadhiResult", signalement.getEquipements().toString());
        view = new View_Equipements(layout);
        model = new Model_Equipements();
        model.addObserver(view);
        model.setEquipements(signalement.getEquipements());
        controller = new Controller_Equipements(view, model);
        Equipement_adapter adapter = new Equipement_adapter(getApplicationContext(), controller, model, view);
        view.setAdapter(adapter);
        model.setController(controller);
        view.setController(controller);
    }

    private void post() {
        this.signalement.setEquipements(new ArrayList<>(model.getEquipements()));
        int indice = -1;
        for (int i = 0; i < Signalements.signalementsMock.size(); i++) {
            Signalement currentSignalement = Signalements.signalementsMock.get(i);
            if (currentSignalement.getAddress().equals(signalement.getAddress()) &&
                    currentSignalement.getAuteur().equals(signalement.getAuteur()) &&
                    currentSignalement.getNiveau() == signalement.getNiveau() &&
                    currentSignalement.getCity().equals(signalement.getCity()) &&
                    currentSignalement.getZipCode().equals(signalement.getZipCode()) &&
                    currentSignalement.getDescription().equals(signalement.getDescription())) {
                indice = i;
                break;
            }
        }
        if (indice != -1) {
            Signalements.signalementsMock.get(indice).setEquipements(new ArrayList<>(model.getEquipements()));
        } else {
            Log.d("radhi","No matching signalement found.");
        }
        Intent intent = new Intent(getApplicationContext(), AgentSignalementInfoDisplayActivity.class);
        intent.putExtra("signalement", (Parcelable) signalement);
        startActivity(intent);
    }

}