package edu.ihm.vue.agent_signalements_view;

import android.content.Intent;
import android.os.Bundle;


import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import edu.ihm.vue.R;
import edu.ihm.vue.mocks.Signalements;
import edu.ihm.vue.models.Signalement;


public class AgentSignalementsDisplayFragment extends Fragment implements Clickable,IDisplay  {

    private ListView listview;
    private Button plus;
    private Button minus;
    private TextView minimalLevel;
    public static List<Signalement> signalementsToDisplay;
    public static final int MAX_LEVEL = 5;
    public static final int MIN_LEVEL = 0;
    private SignalementForAgentAdapter adapter;
    private NiveauMinimalSensor niveauSensor=new NiveauMinimalSensor();
    private NiveauFilter niveauFilter=new NiveauFilter(this);

    public AgentSignalementsDisplayFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        if (Signalements.signalementsMock != null) {
            signalementsToDisplay = new ArrayList<>(Signalements.signalementsMock);
        } else {
            signalementsToDisplay = new ArrayList<>();
        }

        View view =inflater.inflate(R.layout.fragment_agent_signalements_display, container, false);
        niveauSensor.addObserver(niveauFilter);
        minimalLevel=view.findViewById(R.id.niveau_minimal);
        minimalLevel.setText(Integer.toString(niveauSensor.getNiveauMinimal()));
        plus=view.findViewById(R.id.plus);
        minus=view.findViewById(R.id.moins);
        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int currentLevel = niveauSensor.getNiveauMinimal();
                if (currentLevel < MAX_LEVEL) {
                    niveauSensor.setNiveauMinimal(currentLevel + 1);
                }
            }

        });
        minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int currentLevel = niveauSensor.getNiveauMinimal();
                if (currentLevel > MIN_LEVEL) {
                    niveauSensor.setNiveauMinimal(currentLevel - 1);
                }
            }
        });
        listview = view.findViewById(R.id.listView);
        adapter = new SignalementForAgentAdapter(this, getContext());
        listview.setAdapter(adapter);
        return view;
    }


    @Override
    public void onClickButton(int position) {
        Intent intent = new Intent(getContext(), AgentSignalementInfoDisplayActivity.class);
        intent.putExtra("signalement",(Parcelable) signalementsToDisplay.get(position));
        startActivity(intent);
    }


    @Override
    public void updateSignalementsDisplay(int currentLevel) {
        signalementsToDisplay=Signalements.signalementsMock.stream().filter(s->s.getNiveau()>=currentLevel)
                .collect(Collectors.toList());
        //adapter = new SignalementForAgentAdapter(this, getContext());
        //listview.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        minimalLevel.setText(Integer.toString(currentLevel));
    }
}