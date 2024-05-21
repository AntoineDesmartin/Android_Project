package edu.ihm.vue.signalements_view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import java.util.logging.Logger;

import edu.ihm.vue.R;
import edu.ihm.vue.models.Signalement;

public class AgentSignalementAgendaActivity extends AppCompatActivity {
    Signalement signalement;

    Logger logger = Logger.getLogger("MyLog");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agent_signalement_agenda);
        Intent intent = getIntent();
        signalement=getIntent().getParcelableExtra("signalement");

        logger.info("titre du signalement in agendaActivity : "+signalement.toString());

        final String titre = signalement.getTitreSignalement();


    }
}