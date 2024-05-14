package edu.ihm.vue.signalements_view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import java.util.logging.Logger;

import edu.ihm.vue.R;

public class AgentSignalementAgendaActivity extends AppCompatActivity {

    Logger logger = Logger.getLogger("MyLog");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agent_signalement_agenda);
        Intent intent = getIntent();
        final String titre = intent.getStringExtra(AgentSignalementInfoDisplayActivity.signalementTitre);
    }
}