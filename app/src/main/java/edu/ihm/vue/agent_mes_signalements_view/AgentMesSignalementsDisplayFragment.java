package edu.ihm.vue.agent_mes_signalements_view;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.RecursiveTask;
import java.util.stream.Collectors;

import edu.ihm.vue.R;
import edu.ihm.vue.agent_signalements_view.AgentSignalementInfoDisplayActivity;
import edu.ihm.vue.agent_signalements_view.Clickable;
import edu.ihm.vue.agent_signalements_view.SignalementForAgentAdapter;
import edu.ihm.vue.main_activities.AgentActivity;
import edu.ihm.vue.mocks.Signalements;
import edu.ihm.vue.models.Signalement;
import edu.ihm.vue.models.User;


public class AgentMesSignalementsDisplayFragment extends Fragment implements Clickable {

    private ListView listview;
    private MesSignalementsForAgentAdapter adapter;

    public AgentMesSignalementsDisplayFragment() {
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_agent_mes_signalements_display, container, false);
        listview = view.findViewById(R.id.listView);
        adapter = new MesSignalementsForAgentAdapter(this, getContext());
        listview.setAdapter(adapter);
        return view;


    }

    @Override
    public void onClickButton(int position) {
        Intent intent = new Intent(getContext(), AgentSignalementInfoDisplayActivity.class);
        intent.putExtra("signalement",(Parcelable) AgentActivity.mesSignalements.get(position));
        startActivity(intent);
    }
}