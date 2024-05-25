package edu.ihm.vue.agent_equipements_view;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import edu.ihm.vue.R;
import edu.ihm.vue.agent_equipements_view.mvc.Controller_Equipements;
import edu.ihm.vue.agent_equipements_view.mvc.Model_Equipements;
import edu.ihm.vue.agent_equipements_view.mvc.View_Equipements;
import edu.ihm.vue.models.Signalement;

public class AgentEquipementsActivity extends AppCompatActivity {
    Signalement signalement;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_agent_equipements);
        onViewCreated( findViewById(R.id.view_equipements) );

        findViewById(R.id.sauvegarder).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                post();
            }
        });

    }



    public <T extends ViewGroup> void onViewCreated(T layout) {
        signalement=getIntent().getParcelableExtra("signalement");
        TextView T=(TextView)findViewById(R.id.titre);
        T.setText("Equipements pour : "+signalement.getTitle());
        Log.d("RadhiResult",signalement.getEquipements().toString());
        View_Equipements view = new View_Equipements(layout);
        Model_Equipements model = new Model_Equipements();
        model.addObserver(view);
        model.setEquipements(signalement.getEquipements());
        Controller_Equipements controller=new Controller_Equipements(view,model);
        Equipement_adapter adapter = new Equipement_adapter(getApplicationContext(),controller, model, view);
        view.setAdapter(adapter);
        model.setController(controller);
        view.setController(controller);
    }

    private void post() {
    }
}